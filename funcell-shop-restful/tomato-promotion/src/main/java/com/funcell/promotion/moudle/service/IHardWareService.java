package com.funcell.promotion.moudle.service;

import com.funcell.promotion.moudle.entity.HardwareEntity;

public interface IHardWareService {


    HardwareEntity getHardware();

    boolean insertHardware(HardwareEntity hardwareEntity);
}
