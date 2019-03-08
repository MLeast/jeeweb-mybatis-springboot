package com.funcell.manerger.sys.service.modules.sys.service.impl;

import com.funcell.manerger.sys.common.mybatis.service.impl.CommonServiceImpl;
import com.funcell.manerger.sys.service.modules.sys.entity.RoleMenu;
import com.funcell.manerger.sys.service.modules.sys.mapper.RoleMenuMapper;
import com.funcell.manerger.sys.service.modules.sys.service.IRoleMenuService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("roleMenuService")
public class RoleMenuServiceImpl extends CommonServiceImpl<RoleMenuMapper,RoleMenu> implements IRoleMenuService {

}
