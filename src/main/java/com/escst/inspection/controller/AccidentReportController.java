package com.escst.inspection.controller;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.inspection.entity.AccidentReportEntity;
import com.escst.inspection.service.AccidentReportService;
import com.escst.inspection.vo.AccidentReportDetailVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * @author caozx
 * @desc 质量事故报告和安全事故报告控制器
 * @date 2017/3/6 14:22
 */
@Controller
@RequestMapping("accidentReport")
public class AccidentReportController {

    private static final Logger logger = LoggerFactory.getLogger(AccidentReportController.class);

    @Autowired
    private AccidentReportService accidentReportService;

    /**
     * @desc 跳转到事故报告列表页面
     * @return
     * @author caozx
     * @date 2017年8月8日 上午11:16:50
     */
    @RequestMapping("list")
    public ModelAndView list(@RequestParam(value = "type") Integer type) {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId",userId);
        mv.addObject("type",type);
        mv.setViewName("accident/list");
        return mv;
    }

    /**
     * @desc 跳转到新增页面
     * @param type
     * @return
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd(@RequestParam(value = "type") Integer type) {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId",userId);
        mv.addObject("type",type);
        mv.setViewName("accident/add");
        return mv;
    }

    /**
     * @desc 跳转到查看页面
     * @param type
     * @return
     */
    @RequestMapping("toView")
    public ModelAndView toView(@RequestParam(value = "type") Integer type,
                               @RequestParam(value = "id") String id) {
        ModelAndView mv = new ModelAndView();
        //通过id查询详细信息
        AccidentReportDetailVO vo = accidentReportService.get(id);
        mv.addObject("vo",vo);
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId",userId);
        mv.addObject("type",type);
        mv.setViewName("accident/view");
        return mv;
    }

    /**
     * @desc 跳转到选择受伤人员和死亡人员列表
     * @param constructionId
     * @return
     */
    @RequestMapping("toPersonList")
    public ModelAndView toView(@RequestParam(value = "constructionId") String constructionId) {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId",userId);
        mv.addObject("constructionId",constructionId);
        mv.setViewName("accident/person_list");
        return mv;
    }

    /**
     * 通过工地id查询质量事故报告和安全事故报告列表
     *
     * @param accidentReportEntity
     * @return
     */
    @RequestMapping("queryByConstructionId")
    @ResponseBody
    public ReturnJson queryByConstructionId(AccidentReportEntity accidentReportEntity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = accidentReportService.listByConstructionId(accidentReportEntity);
            returnJson = ReturnJson.success(pageVo);
        } catch (EscstException e) {
            logger.error("查询质量事故报告和安全事故报告出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * 新增和修改事故报告
     * @param accidentReportEntity
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ReturnJson save(AccidentReportEntity accidentReportEntity) {
        ReturnJson returnJson = null;
        try {
            accidentReportService.save(accidentReportEntity);
            returnJson = ReturnJson.success();
        } catch (EscstException e) {
            logger.error("保存质量事故报告和安全事故报告出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

}
