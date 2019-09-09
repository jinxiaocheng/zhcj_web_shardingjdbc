package com.escst.equipment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.vo.PageVo;
import com.escst.equipment.service.EquipmentInspectionService;
import com.escst.equipment.vo.InspectionQueryVO;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月23日 下午2:36:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", 
									"classpath:/spring/spring-mvc.xml" })
public class EquipmentInspectionServiceTest {

	@Autowired
	private EquipmentInspectionService service;
	
	@Test
	public void test01_queryEquipmentInspectionList() {
		InspectionQueryVO queryVO = new InspectionQueryVO();
		queryVO.setUserId("c18c1656727011e79386b82a72dd25a2");
		queryVO.setConstructionId("402864e35841e6f601584cd30ac10175");
		PageVo pageVo = service.queryEquipmentInspectionList(queryVO);
		System.out.println(JSONObject.toJSONString(pageVo));
	}
}
