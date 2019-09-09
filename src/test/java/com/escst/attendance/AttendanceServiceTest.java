package com.escst.attendance;

import com.alibaba.fastjson.JSONObject;
import com.escst.attendance.bean.AttendanceBean;
import com.escst.attendance.vo.AttendanceClockRecordVo;
import com.escst.commons.vo.PageVo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.escst.attendance.service.AttendanceService;

import java.util.List;
import java.util.Map;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年7月13日 上午11:21:42
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", 
									"classpath:/spring/spring-mvc.xml" })
public class AttendanceServiceTest {

	@Autowired
	private AttendanceService service;

	@Test
	public void test01_hasNullClockDate() {
		boolean rst = service.hasNullClockDate();
		System.out.println(rst);
	}

	@Test
	public void test02_updateClockDateMinute() {
		service.updateClockDateMinute();
		System.out.println("updateClockDateMinute执行完毕");
	}


	@Test
	public void  test03_selectList(){
		AttendanceClockRecordVo vo = new AttendanceClockRecordVo();
		vo.setConstructionId("67b63245bec94eb39a84121c116c960c");
		vo.setStartDate("2018-07-02 00:00");
		vo.setEndDate("2018-07-02 16:30");
		vo.setPage(1);
		vo.setRowNum(10);
		PageVo pageVo = service.querByDate(vo);
		System.out.println(JSONObject.toJSON(pageVo).toString());
	}

	@Test
	public void test04_selectList(){
		AttendanceBean bean = new AttendanceBean();
		bean.setUserId("24965f95798746f393ff36fea07d6201");
		bean.setPage(1);
		bean.setRowNum(10);
		PageVo pageVo = service.selectAttendanceByUserId(bean);
		System.out.println(JSONObject.toJSON(pageVo).toString());
	}


	@Test
	public void test05_selectList(){
		AttendanceBean bean = new AttendanceBean();
		bean.setId("1b43284f640911e8b0a0c81f66fba165");
		List<Map<String,List<Object>>> vos = service.createChart(bean);
		System.out.println(JSONObject.toJSON(vos).toString());
	}
}
