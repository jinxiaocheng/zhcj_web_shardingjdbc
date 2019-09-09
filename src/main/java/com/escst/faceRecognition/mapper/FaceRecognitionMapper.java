package com.escst.faceRecognition.mapper;

import com.escst.faceRecognition.vo.FaceRecognitionVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @author jincheng
 * @desc 人脸识别相关
 * @date 2018/3/7 14:21
 */
@Repository
public interface FaceRecognitionMapper {


    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 根据工地ID，查询工地中的所有摄像头信息
     * @author jincheng
     * @date 2018/3/7 15:06
     */
    List<FaceRecognitionVO> listCamera(FaceRecognitionVO faceRecognitionVO);


    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 根据工地id 摄像头id 查询出当天内摄像头区域的人员
     * @author jincheng
     * @date 2018/3/7 16:03
     */
    List<FaceRecognitionVO> listFace(FaceRecognitionVO faceRecognitionVO);


    /**
     * @param faceRecognitionVO
     * @return com.escst.faceRecognition.vo.FaceRecognitionVO
     * @desc 查询工地下，当日，每个摄像头下抓拍的人数
     * @author jincheng
     * @date 2018/3/22 10:02
     */
    FaceRecognitionVO getCount(FaceRecognitionVO faceRecognitionVO);


    /**
     * @param faceRecognitionVO
     * @return com.escst.faceRecognition.vo.FaceRecognitionVO
     * @desc 查询人脸抓拍照片的条数
     * @author jincheng
     * @date 2018/3/22 10:02
     */
    int getFaceCount(FaceRecognitionVO faceRecognitionVO);


    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 查询人脸抓拍的照片
     * @author jincheng
     * @date 2018/3/7 17:01
     */
    List<FaceRecognitionVO> listPhoto(FaceRecognitionVO faceRecognitionVO);


    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 查询一个人所有的抓拍轨迹
     * @author jincheng
     * @date 2018/3/7 17:01
     */
    List<FaceRecognitionVO> listTrack(FaceRecognitionVO faceRecognitionVO);

    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 点击人员，查询出他经过摄像头的轨迹
     * @author jincheng
     * @date 2018/3/8 16:42
     */
    List<FaceRecognitionVO> listFaceCount(FaceRecognitionVO faceRecognitionVO);


    /**
     * @param faceRecognitionVO
     * @return com.escst.faceRecognition.vo.FaceRecognitionVO
     * @desc 人证对比条数查询
     * @author jincheng
     * @date 2018/3/22 10:02
     */
    int getFaceContrastCount(FaceRecognitionVO faceRecognitionVO);


    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 人证对比查询
     * @author jincheng
     * @date 2018/3/20 16:42
     */
    List<FaceRecognitionVO> listFaceContrast(FaceRecognitionVO faceRecognitionVO);


    /**
     * @param faceRecognitionVO
     * @return com.escst.faceRecognition.vo.FaceRecognitionVO
     * @desc 人证对比历史轨迹条数查询
     * @author jincheng
     * @date 2018/3/22 10:02
     */
    int getContrastTrackCount(FaceRecognitionVO faceRecognitionVO);


    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 查询人证对比的历史轨迹
     * @author jincheng
     * @date 2018/3/7 17:01
     */
    List<FaceRecognitionVO> listContrastTrack(FaceRecognitionVO faceRecognitionVO);


}
