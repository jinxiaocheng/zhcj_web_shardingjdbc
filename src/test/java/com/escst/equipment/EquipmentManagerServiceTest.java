package com.escst.equipment;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.vo.PageVo;
import com.escst.equipment.enums.EquipmentTypeEnum;
import com.escst.equipment.service.EquipmentManagerService;
import com.escst.equipment.vo.QueryVO;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月16日 下午6:50:12
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", 
									"classpath:/spring/spring-mvc.xml" })
public class EquipmentManagerServiceTest {

	@Autowired
	private EquipmentManagerService service;

	@Test
	public void test01_queryAuthEquipmentList() {
		QueryVO queryVO = new QueryVO();
		queryVO.setUserId("c18c1656727011e79386b82a72dd25a2");
		queryVO.setType(EquipmentTypeEnum.ENVIRONMENT.getValue());
		PageVo pageVo = service.queryAuthEquipmentList(queryVO);
		System.out.println(JSONObject.toJSONString(pageVo));
	}
}
