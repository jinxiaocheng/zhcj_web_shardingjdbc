package com.escst.material;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.vo.PageVo;
import com.escst.material.bean.MaterialBean;
import com.escst.material.entity.MaterialApproachEntity;
import com.escst.material.service.MaterialApproachService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.Map;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年12月29日 下午2:31:29
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml", "classpath:/spring/spring-mvc.xml" })
public class MaterialApproachServiceTest {

	@Autowired
	private MaterialApproachService service;

	@Test
	public void test01_queryMaterialAvailableList() {
		MaterialBean entity = new MaterialBean();
		entity.setPage(1);
		entity.setRowNum(10);
		entity.setConstructionId("rt2bfa3a5186f0d30151d3008cb40d99");
		entity.setName("体");
		entity.setModel("2");
		PageVo pageVo = service.queryMaterialAvailableList(entity);
		System.out.println(JSONObject.toJSONString(pageVo));
	}

	@Test
	public void queryApproachDetailInfo() {
		String id = "36d13be60e604b5c8536abd4f1a0e3c1";
		MaterialApproachEntity entity = new MaterialApproachEntity();
		entity.setId(id);
		Map<String, Object> stringObjectMap = service.queryApproachDetailInfo(entity);
		System.out.println(stringObjectMap);

	}

}
