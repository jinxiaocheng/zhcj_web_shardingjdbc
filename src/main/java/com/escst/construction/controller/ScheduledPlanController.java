package com.escst.construction.controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import com.escst.construction.vo.ScheduledPlanTree;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.construction.entity.ScheduledPlanEntity;
import com.escst.construction.service.ScheduledPlanService;

/**
 * @author niejing
 * @desc 总体进度计划控制器
 * @date 2017年8月18日 下午1:26:43
 */
@Controller
@RequestMapping("scheduledPlan")
public class ScheduledPlanController {

    private static Logger logger = LoggerFactory.getLogger(ScheduledPlanController.class);

    @Autowired
    public ScheduledPlanService service;

    @RequestMapping("list")
    public ModelAndView list(Model model) {
        return new ModelAndView("scheduledPlan/scheduledPanTree");
    }

    @RequestMapping("toScheduledPlanList")
    public ModelAndView toScheduledPlanList(Model model, String constructionId) {
        model.addAttribute("constructionId", constructionId);
        return new ModelAndView("scheduledPlan/schedulePlanList");
    }

    /**
     * @param entity
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查询工地的进度计划树
     * @author jincheng
     * @date 2018-4-8 14:15
     */
    @RequestMapping("loadTree")
    @ResponseBody
    public ReturnJson listScheduledPlanTree(ScheduledPlanEntity entity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = service.listScheduledPlanTree(entity);
            returnJson = ReturnJson.easyUI(pageVo);
        } catch (Exception e) {
            logger.error("查询总体进度计划异常：", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * @param scheduledPlanTree
     * @return com.escst.commons.vo.ReturnJson
     * @desc 修改工地下的进度计划树节点名称
     * @author jincheng
     * @date 2018-4-8 14:15
     */
    @RequestMapping("updateTreeName")
    @ResponseBody
    public ReturnJson updateScheduledPlanTreeName(ScheduledPlanTree scheduledPlanTree) {
        ReturnJson returnJson = null;
        try {
            service.updateScheduledPlanTreeName(scheduledPlanTree);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("修改工地下的进度计划树节点名称异常：", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * @param scheduledPlanTree
     * @return com.escst.commons.vo.ReturnJson
     * @desc 添加工地下的进度计划树节点
     * @author jincheng
     * @date 2018-4-8 14:15
     */
    @RequestMapping("addTree")
    @ResponseBody
    public ReturnJson addScheduledPlanTree(ScheduledPlanTree scheduledPlanTree) {
        ReturnJson returnJson = null;
        try {
            service.addScheduledPlanTree(scheduledPlanTree);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("添加工地下的进度计划树节点异常：", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * @param scheduledPlanTree
     * @return com.escst.commons.vo.ReturnJson
     * @desc 删除工地下的进度计划树节点
     * @author jincheng
     * @date 2018-4-8 14:15
     */
    @RequestMapping("deleteTree")
    @ResponseBody
    public ReturnJson deleteScheduledPlanTree(ScheduledPlanTree scheduledPlanTree) {
        ReturnJson returnJson = null;
        try {
            service.deleteScheduledPlanTree(scheduledPlanTree);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("删除工地下的进度计划树节点异常：", e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }



    @RequestMapping("toImport")
    public ModelAndView toImport(Model model, String constructionId) {
        model.addAttribute("userId", ContextUtils.getCurrentUserId());
        model.addAttribute("constructionId", constructionId);
        return new ModelAndView("scheduledPlan/toImport");
    }

    /**
     * 导入
     *
     * @param request
     * @param response
     * @param file           文件
     * @param constructionId 工地ID
     * @return
     */
    @RequestMapping("saveFile")
    @ResponseBody
    public void saveFile(MultipartFile file, HttpServletResponse response, String constructionId) throws IOException {
        ReturnJson returnJson = null;
        String result = null;
        try {
            response.setContentType("text/html;charset=utf-8");
            result = service.importDataFromExcel(file, constructionId);
            logger.info(result);
            if (!StringUtils.isEmpty(result)) {
                returnJson = ReturnJson.fail(result);
            } else {
                returnJson = ReturnJson.success();
            }
        } catch (EscstException e) {
            logger.error("导入总体进度计划数据出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("导入数据出现异常");
        }
        String data = JSONObject.toJSONString(returnJson);
        response.getWriter().write(data);
        response.getWriter().flush();
        response.getWriter().close();
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
        return new ModelAndView("scheduledPlan/detail");
    }

}
