package com.funcell.promotion.moudle.service.impl;

import com.baomidou.mybatisplus.service.impl.ServiceImpl;
import com.funcell.promotion.moudle.entity.HardwareEntity;
import com.funcell.promotion.moudle.mapper.HardwareMapper;
import com.funcell.promotion.moudle.service.IHardWareService;
import com.funcell.promotion.utils.PrincipalUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service("hardwareService")
@Transactional
public class HardwareServiceImpl extends ServiceImpl<HardwareMapper, HardwareEntity> implements IHardWareService {
    @Override
    public HardwareEntity getHardware() {
        return baseMapper.getHandware(PrincipalUtils.getUsername());
    }

    @Override
    public boolean insertHardware(HardwareEntity hardwareEntity) {
        return insertOrUpdate(hardwareEntity);
    }

}
