package com.escst.task;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.escst.commons.job.InspectionTask;
import com.escst.commons.job.PositionWorkTask;

/**
 * @desc
 * @author niejing
 * @date 2018年5月17日 下午4:43:32
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class InspectionTaskTest {

	@Autowired
	InspectionTask task;

	@Autowired
	PositionWorkTask positionWorkTask;
	
	@Test
	public void test01() {
		task.execute();
	}
	
	@Test
	public void test02(){
		positionWorkTask.execute();
	}
}
