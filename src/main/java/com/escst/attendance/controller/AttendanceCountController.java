package com.escst.attendance.controller;

import java.util.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.attendance.bean.AttendanceBean;
import com.escst.attendance.entity.AttendanceCountEntity;
import com.escst.attendance.service.AttendanceCountService;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.ExcelUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;

/**
 * @author dwj
 * @desc
 * @date 11:24 2017/10/30
 */
@Controller
@RequestMapping("attendaceCountController")
public class AttendanceCountController {

    private static  final Logger logger = LoggerFactory.getLogger(AttendanceCountController.class);

    @Autowired
    private AttendanceCountService attendanceCountService;

    /**
    * @desc  查询考勤统计列表
    * @param bean
    * @return pageVo
    * @author dwj
    * @date 2017/10/30 11:32
    */
    @RequestMapping("queryList")
    @ResponseBody
    public ReturnJson queryList(AttendanceBean bean){
        ReturnJson returnJson;
        try{
            PageVo pageVo = attendanceCountService.queryList(bean);
            returnJson = ReturnJson.success(pageVo);
        }catch (Exception e){
            returnJson = ReturnJson.fail("系统异常");
            logger.error("查询考勤列表失败"+e.getMessage(),e);
        }
        return returnJson;
    }

    /**
    * @desc 新增考勤信息
    * @param entity
    * @return
    * @author dwj
    * @date 2017/10/30 13:09
    */
    @RequestMapping("saveOrUpdate")
    @ResponseBody
    public ReturnJson saveOrUpdate(@RequestBody AttendanceCountEntity entity){
        ReturnJson returnJson;
        try{
            attendanceCountService.batchSave(entity);
            returnJson = ReturnJson.success();
        }catch (Exception e){
            returnJson = ReturnJson.fail("系统异常");
            logger.error("添加考勤统计失败"+e.getMessage(),e);
        }
        return returnJson;
    }

    /**
     * @desc 修改考勤统计失败
     * @param entity
     * @return
     * @author dwj
     * @date 2017/10/30 13:09
     */
    @RequestMapping("update")
    @ResponseBody
    public ReturnJson update(AttendanceCountEntity entity){
        ReturnJson returnJson;
        try{
            attendanceCountService.update(entity);
            returnJson = ReturnJson.success();
        }catch (Exception e){
            returnJson = ReturnJson.fail("系统异常");
            logger.error("修改考勤统计失败"+e.getMessage(),e);
        }
        return returnJson;
    }

    @RequestMapping("list")
    public ModelAndView countLits(){
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);
        mv.setViewName("attendance/countList");
        return mv;
    }

    @RequestMapping("tosave")
    public ModelAndView save(){
        ModelAndView mv = new ModelAndView();
        //获取当前用户ID
        String userId = ContextUtils.getCurrentUserId();
        mv.addObject("userId", userId);
        mv.setViewName("attendance/countSave");
        return mv;
    }

    @RequestMapping("toview")
    public ModelAndView toView(AttendanceCountEntity entity){
        ModelAndView mv = new ModelAndView();
        try{
            AttendanceCountEntity attendanceCountEntity = attendanceCountService.queryById(entity);
            mv.addObject("entity",attendanceCountEntity);
            mv.setViewName("attendance/countView");
        }catch (Exception e){
            logger.error("查看详细信息失败"+e.getMessage(),e);
        }

        return mv;
    }

    @RequestMapping("toedit")
    public ModelAndView toEdit(AttendanceCountEntity entity){
        ModelAndView mv = new ModelAndView();
        try{
            AttendanceCountEntity attendanceCountEntity = attendanceCountService.queryById(entity);
            mv.addObject("entity",attendanceCountEntity);
            mv.setViewName("attendance/countEdit");
        }catch (Exception e){
            logger.error("编辑信息失败"+e.getMessage(),e);
        }

        return mv;
    }
    
    /**
     * @desc 导出人员考勤数据
     * @param bean
     * @return 
     * @author zhouwei
     * @date 2017年11月2日 下午1:55:14
     */
    @RequestMapping("/exportExcel")
	@ResponseBody
	public ReturnJson exportExcel(AttendanceBean bean) {
    	ReturnJson returnJson = null;
		try {
            Map<String,Object> beanParams = attendanceCountService.getAttendancePerson(bean);
			String filePath = ExcelUtils.createExcel("personAttendance.xls", beanParams);
			returnJson = ReturnJson.success(filePath);
		}
		catch (Exception e) {
			logger.error("导出excel异常:" + e.getMessage(), e);
			returnJson = ReturnJson.fail(e.getMessage());
		}
		return returnJson;
    }

    @RequestMapping("toExport")
    public ModelAndView toExport(){
        ModelAndView mv = new ModelAndView();
        try{
            mv.setViewName("attendance/export");
        }catch (Exception e){
            logger.error("编辑信息失败"+e.getMessage(),e);
        }

        return mv;
    }
}
