package com.funcell.promotion.common.vo;

import lombok.Data;

import java.util.Date;

@Data
public class AccessTokenVo extends BaseTomatoVo {
    String accessToken;
    Date date;

}
