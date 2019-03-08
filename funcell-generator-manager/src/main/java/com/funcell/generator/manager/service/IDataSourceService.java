package com.funcell.generator.manager.service;

import com.funcell.generator.manager.common.dao.IDbHelper;
import com.funcell.generator.manager.entity.DataSource;
import com.funcell.manerger.sys.common.mybatis.service.ICommonService;


public interface IDataSourceService extends ICommonService<DataSource> {
    /**
     * 获取Hepler
     * @param datasourid
     * @return
     */
    IDbHelper getDbHelper(String datasourid);

    void testConnect(DataSource dataSource);

}

