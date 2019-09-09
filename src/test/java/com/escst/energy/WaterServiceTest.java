package com.escst.energy;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.vo.PageVo;
import com.escst.energy.service.WaterService;
import com.escst.energy.vo.EnergyQueryVO;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年9月21日 下午2:03:19
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", 
									"classpath:/spring/spring-mvc.xml" })
public class WaterServiceTest {

	@Autowired
	private WaterService service;

	@Test
	public void test01_queryEnergyListPage() {
		EnergyQueryVO queryVO = new EnergyQueryVO();
		queryVO.setConstructionId("25993d79960b11e78e1bc81f66fba166");
		queryVO.setStartDate("2017-09-10");
		queryVO.setEndDate("2017-09-15");
		PageVo pageVo = service.queryEnergyListPage(queryVO);
		System.out.println(JSONObject.toJSONString(pageVo));
	}
}
