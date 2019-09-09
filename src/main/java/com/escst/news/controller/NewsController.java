package com.escst.news.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.news.entity.NewsEntity;
import com.escst.news.service.NewsService;

/**
 * 
 * @desc
 * @author niejing
 * @date 2017年7月10日 下午1:36:35
 */
@Controller
@RequestMapping("news")
public class NewsController {

	private static Logger logger = LoggerFactory.getLogger(NewsController.class);

	@Autowired
	private NewsService newsService;

	@RequestMapping("list")
	public ModelAndView menuListView(Model model) {
		return new ModelAndView("news/list");
	}

	/**
	 * 获取广告列表
	 *
	 * @param uuid
	 *            uuid
	 * @return 返回值
	 */
	@RequestMapping("newsList")
	@ResponseBody
	public ReturnJson newsList(NewsEntity entity) {
		ReturnJson returnJson;
		try {
			PageVo pageVo = newsService.queryAdList(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("系统异常!");
			logger.error("获取资讯列表异常:" + e.getMessage(), e);
		}
		return returnJson;
	}

	@RequestMapping("toAdd")
	public ModelAndView toAdd(Model model) {
		return new ModelAndView("news/add");
	}

	@RequestMapping("add")
	@ResponseBody
	public ReturnJson add(NewsEntity entity) {
		ReturnJson returnJson = null;
		try {
			newsService.save(entity);
			returnJson = ReturnJson.success();
		} catch (Exception e) {
			logger.error("新增资讯异常：", e);
			returnJson = ReturnJson.fail("新增资讯异常!");
		}
		return returnJson;
	}
}
