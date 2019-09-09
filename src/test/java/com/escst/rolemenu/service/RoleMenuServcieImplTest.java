package com.escst.rolemenu.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.escst.menu.entity.MenuEntity;
import com.escst.menu.service.MenuService;
import com.escst.roleMenu.service.RoleMenuService;

/**
 * @desc
 * @author niejing
 * @date 2017年7月28日 下午3:33:34
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class RoleMenuServcieImplTest {

	@Autowired
	public RoleMenuService service;

	@Autowired
	public MenuService menuServce;

	/**
	 * 
	 * @desc 平台44条 app37条  
	 * @author niejing
	 * @date 2017年7月28日 下午4:46:23
	 */
	@Test
	public void testInsert() {
//		List<MenuEntity> list = menuServce.queryByParentId("1");
		List<MenuEntity> list = menuServce.queryAppByParentId("2");
		try {
			service.batchInsert(list);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
