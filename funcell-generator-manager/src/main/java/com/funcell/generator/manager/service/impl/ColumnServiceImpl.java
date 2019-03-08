package com.funcell.generator.manager.service.impl;

import com.funcell.generator.manager.entity.Column;
import com.funcell.generator.manager.mapper.ColumnMapper;
import com.funcell.generator.manager.service.IColumnService;
import com.baomidou.mybatisplus.mapper.EntityWrapper;
import com.funcell.manerger.sys.common.mybatis.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Transactional
@Service("columnService")
public class ColumnServiceImpl extends CommonServiceImpl<ColumnMapper, Column> implements IColumnService {

	@Override
	public List<Column> selectListByTableId(String tableId) {
		EntityWrapper<Column> columnWrapper = new EntityWrapper<>();
		columnWrapper.eq("table_id", tableId);
		columnWrapper.orderBy("sort");
		List<Column> oldColumnList = selectList(columnWrapper);
		return oldColumnList;
	}

}
