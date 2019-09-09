package com.escst.safety.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @desc 安全管理
 * @author zhouwei
 * @date 2017年4月27日 上午11:14:09
 */
@Controller
@RequestMapping("safety")
public class SafetyController {

	/**
	 * @desc 安全检查
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月27日 上午11:16:50
	 */
	@RequestMapping("inspection")
	public ModelAndView inspection() {
		return new ModelAndView("safety/inspection");
	}

	/**
	 * @desc 安全整改
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月27日 上午11:16:50
	 */
	@RequestMapping("corrective")
	public ModelAndView corrective() {
		return new ModelAndView("safety/corrective");
	}

	/**
	 * @desc 安全事故报告
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月27日 上午11:16:50
	 */
	@RequestMapping("accident")
	public ModelAndView accident() {
		return new ModelAndView("safety/accident");
	}

	/**
	 * @desc 安全培训
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月27日 上午11:16:50
	 */
	@RequestMapping("train")
	public ModelAndView train() {
		return new ModelAndView("safety/train");
	}

	/**
	 * @desc 安全演习
	 * @return 
	 * @author zhouwei
	 * @date 2017年4月27日 上午11:16:50
	 */
	@RequestMapping("exercise")
	public ModelAndView exercise() {
		return new ModelAndView("safety/exercise");
	}
}
