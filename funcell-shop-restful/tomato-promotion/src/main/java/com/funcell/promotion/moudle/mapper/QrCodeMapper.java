package com.funcell.promotion.moudle.mapper;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.funcell.promotion.moudle.entity.QrCodeEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
@DS("slave_funcellShop")
public interface QrCodeMapper extends BaseMapper<QrCodeEntity> {

    List<QrCodeEntity> getAllQrList(@Param("status") int status);


}
