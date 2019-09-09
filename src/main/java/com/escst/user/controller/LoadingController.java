package com.escst.user.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * @author caozx
 * @desc  页面加载跳转
 * @date 2017/9/6 15:15
 */
@Controller
@RequestMapping("loading")
public class LoadingController {

    @RequestMapping("toView")
    public ModelAndView toView() {
        return new ModelAndView("common/loading");
    }
}
