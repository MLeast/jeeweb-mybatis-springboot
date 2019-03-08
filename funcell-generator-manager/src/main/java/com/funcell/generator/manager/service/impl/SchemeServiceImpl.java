package com.funcell.generator.manager.service.impl;

import com.funcell.generator.manager.entity.Scheme;
import com.funcell.generator.manager.mapper.SchemeMapper;
import com.funcell.generator.manager.service.ISchemeService;
import com.funcell.manerger.sys.common.mybatis.service.impl.CommonServiceImpl;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("schemeService")
public class SchemeServiceImpl  extends CommonServiceImpl<SchemeMapper,Scheme> implements ISchemeService {

}
