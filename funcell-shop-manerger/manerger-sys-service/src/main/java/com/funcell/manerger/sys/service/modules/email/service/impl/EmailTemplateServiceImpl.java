package com.funcell.manerger.sys.service.modules.email.service.impl;


import com.funcell.manerger.sys.common.mybatis.service.impl.CommonServiceImpl;
import com.funcell.manerger.sys.service.modules.email.entity.EmailTemplate;
import com.funcell.manerger.sys.service.modules.email.mapper.EmailTemplateMapper;
import com.funcell.manerger.sys.service.modules.email.service.IEmailTemplateService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
* @description: 邮件模板服务实现
*/
@Transactional
@Service("emailtemplateService")
public class EmailTemplateServiceImpl  extends CommonServiceImpl<EmailTemplateMapper, EmailTemplate> implements IEmailTemplateService {

}