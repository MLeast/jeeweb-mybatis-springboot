package com.funcell.manerger.sys.service.modules.sys.service;

import com.funcell.manerger.sys.common.mybatis.service.ICommonService;
import com.funcell.manerger.sys.service.modules.sys.entity.StoreUser;

public interface IStoreUserService extends ICommonService<StoreUser> {
    /**
     * 根据用户名查找用户
     *
     * @param username
     * @return
     */
    public StoreUser findByUsername(String username);
}
