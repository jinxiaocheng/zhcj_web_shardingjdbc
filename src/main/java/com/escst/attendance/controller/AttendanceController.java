package com.escst.attendance.controller;

import java.io.IOException;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.attendance.bean.AttendanceBean;
import com.escst.attendance.entity.AttendanceResultEntity;
import com.escst.attendance.service.AttendanceService;
import com.escst.attendance.vo.AttendanceClockRecordVo;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.projectCompany.service.ProjectCompanyService;

/**
 * @desc 考勤结果控制类
 * @author niejing
 * @date 2017年8月9日 下午2:49:29
 */
@Controller
@RequestMapping("attendance")
public class AttendanceController {

	private static Logger logger = LoggerFactory.getLogger(AttendanceController.class);

	@Autowired
	public AttendanceService attendanceService;

	@Autowired
	public ProjectCompanyService projectCompanyService;

	/**
	 * @desc 考勤
	 * @return
	 * @author zhouwei
	 * @date 2017年4月27日 上午11:10:40
	 */
	@RequestMapping("list")
	public ModelAndView attendance(Model model) {
		List<Map<String, Object>> list = projectCompanyService.listByConstructionId(null, ContextUtils.getCurrentUserId());
		model.addAttribute("list", list);
		return new ModelAndView("attendance/list");
	}

	@RequestMapping("attendanceList")
	@ResponseBody
	public ReturnJson queryPersonList(AttendanceResultEntity entity) {
		ReturnJson returnJson;
		try {
			entity.setUserId(ContextUtils.getCurrentUserId());
			// 获取人员列表
			PageVo pageVo = attendanceService.queryAttendanceList(entity);
			returnJson = ReturnJson.success(pageVo);
		} catch (Exception e) {
			returnJson = ReturnJson.fail("系统异常!");
			logger.error("查询考勤结果列表失败:" + e.getMessage(), e);
		}
		return returnJson;
	}


	/**
	* @desc查询考勤信息
	* @param vo
	* @return
	* @author dwj
	* @date 2018/1/17 10:33
	*/
	@RequestMapping("queryList")
	@ResponseBody
	public ReturnJson queryList(AttendanceClockRecordVo vo){
		ReturnJson returnJson = null;
		try{
			PageVo pageVo = attendanceService.queryList(vo);
			returnJson = ReturnJson.success(pageVo);
		}catch (Exception e){
			returnJson = ReturnJson.fail("系统异常！");
			logger.error("查询考勤信息失败："+e.getMessage(),e);
		}
		return returnJson;

	}

	/**
	 * @desc查询某一时段未打卡
	 * @param vo
	 * @return
	 * @author dwj
	 * @date 2018/1/17 10:33
	 */
	@RequestMapping("queryByDate")
	@ResponseBody
	public ReturnJson queryByDate(AttendanceClockRecordVo vo){
		ReturnJson returnJson = null;
		try{
			vo.setUserId(ContextUtils.getCurrentUserId());
			PageVo pageVo = attendanceService.querByDate(vo);
			returnJson = ReturnJson.success(pageVo);
		}catch (Exception e){
			returnJson = ReturnJson.fail("系统异常！");
			logger.error("查询某一时段未打卡信息失败："+e.getMessage(),e);
		}
		return returnJson;

	}

	/**
	 * @desc查询用户下分包公司出勤人数
	 * @param
	 * @return
	 * @author dwj
	 * @date 2018/1/17 10:33
	 */
	@RequestMapping("selectAttendanceByUserId")
	@ResponseBody
	public ReturnJson selectAttendanceByUserId(AttendanceBean bean){
		ReturnJson returnJson = null;
		try{
			bean.setUserId(ContextUtils.getCurrentUserId());;
			PageVo pageVo = attendanceService.selectAttendanceByUserId(bean);
			returnJson = ReturnJson.success(pageVo);
		}catch (Exception e){
			returnJson = ReturnJson.fail("系统异常！");
			logger.error("查询用户下分包公司出勤人数:"+e.getMessage(),e);
		}
		return returnJson;

	}


	/**
	* @desc 获取当前用户下分包公司下班组出勤人数
	* @param bean
	* @return
	* @author dwj
	* @date 23/7/2018 10:28
	*/
	@RequestMapping("createChart")
	@ResponseBody
	public ReturnJson createChart(AttendanceBean bean){
		ReturnJson returnJson = null;
		try{
//			bean.setUserId(ContextUtils.getCurrentUserId());;
			List<Map<String,List<Object>>> vo = attendanceService.createChart(bean);
			returnJson = ReturnJson.success(vo);
		}catch (Exception e){
			returnJson = ReturnJson.fail("系统异常！");
			logger.error("查询用户下分包公司出勤人数:"+e.getMessage(),e);
		}
		return returnJson;

	}

	@RequestMapping("notRecord")
	public ModelAndView notRecord(Model model) {
		return new ModelAndView("attendance/notRecordList");
	}

	@RequestMapping("newList")
	public ModelAndView newList(Model model) {
		return new ModelAndView("attendance/listNew");
	}
	@RequestMapping("toDetail")
	public ModelAndView toDetail(Model model) {
		return new ModelAndView("attendance/detail");
	}
	
	/**
	 * @desc 导出人员模板
	 * @param request
	 * @param response
	 * @throws IOException 
	 * @author caozx
	 * @date 2018年1月3日 下午3:23:10
	 */
	@RequestMapping(value = "/export")
	public void export(HttpServletRequest request,HttpServletResponse response,AttendanceClockRecordVo vo) throws IOException {
		ServletOutputStream sos = null;
		try {
			Workbook workbook = attendanceService.exportExcel(vo);
			String fileName = DateUtils.format(new Date(), DateUtils.TO_MILLISECOND_N) + ".xlsx";
			response.setCharacterEncoding("utf-8");
			// 设置浏览器以附件下载
			response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
			sos = response.getOutputStream();
			workbook.write(sos);
		}
		catch (Exception e) {
			logger.error("导出数据出现错误：" + e.getMessage(), e);
		} finally {
			if (sos != null) {
				sos.close();
			}
		}
	}
}
