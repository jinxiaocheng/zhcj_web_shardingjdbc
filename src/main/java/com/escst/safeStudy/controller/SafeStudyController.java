package com.escst.safeStudy.controller;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.safeStudy.entity.SafetyStudyEntity;
import com.escst.safeStudy.service.SafetyStudyService;
import com.escst.safeStudy.vo.SafetyStudyDetailVO;
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
 * @desc 安全培训和安全演习控制器
 * @date 2017/3/6 14:22
 */
@Controller
@RequestMapping("safeStudy")
public class SafeStudyController {

    private static final Logger logger = LoggerFactory.getLogger(SafeStudyController.class);

    @Autowired
    private SafetyStudyService safetyStudyService;


    /**
     * @desc 跳转到安全培训安全演习列表页面
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
        mv.setViewName("safeStudy/list");
        return mv;
    }

    /**
     * 跳转到查看页面
     * @param type
     * @return
     */
    @RequestMapping("toView")
    public ModelAndView toView(@RequestParam(value = "type") Integer type,@RequestParam(value="id") String id) {
        ModelAndView mv = new ModelAndView();
        try {
            //获取当前用户ID
            String userId = ContextUtils.getCurrentUserId();
            mv.addObject("userId", userId);
            mv.addObject("type", type);

            //根据id查询详细信息
            SafetyStudyDetailVO vo = safetyStudyService.queryDetailInfo(id);
            mv.addObject("vo", vo);
            mv.setViewName("safeStudy/view");
        } catch (Exception e) {
            logger.error(e.getMessage(),e);
        }
        return mv;
    }

    /**
     * 跳转到新增页面
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
        mv.setViewName("safeStudy/add");
        return mv;
    }

    /**
     * 通过工地id和安全类型查询安全培训和安全演习列表
     *
     * @param safetyStudyEntity
     * @return
     */
    @RequestMapping("queryByConstructionId")
    @ResponseBody
    public ReturnJson queryByConstructionId(SafetyStudyEntity safetyStudyEntity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = safetyStudyService.queryByConstructionId(safetyStudyEntity);
            System.out.println(JSONObject.toJSON(pageVo));
            returnJson = ReturnJson.success(pageVo);
        } catch (EscstException e) {
            logger.error("查询安全学习出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * 新增和修改安全培训和安全演习
     *
     * @param safetyStudyEntity
     * @return
     */
    @RequestMapping("save")
    @ResponseBody
    public ReturnJson save(SafetyStudyEntity safetyStudyEntity) {
        ReturnJson returnJson = null;
        try {
            safetyStudyService.save(safetyStudyEntity);
            returnJson = ReturnJson.success();
        } catch (EscstException e) {
            logger.error("保存安全学习出现异常：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

}
