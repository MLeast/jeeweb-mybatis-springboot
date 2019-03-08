package com.funcell.promotion.moudle.entity;

import com.baomidou.mybatisplus.annotations.TableField;
import com.baomidou.mybatisplus.annotations.TableId;
import com.baomidou.mybatisplus.annotations.TableName;
import com.baomidou.mybatisplus.enums.IdType;
import com.funcell.manerger.sys.common.base.mvc.entity.AbstractEntity;
import lombok.Data;

import java.util.Date;

@Data
@TableName("tomato_promotion_coupon")
public class CouponEntity extends AbstractEntity<String> {
    @TableId(value = "id", type = IdType.ID_WORKER_STR)
    private String id;


    @TableField(value = "user_name")
    private String username;

    @TableField(value = "coupon_id")
    private String couponId;
    @TableField(value = "receive_time")
    private Date receiveTime;

}
