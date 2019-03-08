package com.funcell.manerger.sys.service.modules.email.service.impl;

import com.funcell.manerger.sys.common.mybatis.service.impl.CommonServiceImpl;
import com.funcell.manerger.sys.service.modules.email.entity.EmailSendLog;
import com.funcell.manerger.sys.service.modules.email.mapper.EmailSendLogMapper;
import com.funcell.manerger.sys.service.modules.email.service.IEmailSendLogService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
* @description: 邮件发送日志服务实现
*/
@Transactional
@Service("emailsendlogService")
public class EmailSendLogServiceImpl  extends CommonServiceImpl<EmailSendLogMapper, EmailSendLog> implements IEmailSendLogService {

}