package com.escst.lifter;

import com.escst.equipment.vo.QueryVO;
import com.escst.lifter.vo.LifterMonitorVo;
import com.escst.towerCrane.vo.QueryConditionVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.vo.PageVo;
import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.lifter.service.LifterService;

import java.util.List;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月21日 下午6:56:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class LifterServiceTest {

	@Autowired
	public LifterService service;
	
	@Test
	public void test01_queryRealtimeList() {
		AcquisitionDataQueryVO queryVO = new AcquisitionDataQueryVO();
		queryVO.setEquipmentId("96bfaf1bbf4e462f9a73abfba9738e98");
		queryVO.setStartDate("2017-08-17");
		queryVO.setEndDate("2017-08-17");
		PageVo rst = service.queryRealtimeList(queryVO);
		System.out.println(JSONObject.toJSONString(rst));
	}

	@Test
	public void getLifterMonitorTest() {
		QueryVO queryVO = new QueryVO();
		queryVO.setUserId("36ad92c7f59243e596a150b65c9ea4fa");
 		List<LifterMonitorVo> lifterMonitor = service.getLifterMonitor(queryVO);
	}


	@Test
	public void listLifterHistoryData() {
		QueryConditionVo queryConditionVo = new QueryConditionVo();
		queryConditionVo.setEquipmentId("c388c293a97344e1a1770cc842ececfb");
		queryConditionVo.setStartTime("2018-09-26 00:00:00");
		queryConditionVo.setEndTime("2018-11-08 00:00:00");
		queryConditionVo.setPage(1);
		queryConditionVo.setRowNum(10);
		PageVo pageVo = service.listLifterHistoryData(queryConditionVo);
	}
}
