package com.escst.menu.service;

import com.alibaba.fastjson.JSONObject;
import com.escst.menu.entity.MenuEntity;
import com.escst.menu.vo.MenuVO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class MenuServiceImplTest extends AbstractJUnit4SpringContextTests {

	@Autowired
	private MenuService menuService;

//	@Test
//	public void test01_queryMenuById() {
//		MenuEntity menuEntity = menuService.queryMenuById("a6a8faa57554486c982014547679731a");
//		System.out.println(menuEntity.getName());
//	}

	@Test
	public void test02_queryByParentId() {
		List<MenuEntity> list = menuService.queryByParentId("6fd8c7bdb3984e64954d7172e7775a68");
		for (MenuEntity entity : list) {
			System.out.println(entity.getId() + "--" + entity.getName());
		}
	}

	@Test
	public void test03_addMenu() {
		MenuEntity menuEntity = new MenuEntity();
		menuEntity.setName("test");
		menuEntity.setUrl("1");
		menuEntity.setParentId("0");
		menuEntity.setIcon("fa icon-blue");
		menuEntity.setFlag(0);
		menuEntity.setSortNum(1);
		menuService.addMenu(menuEntity);
	}

	@Test
	public void test04_addMenu() {
		MenuEntity menuEntity = new MenuEntity();
		for (int i = 0; i < 5; i++) {
			menuEntity.setName("test" + i);
			menuEntity.setUrl("1" + i);
			menuEntity.setParentId("2cda23b0d1364b60ae48f03b3eccc7f9");
			menuEntity.setIcon("fa icon-blue");
			menuEntity.setFlag(0);
			menuEntity.setSortNum(1);
			menuService.addMenu(menuEntity);
		}
	}


	
	@Test
	public void test06_updateMenu(){
		MenuEntity menuEntity = new MenuEntity();
		menuEntity.setId("31128256a50b4d41ad065785c9192212");
		menuEntity.setName("菜单管理");
		menuService.updateMenu(menuEntity);
	}
	
	@Test
	public void test07_listMenu() {
		MenuVO menuVO = new MenuVO();
		menuVO.setUserId("28a15d1d43c64be6a2d88b6a4786dd9e");
		List<MenuEntity> list = menuService.listMenu(menuVO);
		System.out.println(JSONObject.toJSONString(list));
	}

	@Test
	public void test08_listAuthAllMenu() {
		MenuVO menuVO = new MenuVO();
		menuVO.setUserId("6d6d5eb88487412cadfc493ee6bfd3e0");
		menuVO.setFlag(1);
		List<MenuEntity> list = menuService.listAuthAllMenu(menuVO);
		System.out.println(JSONObject.toJSONString(list));
	}
}
