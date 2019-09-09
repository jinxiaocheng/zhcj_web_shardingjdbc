package com.escst.attendance;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.attendance.service.AttendanceCountService;
import com.escst.attendance.vo.AttendanceNumQueryVO;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.commons.vo.QtyVO;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年11月6日 下午2:28:53
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", 
									"classpath:/spring/spring-mvc.xml" })
public class AttendanceCountServiceTest {

	@Autowired
	private AttendanceCountService service;

	@Test
	public void test01_queryAuthWorkTypePersonQty() {
		BaseAuthVO authVO = new BaseAuthVO();
		authVO.setUserId("c18c1656727011e79386b82a72dd25a2");
		List<QtyVO> list = service.queryAuthWorkTypePersonQty(authVO);
		System.out.println(JSONObject.toJSONString(list));
	}
	
	@Test
	public void Test_queryAttendanceNumQty(){
		AttendanceNumQueryVO vo = new AttendanceNumQueryVO();
		service.queryAttendanceNumQty(vo);
	}
}
