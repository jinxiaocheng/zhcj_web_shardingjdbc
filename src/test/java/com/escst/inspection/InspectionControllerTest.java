package com.escst.inspection;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.alibaba.fastjson.JSONObject;
import com.escst.ControllerUtil;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月26日 下午3:52:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml"
								   ,"classpath:/spring/spring-mvc.xml"
								   ,"classpath:/spring/spring-shiro.xml"})
public class InspectionControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mvc;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
		
		//TODO zhouwei 2017-08-26 junit测试中要整合shiro认证框架
	}

	@Test
	public void test01_queryQualityInspectionMonthQtyList() {
		String url = "/inspection/quality/queryInspectionMonthQtyList";
		ControllerUtil.proPost(mvc, url, JSONObject.toJSONString(new JSONObject()));
	}

	@Test
	public void test02_querySafetyInspectionMonthQtyList() {
		String url = "/inspection/safety/queryInspectionMonthQtyList";
		ControllerUtil.proPost(mvc, url, JSONObject.toJSONString(new JSONObject()));
	}

}
