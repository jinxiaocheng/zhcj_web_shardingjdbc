package com.escst.safeStudy.service;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.ContextUtils;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.utils.FileUploadUtils;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.file.service.FileService;
import com.escst.file.vo.FilePathVO;
import com.escst.safeStudy.entity.SafetyStudyEntity;
import com.escst.safeStudy.entity.SafetyStudyPersonEntity;
import com.escst.safeStudy.mapper.SafetyStudyMapper;
import com.escst.safeStudy.vo.SafetyStudyDetailVO;
import com.escst.user.entity.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * @author caozx
 * @desc 安全学习
 * @date 2017/3/6 14:13
 */
@Service
@Transactional
public class SafetyStudyService {

    private static final String SEPARATOR_CHAR = "/";

    @Autowired
    private SafetyStudyMapper safetyStudyMapper;

    @Autowired
    private FileService fileService;

    /**
     * 通过工地id查询安全学习列表
     *
     * @param safetyStudyEntity
     * @return
     */
    public PageVo queryByConstructionId(SafetyStudyEntity safetyStudyEntity) {
        PageVo pageVo = new PageVo();
        try {
            //工地id
            String constructionId = safetyStudyEntity.getConstructionId();
            //类型.1:安全培训;2:安全演习
            int type = safetyStudyEntity.getType();
            int pageNum = safetyStudyEntity.getPage();
            int pageSize = safetyStudyEntity.getRowNum();
            int offset = (pageNum - 1) * pageSize;
            Map<String, Object> paraMap = new HashMap<String, Object>();
            if(StringUtils.isEmpty(constructionId)) {
                UserEntity userEntity = ContextUtils.getCurrentUser();
                List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
                paraMap.put("constructionList",constructionList);
            } else {
                paraMap.put("constructionId", constructionId);
            }
            paraMap.put("type", type);
            paraMap.put("offset", offset);
            paraMap.put("rows", pageSize);

            int count = safetyStudyMapper.selectCount(paraMap);
            List<Map<String, Object>> list = safetyStudyMapper.selectList(paraMap);
            if (!CollectionUtils.isEmpty(list)) {
                for (Map<String, Object> map : list) {
                    String id = (String) map.get("id");
                    int isAttach = (Integer) map.get("isAttach");

                    Date startDate = (Date)map.get("startDate");
                    Date endDate = (Date)map.get("endDate");
                    String startDateStr = "";
                    String endDateStr = "";
                    if(null != startDate){
                        startDateStr = DateUtils.format(startDate,"yyyy-MM-dd HH:mm");
                    }
                    if(null != endDate) {
                        endDateStr = DateUtils.format(endDate,"yyyy-MM-dd HH:mm");
                    }
                    map.put("startDate",startDateStr);
                    map.put("endDate",endDateStr);

                    List<FilePathVO> filePathVOs = null;
                    if(isAttach == 1) {
                        //通过id获取上传的附件图片的路径集合
                        filePathVOs = fileService.queryFilePath(id);
                    } else {
                        filePathVOs = new ArrayList<FilePathVO>();
                    }

                    //附件url地址集合
                    map.put("picList", filePathVOs);

                    //查询参与人的集合
                    List<Map<String,Object>> personList = safetyStudyMapper.selectPersonListById(id);
                    if(CollectionUtils.isEmpty(personList)) {
                        personList = new ArrayList<Map<String,Object>>();
                    }
                    map.put("personList", personList);
                }
            }
            //相关数据封装到pageVo对象
            pageVo.setCurrentPage(pageNum);
            pageVo.setPageSize(pageSize);
            pageVo.setTotalRecord(count);
            pageVo.setRows(list);
        } catch (Exception e) {
            throw new EscstException("查询安全学习出现异常：" + e.getMessage(), e);
        }
        return pageVo;
    }

    /**
     * 新增或者修改保存操作
     *
     * @param safetyStudyEntity
     */
    public void save(SafetyStudyEntity safetyStudyEntity) {
        try {
            String id = safetyStudyEntity.getId();
            String[] personIds = safetyStudyEntity.getPersonIds();
            //id为空执行新增操作，不为空执行修改操作
            if (StringUtils.isEmpty(id)) {
                id = UuidUtils.getUuid();
                safetyStudyEntity.setId(id);
                safetyStudyEntity.setCreateTime(new Date());

                //上传附件，并将附件信息存到文件表
                MultipartFile[] files = safetyStudyEntity.getFiles();
                if (files != null && files.length > 0) {
                    //是否有附件设置为1
                    safetyStudyEntity.setIsAttach(1);
                    //业务id
                    id = safetyStudyEntity.getId();
                    //文件存储的存到数据库的路径
                    String savePath = "upload" + SEPARATOR_CHAR + "safetyStudy" + SEPARATOR_CHAR + DateUtils.format(new Date(), "yyyyMM");

                    //上传文件
                    Map<String,Object> map = new HashMap<String,Object>();
                    map.put("businessId",id);
                    map.put("filePath",savePath);
                    String result = FileUploadUtils.uploadFiles(files,map);

                    JSONObject jsonObject = JSONObject.parseObject(result);
                    Integer status = jsonObject.getInteger("status");
                    if (status == 0) {
                        throw new EscstException("文件上传失败！");
                    }
//                    for (int i = 0; i < files.length; i++) {
//                        MultipartFile file = files[i];
//                        fileService.uploadFile(file, id, savePath);
//                    }
                }

                safetyStudyMapper.insert(safetyStudyEntity);
                //参与人员存到中间表
                List<SafetyStudyPersonEntity> list = new ArrayList<SafetyStudyPersonEntity>();
                if(personIds != null && personIds.length > 0) {
                    for(String personId : personIds) {
                        SafetyStudyPersonEntity safetyStudyPersonEntity = new SafetyStudyPersonEntity();
                        safetyStudyPersonEntity.setId(UuidUtils.getUuid());
                        safetyStudyPersonEntity.setSafetyStudyId(id);
                        safetyStudyPersonEntity.setPersonId(personId);
                        list.add(safetyStudyPersonEntity);
                    }
                }
                //批量新增安全学习人员数据
                if(!CollectionUtils.isEmpty(list)) {
                    safetyStudyMapper.batchInsertSafetyStudyPerson(list);
                }
            } else {
                safetyStudyEntity.setUpdateTime(new Date());
                safetyStudyMapper.update(safetyStudyEntity);
            }
        } catch (Exception e) {
            throw new EscstException("保存安全学习出现异常：" + e.getMessage(), e);
        }
    }


    /**
     * 通过id查询安全培训详细信息
     * @param id
     * @return
     */
    public SafetyStudyDetailVO queryDetailInfo(String id) {
        SafetyStudyDetailVO vo = null;
        try {
            vo = safetyStudyMapper.selectDetailInfoById(id);
            //查询参与人员
            List<Map<String,Object>> personList = safetyStudyMapper.selectPersonListById(id);
            vo.setPersonList(personList);
            //查询附件
            Integer isAttach = vo.getIsAttach();
            List<FilePathVO> filePathVOs = null;
            if(isAttach == 1) {
                //通过id获取上传的附件图片的路径集合
                filePathVOs = fileService.queryFilePath(id);
            } else {
                filePathVOs = new ArrayList<FilePathVO>();
            }
            vo.setPicList(filePathVOs);
        } catch (Exception e) {
            throw new EscstException("查询安全培训详细信息出现异常：" + e.getMessage(),e);
        }
        return vo;
    }

}
