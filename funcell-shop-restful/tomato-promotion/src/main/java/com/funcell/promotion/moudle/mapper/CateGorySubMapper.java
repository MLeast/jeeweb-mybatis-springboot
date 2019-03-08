package com.funcell.promotion.moudle.mapper;


import com.baomidou.mybatisplus.mapper.BaseMapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.funcell.promotion.moudle.entity.CateGorySubEntity;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface CateGorySubMapper extends BaseMapper<CateGorySubEntity> {

    List<CateGorySubEntity> getCategorySubList(Pagination page, @Param("optId") int optId);

    List<CateGorySubEntity> getCateGoryEntitySubList(@Param("optId") int optId);

    CateGorySubEntity getCateGoryEntity(@Param("parent_opt_id") int parent_opt_id, @Param("optId") int optId);

}
