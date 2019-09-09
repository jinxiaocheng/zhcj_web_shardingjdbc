package com.escst.quality.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @desc 质量管理
 * @author zhouwei
 * @date 2017年4月27日 上午11:13:50
 */
@Controller
@RequestMapping("quality")
public class QualityController {

	/**
	 * @desc 质量检查
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月27日 上午11:16:50
	 */
	@RequestMapping("inspection")
	public ModelAndView inspection() {
		return new ModelAndView("quality/inspection");
	}

	/**
	 * @desc 质量整改
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月27日 上午11:16:50
	 */
	@RequestMapping("corrective")
	public ModelAndView corrective() {
		return new ModelAndView("quality/corrective");
	}

	/**
	 * @desc 质量事故报告
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月27日 上午11:16:50
	 */
	@RequestMapping("accident")
	public ModelAndView accident() {
		return new ModelAndView("quality/accident");
	}
}
