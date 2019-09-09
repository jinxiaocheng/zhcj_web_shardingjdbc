package com.escst.safety.controller;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.safety.entity.RiskOperationEntity;
import com.escst.safety.service.RiskOperationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

/**
 * @author jincheng
 * @desc 危险作业相关
 * @date 2018-8-14 15:49
 */
@Controller
@RequestMapping("riskOperation")
public class RiskOperationController {

    private static final Logger logger = LoggerFactory.getLogger(RiskOperationController.class);

    @Autowired
    private RiskOperationService riskOperationService;


    @RequestMapping("toList")
    public ModelAndView toList() {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);
        mv.setViewName("safety/list");
        return mv;
    }


    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);
        mv.setViewName("safety/add");
        return mv;
    }


    @RequestMapping("toView")
    public ModelAndView toView(@RequestParam(value = "id") String id) {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);

        RiskOperationEntity entity = riskOperationService.view(id);
        mv.addObject("vo", entity);
        mv.setViewName("safety/view");
        return mv;
    }


    /**
     * @param
     * @return com.escst.commons.vo.ReturnJson
     * @desc 新增危险作业记录
     * @author jincheng
     * @date 2018-8-14 15:54
     */
    @RequestMapping("add")
    @ResponseBody
    public ReturnJson add(RiskOperationEntity riskOperationEntity) {
        ReturnJson returnJson = null;
        try {
            riskOperationService.add(riskOperationEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            returnJson = ReturnJson.fail("新增危险作业记录失败");
            logger.error("新增危险作业记录异常");
        }
        return returnJson;
    }


    /**
     * @param
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查看危险作业记录详情
     * @author jincheng
     * @date 2018-8-14 15:54
     */
    @RequestMapping("view")
    @ResponseBody
    public ReturnJson view(RiskOperationEntity riskOperationEntity) {
        ReturnJson returnJson = null;
        try {
            RiskOperationEntity entity = riskOperationService.view(riskOperationEntity.getId());
            returnJson = ReturnJson.success(entity);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("查看危险作业记录详情失败");
            logger.error("查看危险作业记录详情异常");
        }
        return returnJson;
    }


    /**
     * @param
     * @return com.escst.commons.vo.ReturnJson
     * @desc 删除危险作业记录
     * @author jincheng
     * @date 2018-8-14 15:54
     */
    @RequestMapping("delete")
    @ResponseBody
    public ReturnJson delete(RiskOperationEntity riskOperationEntity) {
        ReturnJson returnJson = null;
        try {
            riskOperationService.delete(riskOperationEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            returnJson = ReturnJson.fail("删除危险作业记录失败");
            logger.error("删除危险作业记录异常");
        }
        return returnJson;
    }


    /**
     * @param
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查询危险作业记录列表
     * @author jincheng
     * @date 2018-8-14 15:54
     */
    @RequestMapping("listData")
    @ResponseBody
    public ReturnJson listData(RiskOperationEntity riskOperationEntity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = riskOperationService.listData(riskOperationEntity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("查询危险作业记录列表失败");
            logger.error("查询危险作业记录列表异常");
        }
        return returnJson;
    }


    /**
     * @param riskOperationEntity
     * @return java.util.List<com.escst.safety.entity.RiskOperationEntity>
     * @desc 查询危险作业记录（查询当天）
     * @author jincheng
     * @date 2018-8-17 14:03
     */
    @RequestMapping("data")
    @ResponseBody
    public ReturnJson data(RiskOperationEntity riskOperationEntity) {
        ReturnJson returnJson = null;
        try {
            List<RiskOperationEntity> list = riskOperationService.data(riskOperationEntity);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("查询危险作业记录列表失败");
            logger.error("查询危险作业记录列表异常");
        }
        return returnJson;
    }


    /**
     * @param riskOperationEntity
     * @return java.util.List<com.escst.safety.entity.RiskOperationEntity>
     * @desc 修改危险作业记录
     * @author jincheng
     * @date 2018-8-17 14:03
     */
    @RequestMapping("update")
    @ResponseBody
    public ReturnJson update(RiskOperationEntity riskOperationEntity) {
        ReturnJson returnJson = null;
        try {
            riskOperationService.update(riskOperationEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            returnJson = ReturnJson.fail("修改危险作业记录失败");
            logger.error("修改危险作业记录异常");
        }
        return returnJson;
    }



}
