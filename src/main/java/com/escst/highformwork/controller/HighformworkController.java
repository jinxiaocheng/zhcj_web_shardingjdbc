package com.escst.highformwork.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @desc   高支模相关页面跳转
 * @author caozx
 * @date 2018年7月17日 下午2:13:39
 */
@Controller
@RequestMapping("highformwork")
public class HighformworkController {
	
	private static final Logger logger = LoggerFactory.getLogger(HighformworkController.class);
	
	
	/**
	 * @desc  跳转到采集仪列表页面
	 * @param uuid
	 * @return 
	 * @author caozx
	 * @date 2018年7月17日 下午2:19:28
	 */

	//新增采集仪
	@RequestMapping("toAddCJY/{uuid}")
	public ModelAndView toAddCJY(@PathVariable String uuid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("gzm/addCJY");
		return mv;
	}
	//新增流水段
	@RequestMapping("toAddLSD/{uuid}")
	public ModelAndView toAddLSD(@PathVariable String uuid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("gzm/addLSD");
		return mv;
	}
	//高支模主页
	@RequestMapping("toIndex/{uuid}")
	public ModelAndView toIndex(@PathVariable String uuid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("gzm/index");
		return mv;
	}
	//高支模查看页-预警次数-报警次数-控制次数
	@RequestMapping("toIndexView/{uuid}")
	public ModelAndView toIndexView(@PathVariable String uuid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("gzm/indexView");
		return mv;
	}
	//高支模设置
	@RequestMapping("toSet/{uuid}")
	public ModelAndView toSet(@PathVariable String uuid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("gzm/set");
		return mv;
	}
	//查看流水段
	@RequestMapping("toViewLSD/{uuid}")
	public ModelAndView toViewLSD(@PathVariable String uuid){
		ModelAndView mv = new ModelAndView();
		mv.setViewName("gzm/viewLSD");
		return mv;
	}

}
