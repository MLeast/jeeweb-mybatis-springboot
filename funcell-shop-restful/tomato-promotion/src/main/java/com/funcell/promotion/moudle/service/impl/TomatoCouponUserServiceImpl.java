package com.funcell.promotion.moudle.service.impl;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.funcell.promotion.common.constant.TomatoConstant;
import com.funcell.promotion.common.vo.FriendsVo;
import com.funcell.promotion.common.vo.UserFansVo;
import com.funcell.promotion.moudle.entity.HardwareEntity;
import com.funcell.promotion.moudle.entity.OrderEntity;
import com.funcell.promotion.moudle.entity.RateEntity;
import com.funcell.promotion.moudle.service.IHardWareService;
import com.funcell.promotion.moudle.service.IOrderService;
import com.funcell.promotion.moudle.service.IRateService;
import com.funcell.promotion.moudle.service.ITomatoCouponUserService;
import com.funcell.promotion.security.user.*;
import com.funcell.promotion.utils.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service("tomatoCouponUserService")
@Transactional
public class TomatoCouponUserServiceImpl extends ServiceImpl<TomatoUserMapper, TomatoUserEntity> implements ITomatoCouponUserService {

    @Autowired
    private ITomatoUserService tomatoUserService;
    @Autowired
    private IHardWareService handWareService;
    @Autowired
    private IOrderService orderService;

    @Autowired
    private IRateService rateService;

    @Override
    public int updateExitTime() {
        TomatoUserEntity tomatoUser = tomatoUserService.findUserByUsername(PrincipalUtils.getUsername());
        if (null == tomatoUser) {
            return 0;
        }
        tomatoUser.setExitTime(new Date());
        return insertOrUpdate(tomatoUser) ? 1 : 0;
    }

    /**
     * 获取所有用户--消息推送使用
     *
     * @return
     */
    @Override
    public List<TomatoUserEntity> getAllUser() {
        return baseMapper.getAllUser();
    }

    /**
     * 上传微信用户信息
     *
     * @return
     */
    @Override
    public int updateUserInfo(JSONObject json) {
        boolean isUser = false, isHardware = false;

        String gender = json.getString("gender");//用户性别
        String address = json.getString("address");//用户地址

        String brand = json.getString("brand");//设备品牌

        String model = json.getString("model");//设备型号
        String version = json.getString("version");//微信版本
        String system = json.getString("system");//操作系统版本
        String platform = json.getString("platform");//客户端平台


        TomatoUserEntity tomatoUser = tomatoUserService.findUserByUsername(PrincipalUtils.getUsername());
        if (null != tomatoUser) {
            tomatoUser.setGender(gender);
            tomatoUser.setAddress(address);
            isUser = insertOrUpdate(tomatoUser);

            //硬件信息
            HardwareEntity hardwareEntity = handWareService.getHardware();
            if (null == hardwareEntity) {
                hardwareEntity = new HardwareEntity();
                hardwareEntity.setUsername(PrincipalUtils.getUsername());
                hardwareEntity.setBrand(brand);
                hardwareEntity.setModel(model);
                hardwareEntity.setVersion(version);
                hardwareEntity.setSystem(system);
                hardwareEntity.setPlatform(platform);
                isHardware = handWareService.insertHardware(hardwareEntity);
            } else {
                isHardware = true;
            }

        }


        return (isHardware && isUser) ? 1 : 0;
    }

    private TomatoUserEntity getUserInfo(TomatoUserEntity tomatoUserEntity) {

        if (null == tomatoUserEntity.getNickName() || null == tomatoUserEntity.getAvatar()) {
            TomatoUserEntity tomatoUser = new TomatoUserEntity();
            tomatoUser.setNickName("");
            tomatoUser.setAvatar("");
            tomatoUser.setUsername(tomatoUserEntity.getUsername());
            return tomatoUser;
        }

        return tomatoUserEntity;

    }

    /**
     * 获取当前登录用户的粉丝(邀请好友)
     *
     * @param page
     * @return
     */
    @Override
    public Page<UserFansVo> getUserFansList(Page<UserFansVo> page) {

        List<TomatoUserEntity> list = baseMapper.getUserFansList(page, PrincipalUtils.getUsername());
        List<UserFansVo> retList = new ArrayList<>();
        //fixme 需要获取对应的粉丝状态/未下单用户/离开N天/赚取佣金额度
        if (list.size() > TomatoConstant.Common.NUMBER_0) {
            for (TomatoUserEntity tomatoUserEntity : list) {
                UserFansVo userFansVo = new UserFansVo();
                userFansVo.setUserInfo(getUserInfo(tomatoUserEntity));

                Date exitDate = tomatoUserEntity.getExitTime();

                int exitDay = 0;
                if (null == exitDate) {
                    exitDay = DateUtils.isNotOneDay(tomatoUserEntity.getLatelyLogin(), new Date());
                } else {
                    exitDay = DateUtils.isNotOneDay(tomatoUserEntity.getExitTime(), new Date());
                }

                //fixme 用户离开时间超过5天
                if (exitDay > TomatoConstant.Common.NUMBER_5) {
                    userFansVo.setExitDay(exitDay);
                }
                //FIXME 查询订单表

                List<OrderEntity> orderList = orderService.getOrderByUserName(tomatoUserEntity.getUsername());
                //fixme 未下单用户
                if (orderList.size() == TomatoConstant.Common.NUMBER_0) {
                    userFansVo.setStatus(TomatoConstant.Common.NUMBER_1);
                } else {//fixme 已下单用户，计算收益
                    userFansVo.setMoney(getMoney(tomatoUserEntity, list));
                }

                retList.add(userFansVo);
            }

        }

        return page.setRecords(retList);
    }

    @Override
    public List<TomatoUserEntity> getUserFansList(String username) {
        return baseMapper.getUserFansListSize(username);
    }

    @Override
    public FriendsVo getFriendsVo() {

        List<TomatoUserEntity> list = baseMapper.getUserFansListSize(PrincipalUtils.getUsername());
        FriendsVo friendsVo = new FriendsVo();
        if (list.size() == TomatoConstant.Common.NUMBER_0) {
            friendsVo.setToday_fans(TomatoConstant.Common.NUMBER_0);
            friendsVo.setTotal_fans(TomatoConstant.Common.NUMBER_0);
            return friendsVo;
        }

        Date now = new Date();
        int today_fans = 0;
        for (TomatoUserEntity tomatoUserEntity : list) {

            if (DateUtils.isNotOneDay(tomatoUserEntity.getRegisterDate(), now) == TomatoConstant.Common.NUMBER_0) {

                today_fans += 1;
            }

        }

        friendsVo.setToday_fans(today_fans);
        friendsVo.setTotal_fans(list.size());
        return friendsVo;
    }


    private BigDecimal getMoney(TomatoUserEntity tomatoUserEntity, List<TomatoUserEntity> list) {

        List<OrderEntity> orderList = orderService.getOrderComplete(tomatoUserEntity.getUsername());
        double money = 0;


        RateEntity rateEntity = rateService.getRateEntity();

        for (OrderEntity orderEntity : orderList) {


            if (orderEntity.getFirst_order() == TomatoConstant.Common.NUMBER_1) {//第一单
                if (list.size() >= rateEntity.getInvite_user_promote()) {//fixme 因判断用户邀请了多少人，是否满足用户达到20的佣金奖励提升
                    money += BigDecimalUtils.mul(orderEntity.getPromotion_amount().doubleValue(), rateEntity.getInvite_first_buy_rate().doubleValue() / 100).doubleValue(); //佣金金额
                } else {
                    money += BigDecimalUtils.mul(orderEntity.getPromotion_amount().doubleValue(), rateEntity.getFirst_buy_rate().doubleValue() / 100).doubleValue(); //佣金金额
                }


            } else {
                money += BigDecimalUtils.mul(orderEntity.getPromotion_amount().doubleValue(), rateEntity.getNormal_buy_rate().doubleValue() / 100).doubleValue(); //佣金金额
                Log.i("for money:" + money);
            }
        }


        //fixme 获取配置信息

        return BigDecimalUtils.mul(new BigDecimal(money).setScale(2, BigDecimal.ROUND_HALF_UP).doubleValue(), TomatoConstant.Common.NUMBER_1);

    }

}
