package com.escst.environment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.vo.PageVo;
import com.escst.environment.service.EnvironmentService;
import com.escst.equipment.vo.AcquisitionDataQueryVO;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月17日 下午6:25:27
 */

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", 
									"classpath:/spring/spring-mvc.xml" })
public class EnvironmentServiceTest {

	@Autowired
	private EnvironmentService service;

	@Test
	public void test01_queryAuthEquipmentList() {
		AcquisitionDataQueryVO queryVO = new AcquisitionDataQueryVO();
		queryVO.setEquipmentId("e7e280e56e974f82b1558d89d4c025cf");
		queryVO.setStartDate("2017-08-12");
		queryVO.setEndDate("2017-08-12");
		PageVo rst = service.queryRealtimeListPage(queryVO);
		System.out.println(JSONObject.toJSONString(rst));
	}
	
}
