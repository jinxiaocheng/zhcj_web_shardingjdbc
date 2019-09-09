package com.escst.org.service;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.tree.TreeEntity;
import com.escst.organization.entity.OrgEntity;
import com.escst.organization.service.OrgService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class OrgServiceImplTest extends AbstractJUnit4SpringContextTests{

	@Autowired
	private OrgService orgService;
	
	@Test
	public void test01_addOrg(){
		OrgEntity orgEntity = new OrgEntity();
		orgEntity.setParentId("");
		orgEntity.setName("test");
		orgEntity.setLevel(2);
		orgEntity.setSysCode("ceshi");
		orgEntity.setSortNum(2);
		orgEntity.setStatus(0);
//		orgService.addOrg(orgEntity);
	}
	
	@Test
	public void test02_delOrg(){
		orgService.delOrg("7f6327e4612944e4baa8af36108eb328");
	}
	
	@Test
	public void test03_queryByParentId(){
		List<OrgEntity> list = orgService.queryByParentId("7d25e9e3f71711e6a0d9002590f074f8");
		System.out.println(JSONObject.toJSONString(list));
	}
	
//	@Test
//	public void test04_udpateOrg(){
//		OrgEntity orgEntity = new OrgEntity();
//		orgEntity.setId("64816a1924e3484b8869866b2b326499");
//		orgEntity.setName("testChange");
//		orgService.udpateOrg(orgEntity);
//	}
	
	@Test
	public void test06_getNextSysCode() {
		String parentId = "c18c1656727011e79386b82a72dd25a2";
		String str = orgService.getNextSysCode(parentId);
		System.out.println(str);
	}
	
	@Test
	public void test07_initSysCode() {
		orgService.initSysCode();
		System.out.println("initSysCode success");
	}
}
