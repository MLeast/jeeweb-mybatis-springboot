package com.funcell.generator.manager.mapper;

import com.funcell.generator.manager.entity.Table;
import com.baomidou.mybatisplus.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface TableMapper extends BaseMapper<Table> {
	
	/**
	 * 
	 * @title: findSubTables   
	 * @description:通过表名获得子表信息
	 * @param tablename
	 * @return      
	 * @return: List<Role>
	 */
	List<Table> findSubTables(String tablename);
}