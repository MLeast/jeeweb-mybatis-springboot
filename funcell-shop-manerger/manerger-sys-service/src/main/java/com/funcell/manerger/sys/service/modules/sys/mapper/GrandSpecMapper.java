package com.funcell.manerger.sys.service.modules.sys.mapper;

import com.baomidou.mybatisplus.mapper.Wrapper;
import com.baomidou.mybatisplus.plugins.pagination.Pagination;
import com.funcell.manerger.sys.service.modules.sys.entity.GrandClassify;
import com.funcell.manerger.sys.service.modules.sys.entity.GrandSpec;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 *
 * @version V1.0
 * @package com.funcell.manerger.sys.service.modules.sys.mapper
 * @title: 商品规格数据库控制层接口
 * @description: 显示商品规格数据库控制层接口
 * @date: 2018-12-03 14:41:15
 */
@Mapper
public interface GrandSpecMapper extends BaseMapper<GrandSpec> {


    /**
     * 查找商品规格列表
     * @return
     */
    List<GrandSpec> selectGrandSpecList();
}