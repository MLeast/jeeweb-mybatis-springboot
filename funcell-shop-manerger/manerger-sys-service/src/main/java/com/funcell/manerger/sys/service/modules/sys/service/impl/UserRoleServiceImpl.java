package com.funcell.manerger.sys.service.modules.sys.service.impl;

import com.funcell.manerger.sys.common.mybatis.service.impl.CommonServiceImpl;
import com.funcell.manerger.sys.service.modules.sys.entity.UserRole;
import com.funcell.manerger.sys.service.modules.sys.mapper.UserRoleMapper;
import com.funcell.manerger.sys.service.modules.sys.service.IUserRoleService;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Transactional
@Service("userRoleService")
public class UserRoleServiceImpl extends CommonServiceImpl<UserRoleMapper,UserRole> implements IUserRoleService {

}
