package com.escst.lifter.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.escst.commons.utils.ContextUtils;
import com.escst.equipment.vo.QueryVO;
import com.escst.lifter.vo.LifterMonitorVo;
import com.escst.lifter.vo.LifterRealtimeVO;
import com.escst.towerCrane.vo.QueryConditionVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.lifter.service.LifterService;

/**
 * @author zhouwei
 * @desc
 * @date 2017年8月21日 下午6:32:48
 */
@Controller
@RequestMapping("/lifter")
public class LifterController {

    private static Logger logger = LoggerFactory.getLogger(LifterController.class);

    @Autowired
    private LifterService lifterService;

    /**
     * @param request
     * @return
     * @desc 进入升降机实时数据列表界面
     * @author zhouwei
     * @date 2017年8月22日 上午8:54:58
     */
    @RequestMapping("/toRealtime")
    public ModelAndView toRealtime(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("lifter/realtime");
        String currentDate = DateUtils.format(new Date(), DateUtils.TO_DATE);
        view.addObject("equipmentId", request.getParameter("id"));
        view.addObject("startDate", currentDate);
        view.addObject("endDate", currentDate);
        return view;
    }

    /**
     * @param queryVO
     * @return
     * @desc 得到升降机的实时数据
     * @author zhouwei
     * @date 2017年8月21日 下午6:33:25
     */
    @RequestMapping("/queryRealtimeList")
    @ResponseBody
    public ReturnJson queryRealtimeList(AcquisitionDataQueryVO queryVO) {
        ReturnJson returnJson;
        try {
            PageVo pageVo = lifterService.queryRealtimeList(queryVO);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常！");
            logger.error("查询升降机实时数据出现异常", e);
        }
        return returnJson;
    }


    /**
     * @desc 升降机监测
     * @author jincheng
     * @date 2018-11-5 10:05
     */
    @RequestMapping("getLifterMonitor")
    @ResponseBody
    public ReturnJson getLifterMonitor(QueryVO queryVO) {
        ReturnJson returnJson = null;
        try {
            queryVO.setUserId(ContextUtils.getCurrentUserId());
            List<LifterMonitorVo> lifterMonitor = lifterService.getLifterMonitor(queryVO);
            returnJson = ReturnJson.success(lifterMonitor);
        } catch (Exception e) {
            logger.error("升降机监测异常" + e.getMessage());
            returnJson = ReturnJson.fail("升降机监测异常");
        }
        return returnJson;
    }

    /**
     * @desc 查询升降机监测历史数据
     * @author jincheng
     * @date 2018-11-5 10:05
     */
    @RequestMapping("listLifterHistoryData")
    @ResponseBody
    public ReturnJson listLifterHistoryData(QueryConditionVo queryConditionVo) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = lifterService.listLifterHistoryData(queryConditionVo);
            returnJson = ReturnJson.success(pageVo.getRows(),pageVo.getTotalRecord());
        } catch (Exception e) {
            logger.error("查询升降机监测历史数据异常" + e.getMessage());
            returnJson = ReturnJson.fail("查询升降机监测历史数据异常");
        }
        return returnJson;
    }

}
