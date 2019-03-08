package com.funcell.manerger.sys.service.modules.sys.service;

import java.util.List;

import com.funcell.manerger.sys.common.mybatis.service.ICommonService;
import com.funcell.manerger.sys.service.modules.sys.entity.Role;

/**
 */
public interface IRoleService extends ICommonService<Role> {
	/**
	 * 通过用户ID查找角色
	 */
	public List<Role> findListByUserId(String userid);
}
