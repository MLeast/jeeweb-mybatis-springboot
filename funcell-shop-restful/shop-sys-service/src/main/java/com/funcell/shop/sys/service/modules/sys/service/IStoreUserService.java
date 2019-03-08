package com.funcell.shop.sys.service.modules.sys.service;

import com.funcell.manerger.sys.common.mybatis.service.ICommonService;
import com.funcell.shop.sys.service.modules.sys.entity.GoldLogEntity;
import com.funcell.shop.sys.service.modules.sys.entity.StoreUserEntity;

import java.math.BigDecimal;
import java.util.List;

/**
 * @version V1.0
 * @package com.funcell.shop.sys.service.modules.sys.service
 * @title: 用户表服务接口
 * @description: 用户表服务接口
 * @author: huangjian
 * @date: 2018-11-23 15:48:46
 */
public interface IStoreUserService extends ICommonService<StoreUserEntity> {
    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    StoreUserEntity findByUsername(String username);


    String login(String username, String password);

    String register(StoreUserEntity user);

    String refreshToken(String oldToken);


    int updateAward(int step);

    int updateGold(BigDecimal gold);

    int updateUserInfo(String nickName, String avatar);

    StoreUserEntity getUserInfo();

    StoreUserEntity getUserInfo(String username);

    List<GoldLogEntity> getGoldList();

    List<StoreUserEntity> getFriendList();

}