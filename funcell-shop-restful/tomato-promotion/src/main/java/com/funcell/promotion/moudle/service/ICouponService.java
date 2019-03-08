package com.funcell.promotion.moudle.service;

import com.baomidou.mybatisplus.plugins.Page;
import com.funcell.promotion.moudle.entity.CouponEntity;

/**
 * 优惠劵
 */
public interface ICouponService {

    int insertCoupon(String couponId);

    Page<CouponEntity> getCouponList(Page<CouponEntity> page);

    CouponEntity selectCoupon(String couponId);
}
