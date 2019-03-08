package com.funcell.promotion.moudle.mapper;

import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.funcell.promotion.moudle.entity.HardwareEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface HardwareMapper extends BaseMapper<HardwareEntity> {

    HardwareEntity getHandware(@Param("username") String username);
}
