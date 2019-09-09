package com.escst.teamTime.controller;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.ExcelUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.person.entity.PersonConditionBean;
import com.escst.person.entity.PersonExcelEntity;
import com.escst.teamTime.VO.TeamTimeVO;
import com.escst.teamTime.service.TeamTimeService;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @author jincheng
 * @desc 班组工时
 * @date 2018/2/5 15:19
 */
@Controller
@RequestMapping("teamTime")
public class TeamTimeController {

    private static Logger logger = LoggerFactory.getLogger(TeamTimeController.class);

    @Autowired
    private TeamTimeService teamTimeService;


    /**
     * @param
     * @return org.springframework.web.servlet.ModelAndView
     * @desc 跳转到班组工时列表界面
     * @author jincheng
     * @date 2018/2/5 15:20
     */
    @RequestMapping("list")
    public ModelAndView list() {
        return new ModelAndView("teamTime/list");
    }

    /**
     * @param teamTimeVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查询班组工时统计列表
     * @author jincheng
     * @date 2018/2/5 15:39
     */
    @RequestMapping("listData")
    @ResponseBody
    public ReturnJson listData(TeamTimeVO teamTimeVO) {
        ReturnJson returnJson = null;
        try {
            teamTimeVO.setUserId(ContextUtils.getCurrentUserId());
            PageVo vo = teamTimeService.listData(teamTimeVO);
            returnJson = ReturnJson.success(vo);
        } catch (Exception e) {
            logger.error("查询班组工时列表异常", e.getMessage());
            returnJson = ReturnJson.fail("查询班组工时列表异常");
        }
        return returnJson;
    }

    /**
     * @param
     * @return
     * @desc 跳转人员工时统计页面
     * @author kz
     * @date 2018/2/7 10:08
     */
    @RequestMapping("toPersonWorkTime")
    public ModelAndView toPersonWorkTime(Model model, TeamTimeVO vo) {
        TeamTimeVO teamTimeVO = teamTimeService.query(vo.getId());
        model.addAttribute("areaName", teamTimeVO.getAreaName());
        model.addAttribute("constructionName", teamTimeVO.getConstructionName());
        model.addAttribute("time", teamTimeVO.getTime());
        model.addAttribute("teamName", teamTimeVO.getTeamName());
        return new ModelAndView("teamTime/personWorkTime");
    }


    /**
     * @param teamTimeVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查询人员工时数据
     * @author jincheng
     * @date 2018/2/7 16:31
     */
    @RequestMapping("personWorkTimeData")
    @ResponseBody
    public ReturnJson personWorkTimeData(TeamTimeVO teamTimeVO) {
        ReturnJson returnJson = null;
        try {
            teamTimeVO.setUserId(ContextUtils.getCurrentUserId());
            PageVo vo = teamTimeService.personTimeData(teamTimeVO);
            returnJson = ReturnJson.success(vo);
        } catch (Exception e) {
            logger.error("查询人员工时列表异常", e.getMessage());
            returnJson = ReturnJson.fail("查询人员工时列表异常");
        }
        return returnJson;
    }

    /**
     * @param
     * @return
     * @desc 跳转人员考勤明细页面
     * @author kz
     * @date 2018/2/7 10:09
     */
    @RequestMapping("toAttendanceDetail")
    public ModelAndView toAttendanceDetail(Model model, TeamTimeVO vo) {
        TeamTimeVO teamTimeVO = teamTimeService.select(vo.getId());
        model.addAttribute("areaName", teamTimeVO.getAreaName());
        model.addAttribute("constructionName", teamTimeVO.getConstructionName());
        model.addAttribute("time", teamTimeVO.getTime());
        model.addAttribute("teamName", teamTimeVO.getTeamName());
        model.addAttribute("personName", teamTimeVO.getPersonName());
        return new ModelAndView("teamTime/attendanceDetail");
    }

    /**
     * @param teamTimeVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查询人员考勤明细
     * @author jincheng
     * @date 2018/2/7 16:31
     */
    @RequestMapping("attendanceDetail")
    @ResponseBody
    public ReturnJson attendanceDetail(TeamTimeVO teamTimeVO) {
        ReturnJson returnJson = null;
        try {
            teamTimeVO.setUserId(ContextUtils.getCurrentUserId());
            PageVo vo = teamTimeService.selectPersonData(teamTimeVO);
            returnJson = ReturnJson.success(vo);
        } catch (Exception e) {
            logger.error("查询人员考勤明细异常", e.getMessage());
            returnJson = ReturnJson.fail("查询人员考勤明细异常");
        }
        return returnJson;
    }

    /**
     * @param teamTimeVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 导出班组工时excel
     * @author jincheng
     * @date 2018/2/8 15:35
     */
    @RequestMapping("/exportTeamTimeExcel")
    public void exportExcel(TeamTimeVO teamTimeVO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream sos = null;
        try {
            // 导出就查询所有符合条件的数据,不用做分页
            teamTimeVO.setRowNum(Integer.MAX_VALUE);
            Workbook workbook = teamTimeService.exportTeamTimeExcel(teamTimeVO);
            String fileName = DateUtils.format(new Date(), DateUtils.TO_MILLISECOND_N) + ".xls";
            response.setCharacterEncoding("utf-8");
            // 设置浏览器以附件下载
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            sos = response.getOutputStream();
            workbook.write(sos);
        } catch (Exception e) {
            logger.error("导出excel异常:" + e.getMessage(), e);
        } finally {
            if (sos != null) {
                sos.close();
            }

        }
    }


    /**
     * @param teamTimeVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 导出人员工时excel
     * @author jincheng
     * @date 2018/2/8 15:35
     */
    @RequestMapping("/exportPersonTimeExcel")
    public void exportpersonTimeExcel(TeamTimeVO teamTimeVO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream sos = null;
        try {
            // 导出就查询所有符合条件的数据,不用做分页
            teamTimeVO.setRowNum(Integer.MAX_VALUE);
            Workbook workbook = teamTimeService.exportPersonTimeExcel(teamTimeVO);
            String fileName = DateUtils.format(new Date(), DateUtils.TO_MILLISECOND_N) + ".xls";
            response.setCharacterEncoding("utf-8");
            // 设置浏览器以附件下载
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            sos = response.getOutputStream();
            workbook.write(sos);
        } catch (Exception e) {
            logger.error("导出excel异常:" + e.getMessage(), e);
        } finally {
            if (sos != null) {
                sos.close();
            }

        }
    }


    /**
     * @param teamTimeVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 导出人员考勤明细excel
     * @author jincheng
     * @date 2018/2/8 15:35
     */
    @RequestMapping("/exportPersonAttendanceExcel")
    public void exportPersonAttendanceExcel(TeamTimeVO teamTimeVO, HttpServletRequest request, HttpServletResponse response) throws IOException {
        ServletOutputStream sos = null;
        try {
            // 导出就查询所有符合条件的数据,不用做分页
            teamTimeVO.setRowNum(Integer.MAX_VALUE);
            Workbook workbook = teamTimeService.exportPersonAttendanceExcel(teamTimeVO);
            String fileName = DateUtils.format(new Date(), DateUtils.TO_MILLISECOND_N) + ".xls";
            response.setCharacterEncoding("utf-8");
            // 设置浏览器以附件下载
            response.addHeader("Content-Disposition", "attachment;filename=" + fileName);
            sos = response.getOutputStream();
            workbook.write(sos);
        } catch (Exception e) {
            logger.error("导出excel异常:" + e.getMessage(), e);
        } finally {
            if (sos != null) {
                sos.close();
            }

        }
    }





}
