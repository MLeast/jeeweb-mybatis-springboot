package com.funcell.generator.manager.service;

import com.funcell.generator.manager.entity.TemplateScheme;
import com.funcell.manerger.sys.common.base.http.Response;
import com.funcell.manerger.sys.common.mybatis.service.ICommonService;

import java.io.Serializable;


public interface ITemplateSchemeService extends ICommonService<TemplateScheme> {
    /**
     * 复制
     * @param id
     * @return
     */
    boolean copy(Serializable id);

    /**
     * 复制
     * @param id
     * @return
     */
    Response export(Serializable id);

    /**
     * 复制
     * @param json
     * @return
     */
    boolean loadImport(String json);
}

