package com.funcell.promotion.common.vo;

import com.funcell.promotion.moudle.entity.QrCodeEntity;
import lombok.Data;

import java.util.List;

@Data
public class QrCodeVo extends BaseTomatoVo {
    List<QrCodeEntity> list;
}
