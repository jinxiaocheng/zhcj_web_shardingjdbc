package com.escst.faceRecognition.controller;

import com.escst.commons.vo.PageVo;
import com.escst.commons.vo.ReturnJson;
import com.escst.faceRecognition.service.FaceRecognitionService;
import com.escst.faceRecognition.vo.FaceRecognitionVO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.Map;

/**
 * @author jincheng
 * @desc 人脸识别相关
 * @date 2018/3/7 15:35
 */
@Controller
@RequestMapping("faceRecognition")
public class FaceRecognitionController {

    private static Logger logger = LoggerFactory.getLogger(FaceRecognitionController.class);

    @Autowired
    private FaceRecognitionService faceRecognitionService;

    /**
     * @param constructionId 工地ID
     * @return com.escst.commons.vo.ReturnJson
     * @desc 根据工地ID，查询工地中的所有摄像头信息
     * @author jincheng
     * @date 2018/3/7 15:37
     */
    @RequestMapping("listCamera")
    @ResponseBody
    public ReturnJson listCamera(String constructionId) {
        ReturnJson returnJson;
        try {
            // 查摄像头
            List<FaceRecognitionVO> list = faceRecognitionService.listCamera(constructionId);
            returnJson = ReturnJson.success(list);
        }  catch (Exception e) {
            returnJson = ReturnJson.fail("根据工地ID，查询工地中的所有摄像头信息异常");
            logger.error("根据工地ID，查询工地中的所有摄像头信息异常", e);
        }
        return returnJson;
    }


    /**
     * @param
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查询工地下，当日，每个摄像头下抓拍的人数
     * @author jincheng
     * @date 2018/3/7 15:37
     */
    @RequestMapping("getCount")
    @ResponseBody
    public ReturnJson getCount(FaceRecognitionVO faceRecognitionVO) {
        ReturnJson returnJson;
        try {
            // 查人数
            FaceRecognitionVO vo = faceRecognitionService.getCount(faceRecognitionVO);
            returnJson = ReturnJson.success(vo);
        }  catch (Exception e) {
            returnJson = ReturnJson.fail("查询工地下，当日，每个摄像头下抓拍的人数异常");
            logger.error("查询工地下，当日，每个摄像头下抓拍的人数异常", e);
        }
        return returnJson;
    }



    /**
     * @param faceRecognitionVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 根据工地id 摄像头id 查询出摄像头区域的人员
     * @author jincheng
     * @date 2018/3/7 16:03
     */
    @RequestMapping("listFace")
    @ResponseBody
    public ReturnJson listFace(FaceRecognitionVO faceRecognitionVO) {
        ReturnJson returnJson;
        try {
            // 查人
            List<FaceRecognitionVO> list = faceRecognitionService.listFace(faceRecognitionVO);
            returnJson = ReturnJson.success(list);
        }  catch (Exception e) {
            returnJson = ReturnJson.fail("根据工地id 摄像头id 查询出摄像头区域的人员异常");
            logger.error("根据工地id 摄像头id 查询出摄像头区域的人员异常", e);
        }
        return returnJson;
    }


    /**
     * @param faceRecognitionVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 点击人员，查询出他经过摄像头的轨迹
     * @author jincheng
     * @date 2018/3/7 16:03
     */
    @RequestMapping("listFaceCount")
    @ResponseBody
    public ReturnJson listFaceCount(FaceRecognitionVO faceRecognitionVO) {
        ReturnJson returnJson;
        try {
            // 点击人员，查询出他经过摄像头的轨迹
            List<FaceRecognitionVO> list = faceRecognitionService.listFaceCount(faceRecognitionVO);
            returnJson = ReturnJson.success(list);
        }  catch (Exception e) {
            returnJson = ReturnJson.fail("根据工地id 摄像头id 查询出摄像头区域的人员异常");
            logger.error("根据工地id 摄像头id 查询出摄像头区域的人员异常", e);
        }
        return returnJson;
    }




    /**
     * @param faceRecognitionVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查询人脸抓拍的照片
     * @author jincheng
     * @date 2018/3/7 16:03
     */
    @RequestMapping("listPhoto")
    @ResponseBody
    public ReturnJson listPhoto(FaceRecognitionVO faceRecognitionVO) {
        ReturnJson returnJson;
        try {
            // 查询人脸抓拍的照片
            PageVo pageVo = faceRecognitionService.listPhoto(faceRecognitionVO);
            returnJson = ReturnJson.success(pageVo);
        }  catch (Exception e) {
            returnJson = ReturnJson.fail("查询人脸抓拍的照片异常");
            logger.error("查询人脸抓拍的照片异常", e);
        }
        return returnJson;
    }

    /**
     * @param faceRecognitionVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查询人员历史轨迹
     * @author jincheng
     * @date 2018/3/7 16:03
     */
    @RequestMapping("listTrack")
    @ResponseBody
    public ReturnJson listTrack(FaceRecognitionVO faceRecognitionVO) {
        ReturnJson returnJson;
        try {
            // 查询人员历史轨迹
            List<FaceRecognitionVO> list = faceRecognitionService.listTrack(faceRecognitionVO);
            returnJson = ReturnJson.success(list);
        }  catch (Exception e) {
            returnJson = ReturnJson.fail("查询人员历史轨迹异常");
            logger.error("查询人员历史轨迹异常", e);
        }
        return returnJson;
    }


    /**
     * @param faceRecognitionVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 人证对比查询
     * @author jincheng
     * @date 2018/3/20 16:03
     */
    @RequestMapping("listFaceContrast")
    @ResponseBody
    public ReturnJson listFaceContrast(FaceRecognitionVO faceRecognitionVO) {
        ReturnJson returnJson;
        try {
            // 人证对比查询
            PageVo pageVo = faceRecognitionService.listFaceContrast(faceRecognitionVO);
            returnJson = ReturnJson.success(pageVo);
        }  catch (Exception e) {
            returnJson = ReturnJson.fail("人证对比查询异常");
            logger.error("人证对比查询异常", e);
        }
        return returnJson;

    }

    /**
     * @param faceRecognitionVO
     * @return com.escst.commons.vo.ReturnJson
     * @desc 查询人证对比的历史轨迹
     * @author jincheng
     * @date 2018/3/7 16:03
     */
    @RequestMapping("listContrastTrack")
    @ResponseBody
    public ReturnJson listContrastTrack(FaceRecognitionVO faceRecognitionVO) {
        ReturnJson returnJson;
        try {
            // 查询人证对比的历史轨迹
            PageVo pageVo = faceRecognitionService.listContrastTrack(faceRecognitionVO);
            returnJson = ReturnJson.success(pageVo);
        }  catch (Exception e) {
            returnJson = ReturnJson.fail("查询人员历史轨迹异常");
            logger.error("查询人员历史轨迹异常", e);
        }
        return returnJson;
    }




}
