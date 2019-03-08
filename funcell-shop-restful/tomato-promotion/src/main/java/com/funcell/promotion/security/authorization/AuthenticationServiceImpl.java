package com.funcell.promotion.security.authorization;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.funcell.promotion.common.constant.TomatoConstant;
import com.funcell.promotion.common.emum.ErrorCodeEnum;
import com.funcell.promotion.common.vo.FormIdVo;
import com.funcell.promotion.moudle.entity.CommissionDailyEntity;
import com.funcell.promotion.moudle.service.ICommissionDailyService;
import com.funcell.promotion.moudle.service.ITemplateService;
import com.funcell.promotion.moudle.service.ITomatoCouponUserService;
import com.funcell.promotion.security.user.ITokenService;
import com.funcell.promotion.security.user.ITomatoUserService;
import com.funcell.promotion.security.user.TomatoUserEntity;
import com.funcell.promotion.utils.*;
import org.apache.http.util.TextUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import javax.servlet.ServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Transactional
@Service
public class AuthenticationServiceImpl implements IAuthenticationService {
    private String ERROR_CODE_MESSAGE = "errcode";
    private String ERROR_MSG_MESSAGE = "errmsg";
    private String SESSION_KEY = "session_key";
    private String OPEN_ID_KEY = "openid";
    @Autowired
    private AuthenticationConfigProperties configProperties;
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private UserDetailsService userDetailsService;
    @Resource
    private ITokenService tokenService;
    @Autowired
    private ITomatoUserService tomatoUserService;

    @Autowired
    private ITemplateService templateService;

    @Autowired
    private ITomatoCouponUserService tomatoCouponUserService;

    @Autowired
    private ICommissionDailyService commissionDailyService;

    /**
     * 重置用户的每日数据
     */
    private void resetDaily() {

        CommissionDailyEntity commissionDailyEntity = commissionDailyService.getCommissionDaily();
        if (null == commissionDailyEntity) {
            return;
        } else if (DateUtils.isNotOneDay(commissionDailyEntity.getCommission_date(), new Date()) > TomatoConstant.Common.NUMBER_0) {
            commissionDailyEntity.setCommission_times(TomatoConstant.Common.NUMBER_0);
            commissionDailyService.insertCommissionEntity(commissionDailyEntity);
        }

    }

    @Override
    public AuthorizationVo authentication(ServletRequest request, String inviteOpenId, String code) {
        AuthorizationVo authorizationVo = this.requestWxAuthtication(request, code);

        //fixme 登录成功
        if (TextUtils.isEmpty(authorizationVo.getErrorMsg())) {
            Date date = new Date();
            String openId = authorizationVo.getOpenId();
            //fixme 注册用户到数据库
            insertUserInfo(date, openId, inviteOpenId);

            //fixme 实例化全局用户信息
            UsernamePasswordAuthenticationToken upToken = new UsernamePasswordAuthenticationToken(openId, openId);


            Authentication authentication = authenticationManager.authenticate(upToken);//调用loadUserByUsername--ProviderManager实现类中
            SecurityContextHolder.getContext().setAuthentication(authentication);//实例化全局Authentication对象--登录成功后会实例化UserDetails实例
            UserDetails userDetails = userDetailsService.loadUserByUsername(openId);
            String token = tokenService.generateToken(userDetails);

            authorizationVo.setToken(token);

            tokenService.storeToken(token, getAccessToken(request));//保存token到redis
            resetDaily();//重置用户的每日数据
        }
        return authorizationVo;
    }

    @Override
    public AuthorizationVo getUserInfo(ServletRequest request, String code) {

        return this.requestWxAuthtication(request, code);
    }

    /**
     * 解析出
     *
     * @param request
     * @param json
     * @return
     */
    @Override
    public JSONObject decodeUserInfo(ServletRequest request, JSONObject json) {
        JSONObject result = null;
        String encryptedData = json.getString("encryptedData");
        String iv = json.getString("iv");
        String code = json.getString("code");

        int category = json.getIntValue("category");

        AuthorizationVo wxAuthorizationVo = this.getUserInfo(request, code);
        try {
            result = AesCbcUtils.decrypt(encryptedData, wxAuthorizationVo.getSessionKey(), iv, "UTF-8");
            if (category == TomatoConstant.Authorization.WX_USER_INFO_1) {//解析用户信息
                //将当前用户的数据保存到数据表
                updateUserInfo(result);
            }


        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }


    /**
     * 受邀者注册成功后触发的消息推送
     *
     * @param inviteOpenId
     */
    private void sendMessage(String inviteOpenId, String openId) {

        if (TextUtils.isEmpty(inviteOpenId)) {
            return;
        }


        //获取邀请者邀请好友数量
        List<TomatoUserEntity> userEntityList = tomatoCouponUserService.getUserFansList(inviteOpenId);

        //获取formId
        FormIdVo formIdVo = (FormIdVo) RedisCacheUtils.getRedisCacheManager().get(RedisKeyUtils.getFormIdKey(inviteOpenId));

        if (null == formIdVo) {
            Log.e("formId invalid");
            return;
        }

        Map<String, Object> map = new HashMap();

        map.put("nickName", new StringBuilder(TomatoConstant.Common.TOMATO_USER).append(openId.substring(openId.length() - 3, openId.length())).toString());
        map.put("num", userEntityList.size());

        templateService.sendNotifyTemplate(inviteOpenId, formIdVo, map, TomatoConstant.Common.NUMBER_1);

    }

    /**
     * 将微信中获取的avatar和nickname插入当前用户表
     */
    private void updateUserInfo(JSONObject json) {
        String nickName = json.getString("nickName");
        String avatar = json.getString("avatarUrl");


        //fixme 获取用户的邀请者信息
        TomatoUserEntity tomatoUserEntity = tomatoUserService.findUserByUsername(PrincipalUtils.getUsername());
        if (null == tomatoUserEntity) {
            return;
        }
        tomatoUserEntity.setNickName(nickName);
        tomatoUserEntity.setAvatar(avatar);

        tomatoUserService.updateUserInfo(tomatoUserEntity);

    }

    /**
     * 获取微信的access_token
     *
     * @param request
     * @return
     */
    @Override
    public String getAccessToken(ServletRequest request) {

        //fixme 获取微信access_token start
        String url = new StringBuilder(configProperties.getWx_token_url()).append("&appid=").append(configProperties.getAppId())
                .append("&secret=").append(configProperties.getAppSecret()).toString();

        String response = TomatoRestUtils.get(request, url, null);
        JSONObject json = JSON.parseObject(response);
        String access_token = json.getString("access_token");
        return access_token;
    }


    private boolean insertUserInfo(Date date, String openId, String inviteOpenId) {

        TomatoUserEntity tomatoUser = tomatoUserService.findUserByUsername(openId);
        if (null == tomatoUser) {
            tomatoUser = buildTomatoUser(openId, date, inviteOpenId);
            boolean success = tomatoUserService.updateTomatoUser(tomatoUser);//--success
            if (success) {
                sendMessage(inviteOpenId, openId);//fixme 触发发送通知消息--需要知道用户的昵称数据
            }
            return success;

        }
        tomatoUser.setRookie(TomatoConstant.Common.NUMBER_0);//
        tomatoUser.setLatelyLogin(date);//设置最近一次登录
        return tomatoUserService.updateTomatoUser(tomatoUser);//更新当前的用户状态


    }


    /**
     * 注册
     *
     * @param openId
     * @return
     */
    private TomatoUserEntity buildTomatoUser(String openId, Date date, String openInviteOpenId) {
        Log.i("BCryptPasswordEncoder");
        TomatoUserEntity tomatoUser = new TomatoUserEntity();
        tomatoUser.setUsername(openId);
        tomatoUser.setPassword(openId);
        tomatoUser.setLatelyLogin(date);//最近登录时间--注册时间
        tomatoUser.setRegisterDate(date);//注册时间
        tomatoUser.setRookie(TomatoConstant.Common.NUMBER_1);//设置是新用户
        tomatoUser.setInviteUser(openInviteOpenId);

        return tomatoUser;
    }

    /**
     * 微信鉴权请求
     *
     * @param request
     * @param code
     */
    private AuthorizationVo requestWxAuthtication(ServletRequest request, String code) {

        String url = new StringBuilder(configProperties.getWx_authorization_url()).append("?appid=")
                .append(configProperties.getAppId()).append("&secret=")
                .append(configProperties.getAppSecret()).append("&js_code=")
                .append(code).append("&grant_type=")
                .append(configProperties.getGrantType()).toString();

        AuthorizationVo authorizationVo = new AuthorizationVo();//创建返回对象
        try {
            String response = TomatoRestUtils.get(request, url, null);
            JSONObject json = JSON.parseObject(response);
            if (!TextUtils.isEmpty(json.getString(OPEN_ID_KEY))) {//成功后无返回码
                String sessionKey = json.getString(SESSION_KEY);
                String openId = json.getString(OPEN_ID_KEY);

                authorizationVo.setSessionKey(sessionKey);
                authorizationVo.setOpenId(openId);

            } else {//失败信息
                authorizationVo.setErrorCode(json.getIntValue(ERROR_CODE_MESSAGE));
                authorizationVo.setErrorMsg(json.getString(ERROR_MSG_MESSAGE));
                return authorizationVo;
            }


        } catch (Exception e) {
            e.printStackTrace();
            authorizationVo.setErrorCode(ErrorCodeEnum.AUTH1003.code());
            authorizationVo.setErrorMsg(ErrorCodeEnum.AUTH1003.msg());
            return authorizationVo;
        }

        return authorizationVo;
    }
}
