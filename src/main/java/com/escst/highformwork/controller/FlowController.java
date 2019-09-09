package com.escst.highformwork.controller;

import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.highformwork.bean.FlowBean;
import com.escst.highformwork.entity.FlowEntity;
import com.escst.highformwork.service.HighformworkService;
import com.escst.highformwork.vo.FlowVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author dwj
 * @desc
 * @date 9:56 17/7/2018
 */
@RequestMapping("flow")
@Controller
public class FlowController {

    private final static Logger logger = LoggerFactory.getLogger(FlowController.class);

    @Autowired
    private HighformworkService highformworkService;

    /**
     * @param entity
     * @return
     * @desc 获取流水段列表
     * @author dwj
     * @date 17/7/2018 9:30
     */
    @RequestMapping("queryListByConstructionId")
    @ResponseBody
    public ReturnJson queryListByConstructionId(FlowEntity entity) {
        ReturnJson returnJson = null;
        try {
            PageVo vo = highformworkService.selectAllByConstructionId(entity);
            returnJson = ReturnJson.success(vo.getRows(), vo.getTotalRecord());
        } catch (Exception e) {
            logger.error("获取流水段列表异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }

    /**
     * @param bean
     * @return
     * @desc 新增流水段列表
     * @author dwj
     * @date 17/7/2018 9:33
     */
    @RequestMapping("save")
    @ResponseBody
    public ReturnJson save(FlowBean bean) {
        ReturnJson returnJson = null;
        try {
            highformworkService.saveFlow(bean);
            returnJson = ReturnJson.success();
        } catch (Exception e) {
            logger.error("新增流水段异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * @param vo
     * @return
     * @desc 获取流水段详情
     * @author dwj
     * @date 18/7/2018 13:52
     */
    @RequestMapping("querFlowDetail")
    @ResponseBody
    public ReturnJson querFlowDetail(FlowVo vo) {
        ReturnJson returnJson = null;
        try {
            FlowVo flowVo = highformworkService.selectFlowDetail(vo);
            returnJson = ReturnJson.success(flowVo);
        } catch (Exception e) {
            logger.error("获取流水段详情异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
     * @desc 获取流水段图片
     * @author jincheng
     * @date 16/10/2018 13:52
     */
    @RequestMapping("queryFilePath")
    @ResponseBody
    public ReturnJson queryFilePath(String flowId) {
        ReturnJson returnJson = null;
        try {
            String filePath = highformworkService.selectFilePath(flowId);
            returnJson = ReturnJson.success(filePath);
        } catch (Exception e) {
            logger.error("获取流水段详情异常" + e.getMessage(), e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


    /**
    * @desc 获取采集器采集时间
    * @param entity
    * @return
    * @author dwj
    * @date 17/10/2018 14:07
    */
    @RequestMapping("getTime")
    @ResponseBody
    public ReturnJson getTime(FlowEntity entity){
        ReturnJson returnJson = null;
        try{
            FlowVo vo = highformworkService.getTime(entity.getId());
            returnJson = ReturnJson.success(vo);
        }catch (Exception e){
            logger.error("获取采集器采集时间异常"+e.getMessage(),e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }



    /**
    * @desc 编辑流水段
    * @param bean
    * @return
    * @author dwj
    * @date 18/10/2018 14:16
    */
    @RequestMapping("updateFlow")
    @ResponseBody
    public ReturnJson updateFlow(FlowBean bean){
        ReturnJson returnJson = null;
        try{
           highformworkService.updateFlow(bean);
            returnJson = ReturnJson.success();
        }catch (Exception e){
            logger.error("编辑流水段异常"+e.getMessage(),e);
            returnJson = ReturnJson.fail("系统异常");
        }
        return returnJson;
    }


}
