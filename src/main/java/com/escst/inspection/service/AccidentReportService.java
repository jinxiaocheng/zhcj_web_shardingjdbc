package com.escst.inspection.service;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.*;
import com.escst.commons.vo.PageVo;
import com.escst.construction.vo.SimpleConstructionVO;
import com.escst.file.service.FileService;
import com.escst.file.vo.FilePathVO;
import com.escst.inspection.entity.AccidentLossDetailEntity;
import com.escst.inspection.entity.AccidentReportEntity;
import com.escst.inspection.mapper.AccidentReportMapper;
import com.escst.inspection.vo.AccidentReportDetailVO;
import com.escst.person.vo.PersonVO;
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
 * @desc 事故报告
 * @date 2017/3/6 14:13
 */
@Service
@Transactional
public class AccidentReportService {

    private static final String SEPARATOR_CHAR = "/";

    @Autowired
    private AccidentReportMapper accidentReportMapper;

    @Autowired
    private FileService fileService;

    /**
     * 通过工地id查询质量和安全事故报告列表
     *
     * @param accidentReportEntity
     * @return
     */
    public PageVo listByConstructionId(AccidentReportEntity accidentReportEntity) {
        PageVo pageVo = new PageVo();
        try {
            //工地id
            String constructionId = accidentReportEntity.getConstructionId();

            String projectCompanyId = accidentReportEntity.getProjectCompanyId();

            //类型.1:质量事故报告;2:安全事故报告
            int type = accidentReportEntity.getType();

            // 事故等级
            // 质量:1:质量问题;2:一般事故;3:严重事故;4:重大事故;5:特别重大事故;
            // 安全:1:一般事故;2:较大事故;3:重大事故;4:特别重大事故;
            int level = accidentReportEntity.getLevel();

            //当前页号
            int pageNum = accidentReportEntity.getPage();

            //每页显示记录数
            int pageSize = accidentReportEntity.getRowNum();

            //每页的第一条记录的索引
            int offset = (pageNum - 1) * pageSize;

            Map<String, Object> paraMap = new HashMap<String, Object>();
            if(StringUtils.isEmpty(constructionId)) {
                UserEntity userEntity = ContextUtils.getCurrentUser();
                List<SimpleConstructionVO> constructionList = userEntity.getConstructionList();
                paraMap.put("constructionList",constructionList);
            } else {
                paraMap.put("constructionId", constructionId);
            }
            paraMap.put("level", level);
            paraMap.put("type", type);
            paraMap.put("offset", offset);
            paraMap.put("rows", pageSize);
            paraMap.put("projectCompanyId", projectCompanyId);

            //查询总的记录数
            int count = accidentReportMapper.queryCount(paraMap);

            List<Map<String, Object>> list  = accidentReportMapper.selectList(paraMap);
            if (!CollectionUtils.isEmpty(list)) {
                for (Map<String, Object> map : list) {
                    String id = (String) map.get("id");
                    long isAttach = (Long) map.get("isAttach");

                    Date businessDate = (Date)map.get("businessDate");
                    String businessDateStr = "";
                    if(null != businessDate) {
                        businessDateStr = DateUtils.format(businessDate,"yyyy-MM-dd");
                    }
                    map.put("businessDate",businessDateStr);

                    List<FilePathVO> filePathVOs = null;
                    if (isAttach == 1) {
                        //通过id获取上传的附件图片的路径集合
                        filePathVOs = fileService.queryFilePath(id);
                    } else {
                        filePathVOs = new ArrayList<FilePathVO>();
                    }
                    map.put("picList", filePathVOs);

                    //如果是安全事故报告，则统计受伤人数和死亡人数
                    if(type == 2) {
                        Map<String,Object> accidentLossMap = queryByLossType(id);
                        map.putAll(accidentLossMap);
                    }
                }
            }
            //相关数据封装到pageVo对象
            pageVo.setCurrentPage(pageNum);
            pageVo.setPageSize(pageSize);
            pageVo.setTotalRecord(count);
            pageVo.setRows(list);
        } catch (Exception e) {
            throw new EscstException("查询检查记录出现异常：" + e.getMessage(), e);
        }
        return pageVo;
    }


    /**
     * 通过id获取事故报告详细信息
     * @param id
     * @return
     */
    public AccidentReportDetailVO
          get(String id) {
        AccidentReportDetailVO accidentReportDetailVO =  new AccidentReportDetailVO();
        try {
            Map<String,Object> map = accidentReportMapper.selectById(id);
            if(!CollectionUtils.isEmpty(map)) {
                Integer isAttach = (Integer)map.get("isAttach");
                Date businessDate = (Date)map.get("businessDate");
                String accidentDate = "";
                if(businessDate != null) {
                    accidentDate = DateUtils.format(businessDate,DateUtils.TO_DATE);
                }
                accidentReportDetailVO.setAccidentDate(accidentDate);
                accidentReportDetailVO.setContactMobile((String)map.get("contactMobile"));
                accidentReportDetailVO.setContactName((String)map.get("contactName"));
                accidentReportDetailVO.setFirstTreatment((String)map.get("results"));
                accidentReportDetailVO.setResume((String)map.get("items"));
                accidentReportDetailVO.setIsAttach(isAttach);
                accidentReportDetailVO.setLevel((Integer)map.get("status"));
                accidentReportDetailVO.setProjectStructureName((String)map.get("projectStructureName"));
                accidentReportDetailVO.setProjectCompanyName((String)map.get("projectCompanyName"));
                accidentReportDetailVO.setTeamName((String)map.get("teamName"));

                AccidentLossDetailEntity accidentLossDetailEntity = new AccidentLossDetailEntity();
                accidentLossDetailEntity.setType(AccidentLossDetailEntity.ACCIDENT_LOSS_INJURED);  //受伤
                accidentLossDetailEntity.setAccidentReportId(id);
                List<PersonVO> injuredList = accidentReportMapper.selectPersonNameByLossType(accidentLossDetailEntity);


                accidentLossDetailEntity.setType(AccidentLossDetailEntity.ACCIDENT_LOSS_DEATH);  //死亡
                List<PersonVO> deathList = accidentReportMapper.selectPersonNameByLossType(accidentLossDetailEntity);

                accidentReportDetailVO.setInjuredPersonList(injuredList);
                accidentReportDetailVO.setDeathPersonList(deathList);

                List<FilePathVO> filePathVOs = null;
                if (isAttach == 1) {
                    //通过id获取上传的附件图片的路径集合
                    filePathVOs = fileService.queryFilePath(id);
                }
                accidentReportDetailVO.setPicList(filePathVOs);
            }
        } catch (EscstException e) {
            throw new EscstException("查询事故报告详细信息出现异常,id:" + id + ";" + e.getMessage(),e);
        }
        return accidentReportDetailVO;
    }

    /**
     * 新增或者修改保存操作
     *
     * @param accidentReportEntity
     */
    public void save(AccidentReportEntity accidentReportEntity) {
        try {
            String id = accidentReportEntity.getId();
            //id为空执行新增操作，不为空执行修改操作
            if (StringUtils.isEmpty(id)) {
                id = UuidUtils.getUuid();
                accidentReportEntity.setId(id);
                accidentReportEntity.setCreateTime(new Date());

                //上传附件，并将附件信息存到文件表
                MultipartFile[] files = accidentReportEntity.getFiles();
                if (files != null && files.length > 0) {
                    accidentReportEntity.setIsAttach(1);
                    //业务id
                    id = accidentReportEntity.getId();
                    //文件存储的存到数据库的路径
                    String savePath = "upload" + SEPARATOR_CHAR + "accidentReport" + SEPARATOR_CHAR + DateUtils.format(new Date(), "yyyyMM");

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
                } else {
                    accidentReportEntity.setIsAttach(0);
                }

                accidentReportMapper.insert(accidentReportEntity);

                //安全事故报告需要选择伤亡人员
                int type = accidentReportEntity.getType();
                if(type == AccidentReportEntity.ACCIDENT_REPORT_SAFETY) {
                    //伤亡人员
                    String[] injuredPersonIds = accidentReportEntity.getInjuredPersonIds();
                    //死亡人员
                    String[] deathPersonIds = accidentReportEntity.getDeathPersonIds();

                    //保存伤亡明细
                    if(injuredPersonIds != null && injuredPersonIds.length > 0) {
                        saveAccidentLossDetail(injuredPersonIds,AccidentLossDetailEntity.ACCIDENT_LOSS_INJURED,id);
                    }
                    if(deathPersonIds != null && deathPersonIds.length > 0) {
                        saveAccidentLossDetail(deathPersonIds,AccidentLossDetailEntity.ACCIDENT_LOSS_DEATH,id);
                    }
                }

            } else {
                accidentReportEntity.setUpdateTime(new Date());
                accidentReportMapper.update(accidentReportEntity);
            }

        } catch (Exception e) {
            throw new EscstException("保存检查记录出现异常：" + e.getMessage(), e);
        }
    }

    /**
     * 保存伤亡明细信息
     * @param personIds 伤亡人员id数组
     * @param lossType  1：受伤；2：死亡
     * @param id  ： 安全事故id
     */
    private void saveAccidentLossDetail(String[] personIds,int lossType,String id) {
        try{
            List<AccidentLossDetailEntity> accidentLossDetailEntityList = new ArrayList<AccidentLossDetailEntity>();
            for(int i=0;i<personIds.length;i++) {
                AccidentLossDetailEntity accidentLossDetailEntity = new AccidentLossDetailEntity();
                String personId = personIds[i];
                accidentLossDetailEntity.setId(UuidUtils.getUuid());
                accidentLossDetailEntity.setAccidentReportId(id);
                accidentLossDetailEntity.setType(lossType);
                accidentLossDetailEntity.setPersonId(personId);
                accidentLossDetailEntityList.add(accidentLossDetailEntity);
            }
            if(!CollectionUtils.isEmpty(accidentLossDetailEntityList)) {
                accidentReportMapper.batchInsertAccidentLoss(accidentLossDetailEntityList);
            }
        } catch (Exception e) {
            throw new EscstException("保存伤亡明细信息出现异常：" +e.getMessage(),e);
        }
    }

    /**
     * 通过伤亡类型分别查询受伤和死亡的人员信息
     * @param id 安全事故报告id
     * @return
     */
    private Map<String,Object> queryByLossType(String id) {
        Map<String,Object> map = new HashMap<String,Object>();
        try {
            AccidentLossDetailEntity accidentLossDetailEntity = new AccidentLossDetailEntity();
            accidentLossDetailEntity.setType(AccidentLossDetailEntity.ACCIDENT_LOSS_INJURED);  //受伤
            accidentLossDetailEntity.setAccidentReportId(id);
            List<Map<String,Object>> injuredList = accidentReportMapper.selectByLossType(accidentLossDetailEntity);
            accidentLossDetailEntity.setType(AccidentLossDetailEntity.ACCIDENT_LOSS_DEATH);  //死亡
            List<Map<String,Object>> deathList = accidentReportMapper.selectByLossType(accidentLossDetailEntity);
            map.put("injuredPersons", injuredList);
            map.put("injuredCount",injuredList.size());
            map.put("deathPersons", deathList);
            map.put("deathCount",deathList.size());
        } catch (Exception e) {
            throw new EscstException("查询受伤和死亡的人数出现异常：" + e.getMessage() + ";事故报告ID：" + id);
        }
        return map;
    }

}
