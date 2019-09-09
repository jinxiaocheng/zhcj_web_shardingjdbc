package com.escst.construction.controller;

import com.escst.construction.entity.ScheduledPlanEntity;
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
import com.escst.construction.bean.ProjectScheduleQueryBean;
import com.escst.construction.entity.ProjectScheduleEntity;
import com.escst.construction.service.ProjectScheduleService;
import com.escst.construction.service.ScheduledPlanService;

/**
 * @author niejing
 * @desc 工程进度控制器
 * @date 2017年8月18日 下午1:20:35
 */
@Controller
@RequestMapping("projectSchedule")
public class ProjectScheduleController {

    private static Logger logger = LoggerFactory.getLogger(ProjectScheduleController.class);
    @Autowired
    public ProjectScheduleService service;
    @Autowired
    public ScheduledPlanService scheduledPlanService;

    private static final String FINISH = "2";
    private static final String PROCESSING = "1";

    @RequestMapping("list")
    public ModelAndView list(Model model) {
        return new ModelAndView("projectSchedule/projectScheduleTree");
    }

    @RequestMapping("toProjectScheduleList")
    public ModelAndView toProjectScheduleList(Model model, String constructionId) {
        model.addAttribute("constructionId", constructionId);
        return new ModelAndView("projectSchedule/projectScheduleList");
    }

    @RequestMapping("projectScheduleList")
    @ResponseBody
    public ReturnJson projectScheduleList(ProjectScheduleQueryBean entity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = service.queryProjectSchedule(entity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("查询工程进度异常：", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * @param id
     * @return
     * @desc 更新工程进度状态
     * @author niejing
     * @date 2017年8月18日 下午5:21:52
     */
    @RequestMapping("confirmFinish")
    @ResponseBody
    public ReturnJson confirmFinish(ProjectScheduleEntity entity) {
        ReturnJson returnJson = null;
        try {
            service.updateStatus(entity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("更新工程进度状态异常", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * @param id
     * @return
     * @desc 根据id更新工程进度
     * @author niejing
     * @date 2017年11月3日 下午6:02:05
     */
    @RequestMapping("update")
    @ResponseBody
    public ReturnJson update(ProjectScheduleEntity entity) {
        ReturnJson returnJson = null;
        try {
            if (entity.getPercent() == 100.0F) {
                entity.setStatus(FINISH);
            } else {
                entity.setStatus(PROCESSING);
            }
            service.update(entity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("更新工程进度异常", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * @param model
     * @return
     * @desc 查询详情
     * @author niejing
     * @date 2017年8月28日 下午3:02:19
     */
    @RequestMapping("toDetail")
    public ModelAndView toDetail(Model model, String parentId, String constructionId) {
        model.addAttribute("parentId", parentId);
        model.addAttribute("constructionId", constructionId);
        return new ModelAndView("projectSchedule/detail");
    }

    /**
     * @param entity
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查询工程进度树
     * @author jincheng
     * @date 2018-4-8 14:15
     */
    @RequestMapping("loadTree")
    @ResponseBody
    public ReturnJson listProjectScheduleTree(ProjectScheduleEntity entity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = service.listProjectScheduleTree(entity);
            returnJson = ReturnJson.easyUI(pageVo);
        } catch (Exception e) {
            logger.error("查询工程进度树异常：", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


}
