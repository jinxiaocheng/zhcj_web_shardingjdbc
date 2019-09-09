package com.escst.thirdPart.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.StringUtils;
import com.escst.commons.vo.PageVo;
import com.escst.thirdPart.entity.RecordEntity;
import com.escst.thirdPart.entity.RecordPersonEntity;
import com.escst.thirdPart.mapper.TrainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.tempuri.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author jincheng
 * @desc
 * @date 2018-7-24 14:50
 */
@Service
public class TrainService {


    @Autowired
    private TrainMapper trainMapper;


    /**
     * @param recordPerson
     * @desc 查询培训人员
     * @author jincheng
     * @date 2018-7-25 14:55
     */
    public PageVo getRecordPersonPage(RecordPerson recordPerson) {
        ItemInfoService it = ThirdPartService.getItemInfoService();
        ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
        DictionaryEntry dictionaryEntry = new DictionaryEntry();
        ArrayOfDictionaryEntry arrayOfDictionaryEntry = new ArrayOfDictionaryEntry();
        // 查询条件
        if (StringUtils.isNotBlank(recordPerson.getRecordID())) {
            dictionaryEntry.setKey("RecordID");
            dictionaryEntry.setValue(recordPerson.getRecordID());
            arrayOfDictionaryEntry.getDictionaryEntry().add(dictionaryEntry);
        }
        if (StringUtils.isNotBlank(recordPerson.getIdentifyID())) {
            dictionaryEntry.setKey("IdentifyID");
            dictionaryEntry.setValue(recordPerson.getIdentifyID());
            arrayOfDictionaryEntry.getDictionaryEntry().add(dictionaryEntry);
        }
        // 查询
        RecordPersonList recordPersonPage = soap.getRecordPersonPage(arrayOfDictionaryEntry, recordPerson.getPage(), recordPerson.getRowNum());
        List<RecordPerson> list = recordPersonPage.getList().getRecordPerson();
        PageVo pageVo = new PageVo();
        pageVo.setTotalRecord(recordPersonPage.getTotal());
        pageVo.setRows(list);
        pageVo.setTotalPage(recordPersonPage.getTotal() / recordPerson.getRowNum());
        pageVo.setCurrentPage(recordPerson.getPage());
        pageVo.setPageSize(recordPerson.getRowNum());
        return pageVo;

    }


    /**
     * @param recordPerson
     * @desc 获取培训试卷信息
     * @author jincheng
     * @date 2018-7-25 14:55
     */
    public PageVo getRecordExamPage(RecordPerson recordPerson) {
        ItemInfoService it = ThirdPartService.getItemInfoService();
        ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
        DictionaryEntry dictionaryEntry = new DictionaryEntry();
        ArrayOfDictionaryEntry arrayOfDictionaryEntry = new ArrayOfDictionaryEntry();
        // 查询条件
        if (StringUtils.isNotBlank(recordPerson.getRecordID())) {
            dictionaryEntry.setKey("RecordID");
            dictionaryEntry.setValue(recordPerson.getRecordID());
            arrayOfDictionaryEntry.getDictionaryEntry().add(dictionaryEntry);
        }
        // 查询
        RecordExamList recordExamPage = soap.getRecordExamPage(arrayOfDictionaryEntry, recordPerson.getPage(), recordPerson.getRowNum());
        List<RecordExam> list = recordExamPage.getList().getRecordExam();
        PageVo pageVo = new PageVo();
        pageVo.setTotalRecord(recordExamPage.getTotal());
        pageVo.setRows(list);
        pageVo.setTotalPage(recordExamPage.getTotal() / recordPerson.getRowNum());
        pageVo.setCurrentPage(recordPerson.getPage());
        pageVo.setPageSize(recordPerson.getRowNum());
        return pageVo;

    }

    /**
     * @desc 获取项目部信息
     * @author jincheng
     * @date 2018-7-25 14:55
     */
    public List<Depart> getRootProject() {
        ItemInfoService it = ThirdPartService.getItemInfoService();
        ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
        // 查询
        List<Depart> list = soap.getDeparts().getDepart();
        for (Depart depart : list) {
            depart.setpId(depart.getParentID());
            depart.setName(depart.getDepartName());
        }
        return list;

    }


    /**
     * @return
     * @desc 获取培训记录信息并保存到数据库
     * @author jincheng
     * @date 2018-7-30 10:36
     */
    @Transactional
    public void saveTrainRecord() {
        ItemInfoService it = ThirdPartService.getItemInfoService();
        ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
        RecordList recordPage = soap.getRecordPage(new ArrayOfDictionaryEntry(), 1, 1000);
        List<Record> list = recordPage.getList().getRecord();
        List<RecordEntity> entityList = new ArrayList<RecordEntity>();
        for (Record record : list) {
            RecordEntity recordEntity = new RecordEntity();
            recordEntity.setId(record.getID());
            recordEntity.setOperDate(record.getOperDate().toString());
            recordEntity.setOperUser(record.getOperUser());
            recordEntity.setAutoID(record.getAutoID());
            recordEntity.setBoxNo(record.getBoxNo());
            recordEntity.setCreateDate(record.getCreateDate().toString());
            recordEntity.setCreateUser(record.getCreateUser());
            recordEntity.setDemandID(record.getDemandID());
            recordEntity.setDepartCode(record.getDepartCode());
            recordEntity.setIsfinish(record.getISFINISH());
            recordEntity.setOwnerDeptName(record.getOwnerDeptName());
            recordEntity.setPrecisName(record.getPrecisName());
            recordEntity.setStartTime(record.getStartTime().toString());
            recordEntity.setTeachType(record.getTeachType());
            recordEntity.setTotalTime(record.getTotalTime());
            recordEntity.setTrainName(record.getTrainName());
            recordEntity.setVideoID(record.getVideoID());
            recordEntity.setTrainPlace(record.getTrainPlace());
            recordEntity.setTrainPeriod(record.getTrainPeriod());
            recordEntity.setTrainContent(record.getTrainContent());
            recordEntity.setStandby1(record.getStandby1());
            recordEntity.setStandby2(record.getStandby2());
            recordEntity.setStandby3(record.getStandby3());
            recordEntity.setStandby4(record.getStandby4());
            recordEntity.setStandby5(record.getStandby5());
            recordEntity.setTrainTime(record.getTrainTime().toString());
            recordEntity.setTrainName(record.getTrainName());
            recordEntity.setUploadTime(record.getUploadTime().toString());
            recordEntity.setDepartCode(record.getDepartCode());
            recordEntity.setTrainType(record.getTrainType());
            recordEntity.setTrainDepart(record.getTrainDepart());
            recordEntity.setTrainPartType(record.getTrainPartType());
            entityList.add(recordEntity);
        }
        trainMapper.saveTrainRecord(entityList);
    }

    /**
     * @param entity
     * @return
     * @desc 查询培训记录信息
     * @author jincheng
     * @date 2018-7-31 15:25
     */
    public PageVo listTrainRecord(RecordEntity entity) {
        PageVo pageVo = new PageVo();
        try {
            int count = trainMapper.getCount(entity);
            if (count == 0) {
                return pageVo;
            }
            entity.setStartIndex((entity.getPage() - 1) * entity.getRowNum());
            List<RecordEntity> entityList = trainMapper.listTrainRecord(entity);
            pageVo.setTotalRecord(count);
            pageVo.setCurrentPage(entity.getPage());
            pageVo.setPageSize(entity.getRowNum());
            pageVo.setTotalPage(pageVo.getTotalPage());
            pageVo.setRows(entityList);

            return pageVo;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询培训记录信息异常");
        }
    }


    /**
     * @param
     * @return
     * @desc 查询培训类型
     * @author jincheng
     * @date 2018-7-31 15:25
     */
    public List<String> listTrainType(String departCode) {
        List<String> list = new ArrayList<String>();
        try {
            list = trainMapper.listTrainType(departCode);
            return list;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "查询培训类型异常");
        }
    }


    /**
     * @return
     * @desc 获取培训人员信息并保存到数据库
     * @author jincheng
     * @date 2018-7-30 10:36
     */
    @Transactional
    public void saveTrainRecordPerson() {
        ItemInfoService it = ThirdPartService.getItemInfoService();
        ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
        RecordPersonList recordPersonPage = soap.getRecordPersonPage(new ArrayOfDictionaryEntry(), 1, 1000);
        List<RecordPerson> list = recordPersonPage.getList().getRecordPerson();
        List<RecordPersonEntity> entityList = new ArrayList<RecordPersonEntity>();
        for (RecordPerson recordPerson : list) {
            RecordPersonEntity recordPersonEntity = new RecordPersonEntity();
            recordPersonEntity.setId(recordPerson.getID());
            recordPersonEntity.setOperDate(recordPerson.getOperDate().toString());
            recordPersonEntity.setCreateDate(recordPerson.getCreateDate().toString());
            recordPersonEntity.setOperUser(recordPerson.getOperUser());
            recordPersonEntity.setCreateUser(recordPerson.getCreateUser());
            recordPersonEntity.setPersonName(recordPerson.getPersonName());
            recordPersonEntity.setSex(recordPerson.getSex());
            recordPersonEntity.setIdentifyID(recordPerson.getIdentifyID());
            recordPersonEntity.setNation(recordPerson.getNation());
            recordPersonEntity.setSchloolLevel(recordPerson.getSchloolLevel());
            recordPersonEntity.setTrainDepart(recordPerson.getTrainDepart());
            recordPersonEntity.setStation(recordPerson.getStation());
            recordPersonEntity.setCategory(recordPerson.getCategory());
            recordPersonEntity.setTraPrincipal(recordPerson.getTraPrincipal());
            recordPersonEntity.setRegisteDate(recordPerson.getRegisteDate().toString());
            recordPersonEntity.setFileName(recordPerson.getFileName());
            recordPersonEntity.setCommendPerson(recordPerson.getCommendPerson());
            recordPersonEntity.setFingerId(recordPerson.getFingerId());
            recordPersonEntity.setReadCardId(recordPerson.getReadCardId());
            recordPersonEntity.setAvailabeDate(recordPerson.getAvailabeDate().toString());
            recordPersonEntity.setLeaveDate(recordPerson.getLeaveDate());
            recordPersonEntity.setDepartCode(recordPerson.getDepartCode());
            recordPersonEntity.setAnswer(recordPerson.getAnswer());
            recordPersonEntity.setsGrade(recordPerson.getSGrade());
            recordPersonEntity.setAllNum(recordPerson.getAllNum());
            recordPersonEntity.setPassNum(recordPerson.getPassNum());
            recordPersonEntity.setIsPass(recordPerson.getISPass());
            recordPersonEntity.setRecordID(recordPerson.getRecordID());
            recordPersonEntity.setExamID(recordPerson.getExamID());
            recordPersonEntity.setBoxNo(recordPerson.getBoxNo());
            recordPersonEntity.setStandby1(recordPerson.getStandby1());
            recordPersonEntity.setStandby2(recordPerson.getStandby2());
            recordPersonEntity.setStandby3(recordPerson.getStandby3());
            recordPersonEntity.setStandby4(recordPerson.getStandby4());
            recordPersonEntity.setStandby5(recordPerson.getStandby5());
            entityList.add(recordPersonEntity);
        }
        trainMapper.saveTrainRecordPerson(entityList);
    }


    /**
     * @param entity
     * @return
     * @desc 根据培训记录ID获取培训人员列表
     * @author jincheng
     * @date 2018-7-31 15:25
     */
    public PageVo listTrainRecordPerson(RecordPersonEntity entity) {
        PageVo pageVo = new PageVo();
        try {
            int count = trainMapper.getPersonCount(entity);
            if (count == 0) {
                return pageVo;
            }
            entity.setStartIndex((entity.getPage() - 1) * entity.getRowNum());
            List<RecordPersonEntity> entityList = trainMapper.listTrainRecordPerson(entity);
            pageVo.setTotalRecord(count);
            pageVo.setCurrentPage(entity.getPage());
            pageVo.setPageSize(entity.getRowNum());
            pageVo.setTotalPage(pageVo.getTotalPage());
            pageVo.setRows(entityList);

            return pageVo;
        } catch (Exception e) {
            throw new EscstException(e.getMessage() + "根据培训记录ID获取培训人员列表异常");
        }
    }

}
