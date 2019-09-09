package com.escst.thirdPart;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.tree.TreeEntity;
import com.escst.thirdPart.service.ToolboxService;

/**
 * @desc 
 * @author niejing
 * @date 2018年7月31日 下午3:24:02
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class ToolboxServiceTest {
	@Autowired
	ToolboxService service;
	
	@Test
	public void queryTree_test(){
		List<TreeEntity> list=service.queryDepartTree();
		System.out.println("result>>>>>>>>>>>>>"+JSONObject.toJSONString(list));
	}
	
	@Test
	public void batchInsertCode(){
		service.batchInsertCode();
	}
	
	@Test
	public void batchInsert(){
		service.batchInsert();
	}
	
}
