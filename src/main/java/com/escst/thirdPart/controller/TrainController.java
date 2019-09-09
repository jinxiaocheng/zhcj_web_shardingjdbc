package com.escst.thirdPart.controller;

import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.territory.controller.TerritoryController;
import com.escst.thirdPart.entity.RecordEntity;
import com.escst.thirdPart.entity.RecordPersonEntity;
import com.escst.thirdPart.service.TrainRecordService;
import com.escst.thirdPart.service.TrainService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.tempuri.Depart;
import org.tempuri.Record;
import org.tempuri.RecordPerson;

import java.util.List;

/**
 * @author jincheng
 * @desc 人员相关
 * @date 2018-7-25 10:52
 */
@Controller
@RequestMapping("train")
public class TrainController {

    private static final Logger logger = LoggerFactory.getLogger(TerritoryController.class);

    @Autowired
    private TrainService trainService;

    @Autowired
    private TrainRecordService trainRecordService;

    /**
     * @param recordPerson
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查询培训人员
     * @author jincheng
     * @date 2018-7-25 14:55
     */
    @RequestMapping("listPerson")
    @ResponseBody
    public ReturnJson listPerson(RecordPerson recordPerson) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = trainService.getRecordPersonPage(recordPerson);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("查询培训人员异常");
            returnJson = ReturnJson.fail("查询培训人员异常");
        }
        return returnJson;
    }

    /**
     * @param recordPerson
     * @return com.escst.commons.vo.ReturnJson
     * @desc 获取培训试卷信息
     * @author jincheng
     * @date 2018-7-25 14:55
     */
    @RequestMapping("listExam")
    @ResponseBody
    public ReturnJson listExam(RecordPerson recordPerson) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = trainService.getRecordExamPage(recordPerson);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("获取培训试卷信息异常");
            returnJson = ReturnJson.fail("获取培训试卷信息异常");
        }
        return returnJson;

    }


    /**
     * @param record
     * @return ReturnJson
     * @desc 获取培训记录信息
     * @author dwj
     * @date 25/7/2018 16:47
     */
    @RequestMapping("getRecord")
    @ResponseBody
    public ReturnJson getRecord(Record record) {
        ReturnJson returnJson = null;
        try {
            PageVo recordList = trainRecordService.getTrainRecord(record);
            returnJson = ReturnJson.success(recordList);
        } catch (Exception e) {
            logger.error("获取培训记录信息异常", e);
            returnJson = ReturnJson.fail("获取培训记录信息异常");
        }
        return returnJson;
    }


    /**
     * @return com.escst.commons.vo.ReturnJson
     * @desc 获取项目信息
     * @author jincheng
     * @date 2018-7-25 14:55
     */
    @RequestMapping("listProject")
    @ResponseBody
    public ReturnJson listProject() {
        ReturnJson returnJson = null;
        try {
            List<Depart> list = trainService.getRootProject();
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("获取项目信息异常");
            returnJson = ReturnJson.fail("获取项目信息异常");
        }
        return returnJson;

    }

    /**
     * @param
     * @return
     * @desc 查询培训记录列表
     * @author jincheng
     * @date 2018-7-30 10:36
     */
    @RequestMapping("listTrainRecord")
    @ResponseBody
    public ReturnJson listTrainRecord(RecordEntity entity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = trainService.listTrainRecord(entity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("查询培训记录列表异常");
            returnJson = ReturnJson.fail("查询培训记录列表异常");
        }
        return returnJson;
    }


    /**
     * @param
     * @return
     * @desc 查询培训类型
     * @author jincheng
     * @date 2018-7-30 10:36
     */
    @RequestMapping("listTrainType")
    @ResponseBody
    public ReturnJson listTrainType(String departCode) {
        ReturnJson returnJson = null;
        try {
            List<String> list = trainService.listTrainType(departCode);
            returnJson = ReturnJson.success(list);
        } catch (Exception e) {
            logger.error("查询培训类型异常");
            returnJson = ReturnJson.fail("查询培训类型异常");
        }
        return returnJson;
    }


    /**
     * @param
     * @return
     * @desc 根据培训记录ID获取培训人员列表
     * @author jincheng
     * @date 2018-7-30 10:36
     */
    @RequestMapping("listTrainRecordPerson")
    @ResponseBody
    public ReturnJson listTrainRecordPerson(RecordPersonEntity entity) {
        ReturnJson returnJson = null;
        try {
            PageVo pageVo = trainService.listTrainRecordPerson(entity);
            returnJson = ReturnJson.success(pageVo);
        } catch (Exception e) {
            logger.error("根据培训记录ID获取培训人员列表异常");
            returnJson = ReturnJson.fail("根据培训记录ID获取培训人员列表异常");
        }
        return returnJson;
    }


}
