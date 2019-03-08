package com.funcell.promotion.moudle.service.impl;

import com.funcell.promotion.common.constant.TomatoConstant;
import com.funcell.promotion.common.vo.FormIdVo;
import com.funcell.promotion.moudle.service.ITemplateService;
import com.funcell.promotion.security.authorization.AuthenticationConfigProperties;
import com.funcell.promotion.security.authorization.IAuthenticationService;
import com.funcell.promotion.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.List;

/**
 * 发送签到预约模板
 */
@Service("templateService")
@Transactional
public class TemplateServiceImpl implements ITemplateService {

    @Autowired
    private IAuthenticationService authenticationService;

    @Autowired
    private AuthenticationConfigProperties authenticationConfigProperties;

    @Autowired
    private HttpServletRequest request;


    private String getFormId(String username, FormIdVo formIdVo) {

        String formId;
        List<String> formIdList = formIdVo.getList();

        //fixme 从首个元素开始取
        formId = formIdList.get(TomatoConstant.Common.NUMBER_0);
        formIdList.remove(TomatoConstant.Common.NUMBER_0);
        formIdVo.setList(formIdList);

        RedisCacheUtils.getRedisCacheManager().set(RedisKeyUtils.getFormIdKey(username), formIdVo, TomatoConstant.Common.EXPIRE_FORMID_TIME_REDIS);

        return formId;
    }


    /**
     * 发送消息
     *
     * @param openId
     * @param formIdVo
     */
    @Override
    public void sendNotifyTemplate(String openId, FormIdVo formIdVo, Map<String, Object> map, int type) {


        if (formIdVo.getList().size() == TomatoConstant.Common.NUMBER_0) {
            return;
        }

        String access_token = getToken(request);
        String url = new StringBuilder(authenticationConfigProperties.getWx_tamplate_url()).append(access_token).toString();
        Log.i("url:" + url);
        Map<String, Object> dateMap = new HashMap<>();

        if (type == TomatoConstant.Common.NUMBER_0) { //用户召回-0
            dateMap.put("template_id", authenticationConfigProperties.getNotifyId());
            dateMap.put("data", MessageUtils.getNotifyMessageTemplate());//fixme设置下发消息模板的样式
        } else if (type == TomatoConstant.Common.NUMBER_1) {//邀请好友成功消息-1
            dateMap.put("template_id", authenticationConfigProperties.getInviteId());
            dateMap.put("page", "/pages/home");//fixme 邀请好友跳转首页
            dateMap.put("data", MessageUtils.getInviteMessageTemplate(map));//fixme设置下发消息模板的样式
        } else if (type == TomatoConstant.Common.NUMBER_2) {//好友购买收益消息-2

            dateMap.put("template_id", authenticationConfigProperties.getIncomeId());
            dateMap.put("data", MessageUtils.getInvitePriceMessageTemplate(map));//fixme设置下发消息模板的样式
        }

        dateMap.put("touser", openId);
        dateMap.put("access_token", access_token);
        dateMap.put("form_id", getFormId(openId, formIdVo));

        dateMap.put("emphasis_keyword", "keyword1.DATA");//消息1大写
        String response = TomatoRestUtils.post(request, url, dateMap);
        Log.i("response:" + response);
    }

    /**
     * 保存formId--存储一个集合
     *
     * @param formId
     */
    @Override
    public int saveFormId(String formId) {

        FormIdVo formIdVo = (FormIdVo) RedisCacheUtils.getRedisCacheManager().get(RedisKeyUtils.getFormIdKey(PrincipalUtils.getUsername()));
        if (null == formIdVo) {
            formIdVo = new FormIdVo();
            List<String> list = new ArrayList();
            list.add(formId);
            formIdVo.setList(list);
            RedisCacheUtils.getRedisCacheManager().set(RedisKeyUtils.getFormIdKey(PrincipalUtils.getUsername()), formIdVo, TomatoConstant.Common.EXPIRE_FORMID_TIME_REDIS);
            return TomatoConstant.Common.NUMBER_1;
        }

        List<String> formList = formIdVo.getList();
        formList.add(formId);
        formIdVo.setList(formList);

        RedisCacheUtils.getRedisCacheManager().set(RedisKeyUtils.getFormIdKey(PrincipalUtils.getUsername()), formIdVo, TomatoConstant.Common.EXPIRE_FORMID_TIME_REDIS);
        return TomatoConstant.Common.NUMBER_1;


    }


    private String getToken(HttpServletRequest request) {
        return authenticationService.getAccessToken(request);
    }
}
