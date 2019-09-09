package com.escst.construction;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.vo.QtyVO;
import com.escst.construction.service.ConstructionService;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月25日 下午2:27:38
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", 
									"classpath:/spring/spring-mvc.xml" })
public class ConstructionServiceTest {

	@Autowired
	private ConstructionService service;

	@Test
	public void test01_queryAuthAreaConstructionQty() {
		String userId = "";
		List<QtyVO> list = service.queryAuthAreaConstructionQty(userId);
		System.out.println(JSONObject.toJSONString(list));
	}

	@Test
	public void test02() {
		String constructionId = "d640f8e459424cda99ac4be16580f5f3";
		String path = service.queryFloorPlanById(constructionId);
		System.out.println(path);
	}
}
