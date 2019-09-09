package com.escst.job;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.escst.commons.job.PositionWorkTask;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class PositionWorkTaskTest {

	@Autowired
	private PositionWorkTask positionWorkTask;
	
	@Test
	public void test(){
		positionWorkTask.execute();
	}
}
