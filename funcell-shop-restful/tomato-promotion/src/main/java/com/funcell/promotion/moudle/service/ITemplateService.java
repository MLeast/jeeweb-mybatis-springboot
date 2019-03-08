package com.funcell.promotion.moudle.service;

import com.funcell.promotion.common.vo.FormIdVo;

import java.util.Map;


public interface ITemplateService {
    void sendNotifyTemplate(String openId, FormIdVo formIdVo, Map<String,Object> map, int type);


    int saveFormId(String formId);
}
