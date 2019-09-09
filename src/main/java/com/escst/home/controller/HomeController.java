package com.escst.home.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.escst.commons.vo.ReturnJson;
import com.escst.home.entity.HomeVo;
import com.escst.home.service.HomeService;

/**
 * @desc
 * @author niejing
 * @date 2018年2月26日 下午3:10:31
 */
@Controller
@RequestMapping("home")
public class HomeController {
	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private HomeService homeService;

	/**
	 * 
	 * @desc 获取首页基本信息
	 * @return
	 * @author niejing
	 * @date 2018年2月26日 下午2:46:02
	 */
	@RequestMapping("getHomeBaseInfo")
	@ResponseBody
	public ReturnJson getHomeBaseInfo(String constructionId) {
		ReturnJson returnJson = null;
		try {
			HomeVo vo = homeService.queryBasicInfo(constructionId);
			returnJson = ReturnJson.success(vo);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("获取首页基本信息异常");
			logger.error("获取首页基本信息异常", e);
		}
		return returnJson;
	}
}
