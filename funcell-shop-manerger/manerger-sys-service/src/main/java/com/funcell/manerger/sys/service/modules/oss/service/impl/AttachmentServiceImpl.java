package com.funcell.manerger.sys.service.modules.oss.service.impl;

import com.funcell.manerger.sys.common.mybatis.service.impl.CommonServiceImpl;
import com.funcell.manerger.sys.service.modules.oss.entity.Attachment;
import com.funcell.manerger.sys.service.modules.oss.mapper.AttachmentMapper;
import com.funcell.manerger.sys.service.modules.oss.service.IAttachmentService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


/**
* @description: 附件管理服务实现
*/
@Transactional
@Service("attachmentService")
public class AttachmentServiceImpl  extends CommonServiceImpl<AttachmentMapper,Attachment> implements IAttachmentService {

}