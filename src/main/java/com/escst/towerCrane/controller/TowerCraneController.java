package com.escst.towerCrane.controller;

import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.equipment.enums.EquipmentTypeEnum;
import com.escst.equipment.vo.AcquisitionDataQueryVO;
import com.escst.equipment.vo.QueryVO;
import com.escst.lifter.controller.LifterController;
import com.escst.towerCrane.service.TowerCraneService;
import com.escst.towerCrane.vo.QueryConditionVo;
import com.escst.towerCrane.vo.TowercraneMonitorVo;
import com.escst.towerCrane.vo.TowercraneRealtimeVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.List;

/**
 * @author zhouwei
 * @desc
 * @date 2017年8月21日 下午6:37:22
 */
@Controller
@RequestMapping("/towerCrane")
public class TowerCraneController {

    private static Logger logger = LoggerFactory.getLogger(LifterController.class);

    @Autowired
    private TowerCraneService towerCraneService;

    /**
     * @param request
     * @return
     * @desc 进入塔吊实时数据界面
     * @author zhouwei
     * @date 2017年8月22日 上午8:55:42
     */
    @RequestMapping("/toRealtime")
    public ModelAndView toRealtime(HttpServletRequest request) {
        ModelAndView view = new ModelAndView("towerCrane/realtime");
        String currentDate = DateUtils.format(new Date(), DateUtils.TO_DATE);
        view.addObject("equipmentId", request.getParameter("id"));
        view.addObject("startDate", currentDate);
        view.addObject("endDate", currentDate);
        return view;
    }

    /**
     * @param queryVO
     * @return
     * @desc 得到塔吊的实时数据
     * @author zhouwei
     * @date 2017年8月21日 下午6:33:25
     */
    @RequestMapping("/queryRealtimeList")
    @ResponseBody
    public ReturnJson queryRealtimeList(AcquisitionDataQueryVO queryVO) {
        ReturnJson returnJson;
        try {
            PageVo pageVo = towerCraneService.queryRealtimeList(queryVO);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常！");
            logger.error("查询塔吊实时数据出现异常", e);
        }
        return returnJson;
    }


    @RequestMapping("getTowerCraneMonitor")
    @ResponseBody
    public ReturnJson getTowerCraneMonitor(QueryVO queryVO) {
        ReturnJson returnJson;
        try {
            queryVO.setUserId(ContextUtils.getCurrentUserId());
            List<TowercraneMonitorVo> monitorVo = towerCraneService.selectTowerCraneMonitor(queryVO);
            returnJson = ReturnJson.success(monitorVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常！");
            logger.error("查询塔吊实时数据出现异常", e);
        }
        return returnJson;
    }


    /**
     * @desc 跳转到历史数据页面
     * @author jincheng
     * @date 2018-9-18 10:03
     */
    @RequestMapping("historyList")
    public ModelAndView historyList() {
        ModelAndView view = new ModelAndView("towerCrane/historyList");
        return view;
    }


    /**
     * @desc 查询塔吊历史数据
     * @author jincheng
     * @date 2018-9-18 10:03
     */
    @RequestMapping("listHistoryData")
    @ResponseBody
    public ReturnJson listHistoryData(TowercraneRealtimeVO towercraneRealtimeVO) {
        ReturnJson returnJson;
        try {
            PageVo pageVo = towerCraneService.listHistoryData(towercraneRealtimeVO);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("系统异常！");
            logger.error("查询塔吊历史数据异常", e);
        }
        return returnJson;
    }


    /**
     * @desc 查询设备预警数据
     * @author jincheng
     * @date 2018-9-18 10:03
     */
    @RequestMapping("listWarningData")
    @ResponseBody
    public ReturnJson listWarningData(QueryConditionVo queryConditionVo) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = new PageVo();
            Integer type = queryConditionVo.getType();
            if (type == EquipmentTypeEnum.TOWERCRANE.getValue()) {
                pageVo = towerCraneService.listTowerCraneWarningData(queryConditionVo);
            }
            if (type == EquipmentTypeEnum.LIFTER.getValue()) {
                pageVo = towerCraneService.listLifterWarningData(queryConditionVo);
            }
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("查询设备预警数据异常！");
            logger.error("查询塔吊历史数据异常" + e.getMessage(), e);
        }
        return returnJson;
    }


}
