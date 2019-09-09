package com.escst.hook;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.tree.TreeEntity;
import com.escst.hook.service.HookVideoService;
import com.escst.video.service.CameraService;

/**
 * @desc 
 * @author niejing
 * @date 2018年10月23日 上午9:43:55
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml",
        "classpath:/spring/spring-mvc.xml" })
public class HookServiceTest {

	@Autowired
	HookVideoService hookService;
	
	@Autowired
	CameraService CameraService;
	
	@Test
	public void test(){
		List<TreeEntity> list = hookService.queryVideoConstructionTree("c18c1656727011e79386b82a72dd25a2");
		System.out.println("lsit>>>>>>>>>>>>>>>"+JSONObject.toJSON(list));
		
	}
	
	@Test
	public void test2(){
		List<TreeEntity> list = CameraService.queryNodeListByUser("c18c1656727011e79386b82a72dd25a2");
		System.out.println("lsit>>>>>>>>>>>>>>>"+JSONObject.toJSON(list));
		
	}
}
