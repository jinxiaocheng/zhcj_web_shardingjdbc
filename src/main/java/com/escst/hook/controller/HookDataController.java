package com.escst.hook.controller;

import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.hook.service.HookDataService;
import com.escst.hook.vo.HookDataVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Map;

/**
 * @author jincheng
 * @desc 吊钩数据监测相关
 * @date 2018-10-22 13:40
 */
@RestController
@RequestMapping("hookData")
public class HookDataController {

    private final static Logger logger = LoggerFactory.getLogger(HookDataController.class);

    @Autowired
    private HookDataService hookDataService;


    /**
     * @author jincheng
     * @desc 查询吊钩列表
     * @date 2018-10-22 13:43
     */
    @RequestMapping("listHook")
    public ReturnJson listHook(String constructionId) {
        ReturnJson returnJson = null;
        try {
            List<HookDataVo> list = hookDataService.listHook(constructionId);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error(e.getMessage());
            returnJson = ReturnJson.fail(e.getMessage());
        }
        return returnJson;
    }


    /**
     * @author jincheng
     * @desc 查询吊钩实时数据
     * @date 2018-10-22 13:43
     */
    @RequestMapping("getRealTimeData")
    public ReturnJson getRealTimeData(String hookId) {
        ReturnJson returnJson = null;
        try {
            HookDataVo hookDataVo = hookDataService.getRealTimeData(hookId);
            returnJson = ReturnJson.success(hookDataVo);
        } catch (Exception e) {
            logger.error(e.getMessage());
            returnJson = ReturnJson.fail(e.getMessage());
        }
        return returnJson;
    }


    /**
     * @author jincheng
     * @desc 查询吊钩历史数据
     * @date 2018-10-22 13:43
     */
    @RequestMapping("listHistoryData")
    public ReturnJson listHistoryData(HookDataVo hookDataVo) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = hookDataService.listHistoryData(hookDataVo);
            returnJson = ReturnJson.success(pageVo.getRows(), pageVo.getTotalRecord());
        } catch (Exception e) {
            logger.error(e.getMessage());
            returnJson = ReturnJson.fail(e.getMessage());
        }
        return returnJson;
    }


    /**
     * @author jincheng
     * @desc 查询吊钩监测当日小时趋势图数据
     * @date 2018-10-22 13:43
     */
    @RequestMapping("getChartData")
    public ReturnJson getChartData(HookDataVo hookDataVo) {
        ReturnJson returnJson = null;
        try {
            Map<Object, List<String>> chartData = hookDataService.getChartData(hookDataVo);
            returnJson = ReturnJson.success(chartData);
        } catch (Exception e) {
            logger.error(e.getMessage());
            returnJson = ReturnJson.fail(e.getMessage());
        }
        return returnJson;
    }

}
