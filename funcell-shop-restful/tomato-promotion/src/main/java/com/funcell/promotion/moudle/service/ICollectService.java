package com.funcell.promotion.moudle.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.funcell.promotion.moudle.entity.CollectEntity;

/**
 * 优惠劵
 */
public interface ICollectService {

    int addCoupon(String couponId);

    Page<CollectEntity> getCollectList(Page<CollectEntity> page);

    CollectEntity selectCollect(String couponId);

    int cancelCollect(String couponId);

    boolean isCollect(String couponId);
}
