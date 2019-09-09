package com.escst.report.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年10月20日 上午8:58:53
 */
@Controller
@RequestMapping("/report")
public class ReportController {

	@RequestMapping("/index")
    public ModelAndView toIndex(HttpServletRequest request,Model model) {
        return new ModelAndView("report/index");
    }
}
