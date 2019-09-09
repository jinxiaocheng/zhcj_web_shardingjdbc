package com.escst.highformwork.controller;

import com.escst.commons.tree.TreeEntity;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.highformwork.service.HighformworkWarningService;
import com.escst.highformwork.vo.HighformworkFlowVo;
import com.escst.highformwork.vo.HighformworkRealTimeVo;
import org.apache.poi.ss.usermodel.Workbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.util.List;

/**
 * @author jincheng
 * @desc 高支模预警，历史数据相关
 * @date 2018-7-18 16:52
 */
@Controller
@RequestMapping("warning")
public class HighformworkWarningController {

    private static Logger logger = LoggerFactory.getLogger(HighformworkWarningController.class);

    @Autowired
    private HighformworkWarningService highformworkWarningService;


    /**
     * @param highformworkRealTimeVo
     * @return
     * @desc 根据流水段，获取其中的测点
     * @author jincheng
     * @date 2018-7-18 14:28
     */
    @RequestMapping("listMeasurePoint")
    @ResponseBody
    public ReturnJson listMeasurePoint(HighformworkRealTimeVo highformworkRealTimeVo) {
        ReturnJson returnJson = null;
        try {
            List<HighformworkRealTimeVo> list = highformworkWarningService.listMeasurePoint(highformworkRealTimeVo);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("根据流水段，获取其中的测点异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("根据流水段，获取其中的测点异常");
        }
        return returnJson;
    }


    /**
     * @param highformworkRealTimeVo
     * @return
     * @desc 查询测量中的预警/历史数据
     * @author jincheng
     * @date 2018-7-18 15:35
     */
    @RequestMapping("listData")
    @ResponseBody
    public ReturnJson listData(HighformworkRealTimeVo highformworkRealTimeVo) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = highformworkWarningService.listData(highformworkRealTimeVo);
            returnJson = ReturnJson.success(pageVo.getRows(), pageVo.getTotalRecord());
        } catch (Exception e) {
            logger.error("查询测量中的预警/历史数据异常" + e.getMessage());
            returnJson = ReturnJson.fail("查询测量中的预警/历史数据异常");
        }
        return returnJson;
    }


    /**
     * @param type
     * @param flowId
     * @return
     * @desc 根据预警，报警，控制次数查询详情
     * @author niejing
     * @date 2018年7月18日 下午5:15:13
     */
    @RequestMapping("alarmDetail")
    @ResponseBody
    public ReturnJson alarmDetail(@PathVariable String uuid, int type, String flowId) {
        ReturnJson returnJson = null;
        try {
            List<HighformworkRealTimeVo> list = highformworkWarningService.alarmDetail(type, flowId);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            returnJson = ReturnJson.fail("根据预警，报警，控制次数查询详情 异常");
            logger.error("根据预警，报警，控制次数查询详情 异常", e);
        }
        return returnJson;
    }


    /**
     * @param highformworkRealTimeVo
     * @return
     * @desc 获取流水段
     * @author jincheng
     * @date 2018-7-18 14:28
     */
    @RequestMapping("listFlow")
    @ResponseBody
    public ReturnJson listFlow(HighformworkRealTimeVo highformworkRealTimeVo) {
        ReturnJson returnJson = null;
        try {
            List<HighformworkFlowVo> list = highformworkWarningService.listFlow(highformworkRealTimeVo);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("获取流水段异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("获取流水段异常");
        }
        return returnJson;
    }


    /**
     * @desc 获取流水段树
     * @author jincheng
     * @date 2018-7-18 14:28
     */
    @RequestMapping("listFlowTree")
    @ResponseBody
    public ReturnJson listFlowTree(String userId) {
        ReturnJson returnJson = null;
        try {
            List<TreeEntity> list = highformworkWarningService.listFlowTree(userId);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("获取流水段树异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("获取流水段树异常");
        }
        return returnJson;
    }


    /**
     * @desc 导出excel
     * @author jincheng
     * @date 2018/10/10 15:35
     */
    @RequestMapping("/exportExcel")
    public void exportExcel(HighformworkRealTimeVo highformworkRealTimeVo, HttpServletResponse response) throws IOException {
        ServletOutputStream sos = null;
        try {
            // 导出就查询所有符合条件的数据,不用做分页
            highformworkRealTimeVo.setPage(1);
            highformworkRealTimeVo.setRowNum(Integer.MAX_VALUE);
            Workbook workbook = highformworkWarningService.exportExcel(highformworkRealTimeVo);
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
     * @desc 查询流水段的开始、结束时间
     * @author jincheng
     * @date 2018/10/10 15:35
     */
    @RequestMapping("/getTime")
    @ResponseBody
    public ReturnJson getTime(String flowId) {
        ReturnJson returnJson = null;
        try {
            HighformworkFlowVo flowVo = highformworkWarningService.getTime(flowId);
            returnJson = ReturnJson.success(flowVo);
        } catch (Exception e) {
            logger.error("查询流水段的开始、结束时间异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("查询流水段的开始、结束时间异常");
        }
        return returnJson;
    }

}
