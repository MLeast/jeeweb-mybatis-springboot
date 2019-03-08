package com.funcell.generator.manager.service;

import com.funcell.generator.manager.entity.Template;
import com.funcell.manerger.sys.common.mybatis.service.ICommonService;

import java.io.Serializable;


public interface ITemplateService extends ICommonService<Template> {

    /**
     * 复制
     * @param template
     * @return
     */
    boolean inlineEdit(Template template);

    /**
     *  模版测试
     * @param template
     * @return
     */
    void test(Template template);
}

