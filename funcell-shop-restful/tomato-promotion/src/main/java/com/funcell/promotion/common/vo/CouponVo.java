package com.funcell.promotion.common.vo;

import com.funcell.promotion.moudle.entity.CouponEntity;
import lombok.Data;

import java.util.List;

@Data
public class CouponVo extends BaseTomatoVo {
    List<CouponEntity> list;
    long total;


}
