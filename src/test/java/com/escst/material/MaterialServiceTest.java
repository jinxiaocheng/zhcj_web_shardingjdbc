package com.escst.material;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.vo.PageVo;
import com.escst.material.entity.MaterialEntity;
import com.escst.material.service.MaterialService;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年12月29日 上午11:35:07
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class MaterialServiceTest {

	@Autowired
	private MaterialService service;

	@Test
	public void test01_queryBaseMaterialList() {
		MaterialEntity entity = new MaterialEntity();
		entity.setPage(1);
		entity.setRowNum(10);
		PageVo pageVo = service.queryBaseMaterialList(entity);
		System.out.println(JSONObject.toJSONString(pageVo));
	}
}
