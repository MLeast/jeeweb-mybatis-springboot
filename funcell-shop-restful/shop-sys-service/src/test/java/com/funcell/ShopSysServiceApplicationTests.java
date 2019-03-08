package com.funcell;

import com.funcell.shop.sys.service.modules.sys.entity.StoreRole;
import com.funcell.shop.sys.service.modules.sys.service.IStoreRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ShopSysServiceApplicationTests {

	@Autowired
	private IStoreRoleService storeRoleService;

	@Test
	public void contextLoads() {
//		StoreRole storeRole1 = new StoreRole();
//		storeRole1.setName("普通用户");
//		storeRole1.setCode("normal");
//		storeRole1.setIsSys("1");
//		storeRole1.setUsable("1");
//		storeRole1.setDelFlag("0");
//		storeRole1.setRoles("ROLE_USER");
//		storeRoleService.insert(storeRole1);
//
//		StoreRole storeRole2 = new StoreRole();
//		storeRole2.setName("系统管理员");
//		storeRole2.setCode("admin");
//		storeRole2.setIsSys("1");
//		storeRole2.setUsable("1");
//		storeRole2.setDelFlag("0");
//		storeRole2.setRoles("ROLE_ADMIN");
//		storeRoleService.insert(storeRole2);
	}

}
