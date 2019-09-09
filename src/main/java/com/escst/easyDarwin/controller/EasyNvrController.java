package com.escst.easyDarwin.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.easyDarwin.entity.PlayBackEntity;
import com.escst.easyDarwin.service.PlayBackService;

/**
 * @desc
 * @author niejing
 * @date 2018年3月20日 下午2:10:32
 */
@RestController
@RequestMapping("easyNvr")
public class EasyNvrController {

	private static final Logger logger = LoggerFactory.getLogger(EasyNvrController.class);

	@Autowired
	private PlayBackService playBackService;

	@RequestMapping("playBack")
	public ModelAndView playBack(Model model) {
		return new ModelAndView("easyNVR/review");
	}

	/**
	 * 
	 * @desc 获取回放url列表
	 * @param constructionId
	 * @return
	 * @author niejing
	 * @date 2018年3月20日 下午4:01:41
	 */
	@RequestMapping("hlsList")
	@ResponseBody
	public ReturnJson hlsList(PlayBackEntity entity) {
		ReturnJson returnJson = null;
		try {
			PageVo pageVo = playBackService.queryAll(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			logger.error("获取回放列表异常", e);
			returnJson = ReturnJson.fail("获取回放列表异常");
		}
		return returnJson;
	}

}
