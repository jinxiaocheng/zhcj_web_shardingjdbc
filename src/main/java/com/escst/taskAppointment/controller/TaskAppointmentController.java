package com.escst.taskAppointment.controller;

import java.util.List;
import java.util.Map;

import com.escst.person.entity.PersonEntity;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.person.service.PersonService;
import com.escst.taskAppointment.entity.TaskAppointmentEntity;
import com.escst.taskAppointment.entity.TaskAppointmentProcessEntity;
import com.escst.taskAppointment.service.TaskAppointmentService;

/**
 * @author dwj
 * @desc
 * @date 16:31 2017/4/18
 */
@Controller
@RequestMapping("taskAppointment")
public class TaskAppointmentController {

    private static Logger logger = LoggerFactory.getLogger(TaskAppointmentController.class);

    @Autowired
    private TaskAppointmentService taskAppointmentService;

    @Autowired
    private PersonService personService;

    /**
     * 任务列表信息
     */
    @RequestMapping("list")
    public ModelAndView projectTaskList(@RequestParam(value = "type") Integer type) {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        //type==1查看当天预约列表 等于2查看历史列表
        if (type == 1) {
            mv.addObject("userId", userId);
            mv.setViewName("taskAppointment/list");
        } else if (type == 2) {
            mv.addObject("userId", userId);
            mv.setViewName("taskAppointment/infoList");
        }


        return mv;
    }


    /**
     * 跳转处理，检查，提交任务
     */
    @RequestMapping("toDispose")
    public ModelAndView addProjectTaskProcess(@RequestParam(value = "type") Integer type, @RequestParam(value = "taskAppointmentId") String taskAppointmentId) {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        List<Map<String, Object>> list = taskAppointmentService.queryListDetial(taskAppointmentId);
        if (type == 1) {
            mv.addObject("userId", userId);
            mv.addObject("list", list);
            mv.setViewName("taskAppointment/toHandle");
        } else if (type == 3) {
            mv.addObject("userId", userId);
            mv.addObject("list", list);
            mv.setViewName("taskAppointment/toCommit");
        } else if (type == 2) {
            mv.addObject("userId", userId);
            mv.addObject("list", list);
            mv.setViewName("taskAppointment/toAudit");
        }
        return mv;
    }

    /**
     * 添加任务预约
     */
    @RequestMapping("toAdd")
    public ModelAndView toAdd() {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        PersonEntity entity = personService.getCurrent(userId);
        mv.addObject("entity", entity);
        mv.setViewName("taskAppointment/add");
        return mv;
    }


    /**
     * 查询任务所有信息
     */
    @RequestMapping("queryList")
    @ResponseBody
    public ReturnJson queryList(TaskAppointmentEntity taskAppointmentEntity, @RequestParam(value = "type") Integer type) {
        ReturnJson returnJson;
        try {

            PageVo pageVo = taskAppointmentService.queryList(taskAppointmentEntity, type);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("查询任务预约列表信息失败：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询任务列表出现异常");
        }
        return returnJson;
    }


    /**
     * 任务详细列表信息
     */
    @RequestMapping("toView")
    public ModelAndView toView(@RequestParam(value = "taskAppointmentId") String taskAppointmentId) {
        ModelAndView mv = new ModelAndView();
        try {
            List<Map<String, Object>> list = taskAppointmentService.queryListDetial(taskAppointmentId);
            mv.addObject("list", list);
            mv.setViewName("taskAppointment/view");
        } catch (Exception e) {
            logger.error("查询任务详细失败", e);
        }
        return mv;
    }

    /**
     * 新增任务预约
     */
    @RequestMapping("add")
    @ResponseBody
    public ReturnJson addTaskAppointment(TaskAppointmentEntity taskAppointmentEntity) {
        ReturnJson returnJson;
        try {
            String userId = ContextUtils.getCurrentUserId();
            taskAppointmentEntity.setCreateBy(userId);
            taskAppointmentService.insertTaskAppointmnet(taskAppointmentEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("新增任务预约信息失败：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("处理任务派发信息出现异常");
        }
        return returnJson;
    }

    /**
     * 处理任务预约
     */
    @RequestMapping("addProjectTaskAppointmentProcess")
    @ResponseBody
    public ReturnJson addTaskAppointmentProcess(TaskAppointmentProcessEntity taskAppointmentProcessEntity) {
        ReturnJson returnJson;
        try {
            taskAppointmentService.insertTaskAppointmnetProcess(taskAppointmentProcessEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("处理任务预约信息失败：" + e.getMessage(), e);
            returnJson = ReturnJson.fail(",处理任务派发信息出现异常");
        }
        return returnJson;
    }


}
