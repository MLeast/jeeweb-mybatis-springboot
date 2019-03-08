package com.funcell.promotion.common.vo;

import lombok.Data;

@Data
public class ProduceQrCodeVo extends BaseTomatoVo {

    String qrCodeUrl;
    int errCode;
    String errMsg;
}
