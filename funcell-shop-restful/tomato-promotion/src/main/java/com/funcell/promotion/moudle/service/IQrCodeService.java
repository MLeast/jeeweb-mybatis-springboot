package com.funcell.promotion.moudle.service;

import com.alibaba.fastjson.JSONObject;
import com.funcell.promotion.common.vo.ProduceQrCodeVo;
import com.funcell.promotion.moudle.entity.QrCodeEntity;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

public interface IQrCodeService {


    List<QrCodeEntity> getAllList();


    QrCodeEntity showQrCode();

    ProduceQrCodeVo createProductQrCode(HttpServletRequest request, JSONObject json);


}
