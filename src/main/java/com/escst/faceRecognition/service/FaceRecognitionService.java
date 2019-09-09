package com.escst.faceRecognition.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.*;
import com.escst.commons.vo.PageVo;
import com.escst.faceRecognition.mapper.FaceRecognitionMapper;
import com.escst.faceRecognition.vo.FaceRecognitionVO;
import com.escst.file.service.FileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;

import java.util.List;


/**
 * @author jincheng
 * @desc 人脸识别相关
 * @date 2018/3/6 10:46
 */
@Service
public class FaceRecognitionService {

    @Autowired
    private FaceRecognitionMapper faceRecognitionMapper;

    @Autowired
    private FileService fileService;

    /**
     * @param constructionId 工地id
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 根据工地ID，查询工地中的所有摄像头信息
     * @author jincheng
     * @date 2018/3/7 15:26
     */
    public List<FaceRecognitionVO> listCamera(String constructionId) {
        List<FaceRecognitionVO> list = new ArrayList<FaceRecognitionVO>();
        FaceRecognitionVO faceRecognitionVO = new FaceRecognitionVO();
        try {
            faceRecognitionVO.setConstructionId(constructionId);
            // 查询数据库
            list = faceRecognitionMapper.listCamera(faceRecognitionVO);
            return list;
        } catch (Exception e) {
            throw new EscstException("查询工地中的所有摄像头信息异常：" + e.getMessage(), e);
        }
    }


    /**
     * @param faceRecognitionVO
     * @return com.escst.faceRecognition.vo.FaceRecognitionVO
     * @desc 查询工地下，当日，每个摄像头下抓拍的人数
     * @author jincheng
     * @date 2018/3/22 10:04
     */
    public FaceRecognitionVO getCount(FaceRecognitionVO faceRecognitionVO) {
        FaceRecognitionVO vo = new FaceRecognitionVO();
        try {
            if (StringUtils.isBlank(faceRecognitionVO.getConstructionId())) {
                return vo;
            }
            // 设置当天时间
            faceRecognitionVO.setTimeStr(DateUtils.getCurrentDate().substring(0, 10));
            // 查询数据库
            vo = faceRecognitionMapper.getCount(faceRecognitionVO);
            return vo;
        } catch (Exception e) {
            throw new EscstException("查询工地下，当日，每个摄像头下抓拍的人数异常：" + e.getMessage(), e);
        }
    }


    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 根据工地id 摄像头id 查询出当天内摄像头区域的人员
     * @author jincheng
     * @date 2018/3/7 16:03
     */
    public List<FaceRecognitionVO> listFace(FaceRecognitionVO faceRecognitionVO) {
        List<FaceRecognitionVO> list = new ArrayList<FaceRecognitionVO>();
        try {
            String constructionId = faceRecognitionVO.getConstructionId();
            String equipmentId = faceRecognitionVO.getEquipmentId();
            if (StringUtils.isBlank(constructionId) || StringUtils.isBlank(equipmentId)) {
                return list;
            }
            // 设置当天时间
            faceRecognitionVO.setTimeStr(DateUtils.getCurrentDate().substring(0, 10));
            // 查询数据库
            list = faceRecognitionMapper.listFace(faceRecognitionVO);
            // 设置人员的头像信息
            if (!CollectionUtils.isEmpty(list)) {
                for (FaceRecognitionVO vo : list) {
                    if (StringUtils.isNotBlank(vo.getHeadPortraitId())) {
                        // 头像附件ID
                        String headPortraitId = vo.getHeadPortraitId();
                        // 获取头像信息
                        String path = fileService.queryFilePathById(headPortraitId);
                        vo.setHeadFilePath(path);
                    }
                }

            }
            return list;
        } catch (Exception e) {
            throw new EscstException("根据工地id 摄像头id 查询出摄像头区域的人员异常：" + e.getMessage(), e);
        }
    }


    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 点击人员，查询出他经过摄像头的轨迹
     * @author jincheng
     * @date 2018/3/8 16:42
     */
    public List<FaceRecognitionVO> listFaceCount(FaceRecognitionVO faceRecognitionVO) {
        List<FaceRecognitionVO> list = new ArrayList<FaceRecognitionVO>();
        List<FaceRecognitionVO> resultList = new ArrayList<FaceRecognitionVO>();
        // 设置当天时间
        faceRecognitionVO.setTimeStr(DateUtils.getCurrentDate().substring(0, 10));
        try {
            String constructionId = faceRecognitionVO.getConstructionId();
            String guid = faceRecognitionVO.getGuid();
            if (StringUtils.isBlank(constructionId) || StringUtils.isBlank(guid)) {
                return list;
            }
            // 查询数据库
            list = faceRecognitionMapper.listFaceCount(faceRecognitionVO);
            boolean flag = false;
            String equipmentId = null;
            String timeStr = null;
            for (FaceRecognitionVO recognitionVO : list) {
                equipmentId = recognitionVO.getEquipmentId();
                timeStr = recognitionVO.getTimeStr();
                // 添加第一次走过的摄像头
                if (CollectionUtils.isEmpty(resultList)) {
                    resultList.add(recognitionVO);
                }
                for (FaceRecognitionVO vo : resultList) {
                    String voEquipmentId = vo.getEquipmentId();
                    String voTimeStr = vo.getTimeStr();
                    if (!equipmentId.equals(voEquipmentId) && !timeStr.equals(voTimeStr)) {
                        flag = true;
                    } else {
                        flag = false;
                    }

                }
                if (flag) {
                    // 添加
                    resultList.add(recognitionVO);
                }
            }
            return resultList;
        } catch (Exception e) {
            throw new EscstException("点击人员，查询出他经过摄像头的轨迹异常：" + e.getMessage(), e);
        }

    }


    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 查询人脸抓拍的照片
     * @author jincheng
     * @date 2018/3/7 17:01
     */
    public PageVo listPhoto(FaceRecognitionVO faceRecognitionVO) {
        faceRecognitionVO.setUserId(ContextUtils.getCurrentUserId());
        PageVo pageVo = new PageVo();
        List<FaceRecognitionVO> resultList = new ArrayList<FaceRecognitionVO>();
        try {
            int count = faceRecognitionMapper.getFaceCount(faceRecognitionVO);

            if (count == 0) {
                return pageVo;
            }

            // 当前页
            pageVo.setCurrentPage(faceRecognitionVO.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(faceRecognitionVO.getRowNum());
            // 每页第一条记录的索引
            int startIndex = (faceRecognitionVO.getPage() - 1) * (faceRecognitionVO.getRowNum());
            faceRecognitionVO.setStartIndex(startIndex);
            // 查询人脸抓拍照片
            resultList = faceRecognitionMapper.listPhoto(faceRecognitionVO);
            pageVo.setRows(resultList);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException("查询人脸抓拍的照片异常：" + e.getMessage(), e);
        }
    }

    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 查询一个人所有的抓拍轨迹
     * @author jincheng
     * @date 2018/3/8 10:47
     */
    public List<FaceRecognitionVO> listTrack(FaceRecognitionVO faceRecognitionVO) {
        List<FaceRecognitionVO> list = new ArrayList<FaceRecognitionVO>();
        try {
            String constructionId = faceRecognitionVO.getConstructionId();
            String guid = faceRecognitionVO.getGuid();
            if (StringUtils.isBlank(constructionId) || StringUtils.isBlank(guid)) {
                return list;
            }
            // 查询数据库
            list = faceRecognitionMapper.listTrack(faceRecognitionVO);
            return list;
        } catch (Exception e) {
            throw new EscstException("查询一个人所有的抓拍轨迹异常：" + e.getMessage(), e);
        }

    }


    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 人证对比查询
     * @author jincheng
     * @date 2018/3/20 10:47
     */
    public PageVo listFaceContrast(FaceRecognitionVO faceRecognitionVO) {
        faceRecognitionVO.setUserId(ContextUtils.getCurrentUserId());
        PageVo pageVo = new PageVo();
        List<FaceRecognitionVO> resultList = new ArrayList<FaceRecognitionVO>();
        try {
            int count = faceRecognitionMapper.getFaceContrastCount(faceRecognitionVO);

            if (count == 0) {
                return pageVo;
            }

            // 当前页
            pageVo.setCurrentPage(faceRecognitionVO.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(faceRecognitionVO.getRowNum());
            // 每页第一条记录的索引
            int startIndex = (faceRecognitionVO.getPage() - 1) * (faceRecognitionVO.getRowNum());
            faceRecognitionVO.setStartIndex(startIndex);
            // 查询照片
            resultList = faceRecognitionMapper.listFaceContrast(faceRecognitionVO);
            // 组装返回的数据
            for (FaceRecognitionVO vo : resultList) {
                String cardImagePath = vo.getCardImagePath();
                vo.setCardImagePath(ResourceUtil.getFileDownloadPathNew() + cardImagePath);
            }
            pageVo.setRows(resultList);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException("人证对比查询异常：" + e.getMessage(), e);
        }

    }


    /**
     * @param faceRecognitionVO
     * @return java.util.List<com.escst.faceRecognition.vo.FaceRecognitionVO>
     * @desc 查询人证对比的历史轨迹
     * @author jincheng
     * @date 2018/3/8 10:47
     */
    public PageVo listContrastTrack(FaceRecognitionVO faceRecognitionVO) {
        PageVo pageVo = new PageVo();
        List<FaceRecognitionVO> list = new ArrayList<FaceRecognitionVO>();
        try {
            int count = faceRecognitionMapper.getContrastTrackCount(faceRecognitionVO);

            if (count == 0) {
                return pageVo;
            }

            // 当前页
            pageVo.setCurrentPage(faceRecognitionVO.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);
            pageVo.setPageSize(faceRecognitionVO.getRowNum());
            // 每页第一条记录的索引
            int startIndex = (faceRecognitionVO.getPage() - 1) * (faceRecognitionVO.getRowNum());
            faceRecognitionVO.setStartIndex(startIndex);
            // 查询照片
            list = faceRecognitionMapper.listContrastTrack(faceRecognitionVO);
            pageVo.setRows(list);
            return pageVo;
        } catch (Exception e) {
            throw new EscstException("查询一个人所有的抓拍轨迹异常：" + e.getMessage(), e);
        }

    }

}
