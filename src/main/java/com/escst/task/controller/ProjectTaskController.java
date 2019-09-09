package com.escst.task.controller;

import java.util.List;
import java.util.Map;

import com.escst.projectStructure.entity.ProjectStructureEntity;
import com.escst.user.entity.UserEntity;
import com.escst.user.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.task.entity.ProjectTaskEntity;
import com.escst.task.entity.ProjectTaskProcessEntity;
import com.escst.task.service.ProjectTaskService;

/**
 * @author dwj
 * @desc
 * @date 19:31 2017/4/10
 */
@Controller
@RequestMapping("projectTask")
public class ProjectTaskController {

    private static Logger logger = LoggerFactory.getLogger(ProjectTaskController.class);
    @Autowired
    private ProjectTaskService projectTaskService;

    @Autowired
    private UserService userService;


    /**
     * 跳转任务列表
     */
    @RequestMapping("list")
    public ModelAndView projectTaskList() {
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);
        mv.setViewName("task/list");
        return mv;
    }

    /**
     * 查询任务所有信息
     */
    @RequestMapping("queryList")
    @ResponseBody
    public ReturnJson queryList(ProjectTaskEntity projectTaskEntity) {
        ReturnJson returnJson;
        try {
            PageVo pageVo = projectTaskService.queryTaskList(projectTaskEntity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("查询任务列表信息失败：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询任务列表出现异常");
        }
        return returnJson;
    }

    /**
     * 发布任务
     */
    @RequestMapping("addProjectTask")
    @ResponseBody
    public ReturnJson addProjectTask(ProjectTaskEntity projectTaskEntity) {
        ReturnJson returnJson;
        try {
            //获取当前用户ID
            String userId = ContextUtils.getCurrentUserId();
            projectTaskEntity.setCreateBy(userId);
            projectTaskService.addProjectTask(projectTaskEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("新增或修改任务派发信息失败：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("新增或修改任务派发信息出现异常");
        }
        return returnJson;
    }

    /**
     * 添加任务页面
     */
    @RequestMapping("toAdd")
    public ModelAndView addTask(@RequestParam(value = "type") Integer type) {
        ModelAndView mv = new ModelAndView();
        if (type == 1) {
            //获取当前用户ID
            String userId = ContextUtils.getCurrentUserId();
            Map<String, Object> map = userService.selectOrgInfoById(userId);
            Object level = map.get("level");
            if(String.valueOf(level).equals("3")){
                mv.addObject("map", map);
            }else {
                mv.addObject("userId", userId);
            }

            mv.setViewName("task/add");
        }
        return mv;
    }


    /**
     * 提交处理任务
     */
    @RequestMapping("addProjectTaskProcess")
    @ResponseBody
    public ReturnJson addProjectTaskProcess(ProjectTaskProcessEntity projectTaskProcessEntity) {
        ReturnJson returnJson;
        try {
            projectTaskService.addProjectTaskProcess(projectTaskProcessEntity);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("提交,处理任务派发信息失败：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("提交,处理任务派发信息出现异常");
        }
        return returnJson;
    }

    /**
     * 跳转处理，检查，提交任务
     */
    @RequestMapping("toDispose")
    public ModelAndView addProjectTaskProcess(@RequestParam(value = "type") Integer type, @RequestParam(value = "taskId") String taskId) {
        ModelAndView mv = new ModelAndView();
        List<ProjectTaskProcessEntity> entityList = projectTaskService.queryList(taskId);
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        if (type == 1) {
            mv.addObject("userId", userId);
            mv.addObject("list", entityList);
            mv.setViewName("task/toHandle");
        } else if (type == 2) {
            mv.addObject("userId", userId);
            mv.addObject("list", entityList);
            mv.setViewName("task/toCommit");
        } else if (type == 3) {
            mv.addObject("userId", userId);
            mv.addObject("list", entityList);
            mv.setViewName("task/toCheck");
        }
        return mv;
    }

    /**
     * 任务详细列表信息
     */
    @RequestMapping("toView")
    public ModelAndView projectTaskListDetail(@RequestParam(value = "taskId") String taskId) {
        ModelAndView mv = new ModelAndView();
        List<ProjectTaskProcessEntity> entityList = projectTaskService.queryList(taskId);
        mv.addObject("list", entityList);
        mv.setViewName("task/view");
        return mv;
    }


    /**
     * 查询检查部位
     */
    @RequestMapping("projectStructure")
    @ResponseBody
    public ReturnJson selectProjectStructure(ProjectStructureEntity projectStructrueEntity) {
        ReturnJson returnJson;
        try {
            List<TreeEntity> list = projectTaskService.queryProjectStrutrue(projectStructrueEntity);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("查询检查部位信息失败：" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询检查部位信息失败");
        }
        return returnJson;
    }


}
