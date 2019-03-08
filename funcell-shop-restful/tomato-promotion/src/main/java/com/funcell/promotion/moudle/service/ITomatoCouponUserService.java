package com.funcell.promotion.moudle.service;


import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.plugins.Page;
import com.funcell.promotion.common.vo.FriendsVo;
import com.funcell.promotion.common.vo.UserFansVo;
import com.funcell.promotion.security.user.TomatoUserEntity;

import java.util.List;

public interface ITomatoCouponUserService {

    int updateExitTime();

    List<TomatoUserEntity> getAllUser();

    int updateUserInfo(JSONObject json);

    Page<UserFansVo> getUserFansList(Page<UserFansVo> page);//查询配置的分类列表

    List<TomatoUserEntity> getUserFansList(String username);//获取用户的所有邀请者

    FriendsVo getFriendsVo();
}
