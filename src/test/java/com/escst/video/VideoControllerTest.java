package com.escst.video;

import com.escst.video.entity.RoleCameraEntity;
import com.escst.video.service.NvrService;
import com.escst.video.vo.RoleCameraVo;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import com.alibaba.fastjson.JSONObject;
import com.escst.ControllerUtil;
import java.util.List;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年11月29日 下午1:35:36
 */
@RunWith(SpringJUnit4ClassRunner.class)
@WebAppConfiguration
@ContextConfiguration(locations = { "classpath:/spring/applicationContext.xml"
								   ,"classpath:/spring/spring-mvc.xml"
								   ,"classpath:/spring/spring-shiro.xml"})
public class VideoControllerTest {

	@Autowired
	private WebApplicationContext wac;
	
	private MockMvc mvc;

	@Autowired
	private NvrService nvrService;

	@Before
	public void setup() {
		this.mvc = MockMvcBuilders.webAppContextSetup(wac).build();
	}

	@Test
	public void test01_escstNvrFetchTreeNodeList() {
		String url = "/video/escst/nvrPreview/fetchTreeNodeList";
		ControllerUtil.proPost(mvc, url, JSONObject.toJSONString(new JSONObject()));
	}


	@Test
	public void test02_selectCameraByUserId(){
		String userId = "d899be74dc1e4cd08eee6255734bc9b1";
		RoleCameraEntity roleCameraEntity = null;
		List<RoleCameraVo> map = nvrService.selectCameraByUserId(userId,roleCameraEntity);
		System.out.println(JSONObject.toJSONString(map));
	}


}
