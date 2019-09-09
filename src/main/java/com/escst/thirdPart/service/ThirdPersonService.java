package com.escst.thirdPart.service;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.DateUtils;
import com.escst.commons.vo.BaseVO;
import com.escst.commons.vo.PageVo;
import com.escst.thirdPart.bean.CountBean;
import com.escst.thirdPart.bean.PersonInfoBean;
import com.escst.thirdPart.bean.ThridPersonBean;
import com.escst.thirdPart.entity.TrainPersonExcelEntity;
import com.escst.thirdPart.mapper.ThirdPersonMapper;
import com.escst.thirdPart.vo.ChartResultVo;
import com.escst.thirdPart.vo.CountVo;
import com.escst.thirdPart.vo.RecordPersonVo;
import com.escst.thirdPart.vo.ThirdPersonVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.tempuri.*;

import java.text.NumberFormat;
import java.util.*;

/**
 * @author dwj
 * @desc
 * @date 14:13 24/7/2018
 */
@Service
public class ThirdPersonService {

    private static final ResourceBundle bundle = ResourceBundle.getBundle("properties/safeTraining");

    @Autowired
    private ThirdPersonMapper thirdPersonMapper;


    public  void getPersons(){
        PersonInfo info = new PersonInfo();
        info.setRowNum(10);
        info.setPage(1);
        ItemInfoService it = ThirdPartService.getItemInfoService();
        ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
        ArrayOfDictionaryEntry dics = new ArrayOfDictionaryEntry();
        PersonList personList = soap.getPersons(dics,info.getPage(),info.getRowNum());
        int total = personList.getTotal();
        int i= Math.round((float) total/10);
        for(int a=0; a<i;a++){
            info.setRowNum(10);
            info.setPage(a+1);
            this.savePersons(info);
        }
    }

    /**
    * @desc 
    * @param
    * @return 
    * @author dwj
    * @date 24/7/2018 14:15
    */
    @Transactional
    public void savePersons(PersonInfo info){
        List<PersonInfoBean> saveList = new ArrayList<PersonInfoBean>();
        try{
            ItemInfoService it = ThirdPartService.getItemInfoService();
            ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
            ArrayOfDictionaryEntry dics = new ArrayOfDictionaryEntry();
            PersonList personList = soap.getPersons(dics,info.getPage(),info.getRowNum());
            List<PersonInfo> list = personList.getList().getPersonInfo();
            for(PersonInfo personInfo : list){
                String id = personInfo.getID();
                PersonInfoBean bean = new PersonInfoBean();
                bean.setId(id);
                bean.setCreateUser(personInfo.getCreateUser());
                bean.setNative(personInfo.getNative());
                bean.setAddress(personInfo.getAddress());
                bean.setAutoID(personInfo.getAutoID());
                bean.setAvailabeDate(DateUtils.format(personInfo.getAvailabeDate().toGregorianCalendar().getTime(),DateUtils.TO_SECOND));
                bean.setBirthDay(DateUtils.format(personInfo.getBirthDay().toGregorianCalendar().getTime(),DateUtils.TO_SECOND));
                bean.setBlackList(personInfo.getBlackList());
                bean.setBloodType(personInfo.getBloodType());
                bean.setBoxID(personInfo.getBoxID());
                bean.setBuildArea(personInfo.getBuildArea());
                bean.setCategory(personInfo.getCategory());
                bean.setCategoryLevel(personInfo.getCategoryLevel());
                bean.setContractNumber(personInfo.getContractNumber());
                bean.setContractOfLabour(personInfo.getContractOfLabour());
                bean.setCreateDate(personInfo.getCreateDate().toGregorianCalendar().getTime());
                bean.setDegrees(personInfo.getDegrees());
                bean.setEmail(personInfo.getEmail());
                bean.setEmergencyContacts(personInfo.getEmergencyContacts());
                bean.setEmergencyContactsTel(personInfo.getEmergencyContactsTel());
                bean.setEntranceDate(DateUtils.format(personInfo.getEntranceDate().toGregorianCalendar().getTime(),DateUtils.TO_SECOND));
                bean.setFileName(personInfo.getFileName());
                bean.setFingerId(personInfo.getFingerId());
                bean.setHealthReport(personInfo.getHealthReport());
                bean.setIdentifyID(personInfo.getIdentifyID());
                bean.setIntegral(personInfo.getIntegral());
                bean.setIsCard(personInfo.getIsCard());
                bean.setUnitName(personInfo.getUnitName());
                bean.setUnitID(personInfo.getUnitID());
                bean.setUnitCode(personInfo.getUnitCode());
                bean.setTraPrincipal(personInfo.getTraPrincipal());
                bean.setTraID(personInfo.getTraID());
                bean.setTelPhone(personInfo.getTelPhone());
                bean.setSufferDeptName(personInfo.getSufferDeptName());
                bean.setStation(personInfo.getStation());
                bean.setState(personInfo.getState());
                bean.setStandby7(personInfo.getStandby7());
                bean.setSex(personInfo.getSex());
                bean.setSafeDuty(personInfo.getSafeDuty());
                bean.setRegisteredType(personInfo.getRegisteredType());
                bean.setRegisterDate(DateUtils.format(personInfo.getRegisterDate().toGregorianCalendar().getTime(),DateUtils.TO_SECOND));
                bean.setReadCardId(personInfo.getReadCardId());
                bean.setProjectNamePart(personInfo.getProjectNamePart());
                bean.setPersonScan(personInfo.getPersonScan());
                bean.setPersonOrder(personInfo.getPersonOrder());
                bean.setPersonName(personInfo.getPersonName());
                bean.setOwnerDeptID(personInfo.getOwnerDeptID());
                bean.setPassport(personInfo.getPassport());
                bean.setOperUser(personInfo.getOperUser());
                bean.setOperDate(personInfo.getOperDate().toGregorianCalendar().getTime());
                bean.setNation(personInfo.getNation());
                bean.setMaritalStatus(personInfo.getMaritalStatus());
                bean.setLeaveDate(personInfo.getLeaveDate().toGregorianCalendar().getTime());
                bean.setIsPhoto(personInfo.getIsPhoto());
                bean.setIsOut(personInfo.getIsOut());
                bean.setAutoID(personInfo.getAutoID());
                bean.setProjectName(personInfo.getProjectName());
                saveList.add(bean);
            }
            //批量新增
            if(!CollectionUtils.isEmpty(saveList)){
                thirdPersonMapper.batchInsert(saveList);
            }
        }catch (Exception e){
            throw new EscstException("获取人员信息异常"+e.getMessage(),e);
        }
    }


    public byte[] getPersonPicture(String id){
        ItemInfoService it = ThirdPartService.getItemInfoService();
        ItemInfoServiceSoap soap = it.getItemInfoServiceSoap();
        byte[] by = soap.getPersonPicture(id);
        return by;
    }


    /**
    * @desc 获取人员列表
    * @param bean
    * @return
    * @author dwj
    * @date 30/7/2018 17:16
    */
    public PageVo getAllPerson(ThridPersonBean bean){
        PageVo vo = new PageVo();
        try{
            Map<String,Object> map = new HashMap<String, Object>();
            int pageNum = bean.getPage();
            //每页显示记录数
            int pageSize = bean.getRowNum();
            //每页的第一条记录的索引
            int offset = (pageNum - 1) * pageSize;
            map.put("personName", bean.getPersonName());
            map.put("category", bean.getCategory());
            map.put("startDate", bean.getStartDate());
            map.put("endDate", bean.getEndDate());
            map.put("ownerDeptID",bean.getOwnerDeptID());
            map.put("unitID",bean.getUnitID());
            map.put("train",bean.getTrain());
            map.put("offset", offset);
            map.put("rows", pageSize);
            int count = thirdPersonMapper.count(map);
            List<ThirdPersonVo> list = thirdPersonMapper.selectAll(map);
            if (!CollectionUtils.isEmpty(list)) {
                vo.setRows(list);
            }
            //相关数据封装到PageVo
            vo.setCurrentPage(pageNum);
            vo.setPageSize(pageSize);
            vo.setTotalRecord(count);
        }catch (Exception e){
            throw new EscstException("获取人员列表信息异常"+e.getMessage(),e);
        }
        return vo;
    }

    /**
    * @desc 获取人员详情信息
    * @param bean
    * @return
    * @author dwj
    * @date 1/8/2018 10:44
    */
    public Map<String,Object> getPersonDetail(ThridPersonBean bean){
        Map<String,Object> map = new HashMap<String, Object>();
        try{
            String id = bean.getId();
            PersonInfoBean personInfoBean = thirdPersonMapper.selectById(id);
            List<RecordPersonVo> vos = thirdPersonMapper.selectRecorPersonById(id);
            map.put("personInfo",personInfoBean);
            map.put("recordPersonInfos",vos);
        }catch (Exception e){
            throw new EscstException("获取人员详情信息失败"+e.getMessage(),e);
        }
        return map;
    }

    /**
    * @desc 获取工种列表
    * @param type
    * @return
    * @author dwj
    * @date 1/8/2018 14:30
    */
    public List<String> getCode(int type){
        return thirdPersonMapper.selectKindName(type);
    }


    /**
    * @desc 获取安全人员培训统计
    * @param bean
    * @return
    * @author dwj
    * @date 2/8/2018 15:57
    */
    public List<CountVo> getCountData(CountBean bean){
        List<CountVo> vos = new ArrayList<CountVo>();
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        Map<String,Object> paraMap = new HashMap<String, Object>();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        Map<String,Object> pMap = new HashMap<String, Object>();
        String departId = bean.getDepartId();
        try{
            List<Map<String,Object>> list = thirdPersonMapper.TrainPersonCount(bean);
            int type = bean.getType();
            if(type == 1){
                CountVo vo = new CountVo();
              Map<String,Object> map = list.get(0);
                //现在总人数
                long totalPersonNow = (Long)map.get("totalPersonNow");
                //培训总人数
                long totalPersonTrain = (Long)map.get("totalPersonTrain");
                //累计学时
                double totalTrainHour =(Double)map.get("totalTrainHour") == null?0:(Double)map.get("totalTrainHour");
                //累计培训人次
                long totalTrain = (Long)map.get("totalTrain");
                //人均培训次数
                int avgPersonTrain =0;
                if(totalPersonTrain != 0){
                    avgPersonTrain = (int)totalTrain/(int)totalPersonTrain;
                }
                //人均培训学时
                int avgPersonTrainHour = 0;
                if(totalPersonNow != 0){
                     avgPersonTrainHour = (int)totalTrainHour/(int)totalPersonNow;
                }
                //考试人数
                paraMap.put("isPasses","0,1");
                paraMap.put("fDeptID",departId);
                int totalPersonExam = thirdPersonMapper.TotalExamOrPass(paraMap);
                //合格人数
                dataMap.put("isPasses","1");
                dataMap.put("fDeptID",departId);
                int totalExamPass = thirdPersonMapper.TotalExamOrPass(dataMap);
                //合格率
                String examPassPercent ="0.0";
                if(totalPersonExam != 0){
                    examPassPercent = numberFormat.format((float)totalExamPass/(float)totalPersonExam*100);
                }
                vo.setTotalPersonNow((int)totalPersonNow);
                vo.setTotalPersonTrain((int)totalPersonTrain);
                vo.setTotalTrain((int)totalTrain);
                vo.setTotalTrainHour(String.valueOf(totalTrainHour));
                vo.setAvgPersonTrain(avgPersonTrain);
                vo.setAvgPersonTrainHour(avgPersonTrainHour);
                vo.setTotalPersonExam(totalPersonExam);
                vo.setTotalExamPass(totalExamPass);
                vo.setExamPassPercent(examPassPercent);
                vos.add(vo);
            }else{
                for(Map<String,Object> map : list){
                    CountVo vo = new CountVo();
                    String departName = (String)map.get("departName");
                    departId = (String) map.get("departId");
                    //现在总人数
                    long totalPersonNow = (Long)map.get("totalPersonNow");
                    //历史总人数
                    pMap.put("fDeptID",bean.getDepartId());
                    int totalPersonHistory = thirdPersonMapper.totalPerson(pMap);
                    //培训总人数
                    long totalPersonTrain = (Long)map.get("totalPersonTrain");
                    //累计学时
                    double totalTrainHour =(Double)map.get("totalTrainHour");
                    //累计培训人次
                    long totalTrain = (Long)map.get("totalTrain");
                    //人均培训次数
                    int avgPersonTrain =0;
                    if(totalPersonTrain != 0){
                        avgPersonTrain = (int)totalTrain/(int)totalPersonTrain;
                    }
                    //人均培训学时
                    int avgPersonTrainHour = 0;
                    if(totalPersonNow != 0){
                        avgPersonTrainHour = (int)totalTrainHour/(int)totalPersonNow;
                    }
                    //培训率
                    String trainPercent ="0.0";
                    if(totalPersonHistory !=0){

                        trainPercent = numberFormat.format((float)totalPersonTrain/(float)totalPersonHistory*100);
                    }
                    //考试人数
                    paraMap.put("fDeptID",departId);
                    paraMap.put("isPasses","0,1");
                    int totalPersonExam = thirdPersonMapper.TotalExamOrPass(paraMap);
                    //合格人数
                    dataMap.put("fDeptID",departId);
                    dataMap.put("isPasses","1");
                    int totalExamPass = thirdPersonMapper.TotalExamOrPass(dataMap);
                    //合格率
                    String examPassPercent ="0.0";
                    if(totalPersonExam != 0){
                         examPassPercent = numberFormat.format((float)totalExamPass/(float)totalPersonExam*100);
                    }
                    vo.setDepartId(departId);
                    vo.setDepartName(departName);
                    vo.setTotalPersonNow((int)totalPersonNow);
                    vo.setTotalPersonTrain((int)totalPersonTrain);
                    vo.setTotalTrain((int)totalTrain);
                    vo.setTotalTrainHour(String.valueOf(totalTrainHour));
                    vo.setAvgPersonTrain(avgPersonTrain);
                    vo.setAvgPersonTrainHour(avgPersonTrainHour);
                    vo.setTotalPersonHistory(totalPersonHistory);
                    vo.setTotalPersonExam(totalPersonExam);
                    vo.setTotalExamPass(totalExamPass);
                    vo.setExamPassPercent(examPassPercent);
                    vo.setTrainPercent(trainPercent);
                    vos.add(vo);
                }
            }
        }catch (Exception e){
            throw new EscstException("获取统计数据异常"+e.getMessage(),e);
        }
        return vos;
    }


    public List<BaseVO> getDepart(){
        List<BaseVO> vos = new ArrayList<BaseVO>();
        try{
            String topName = new String(bundle.getString("topName").getBytes("ISO-8859-1"), "UTF-8");
            vos = thirdPersonMapper.selectDepartList();
           BaseVO vo = new BaseVO();
           vo.setName(topName);
           vo.setId("");
           vos.add(vo);

        }catch (Exception e){
            throw new EscstException("获取项目部列表异常"+e.getMessage(),e);
        }
        return vos;
    }


    public List<ChartResultVo>  getChartData(CountBean bean){
        NumberFormat numberFormat = NumberFormat.getInstance();
        // 设置精确到小数点后2位
        numberFormat.setMaximumFractionDigits(2);
        List<ChartResultVo> vos = new ArrayList<ChartResultVo>();
        Map<String,Object> paraMap = new HashMap<String, Object>();
        Map<String,Object> pMap = new HashMap<String, Object>();
        Map<String,Object> dataMap = new HashMap<String, Object>();
        try{
            int value = bean.getValue();
            bean.setType(2);
            if(StringUtils.isEmpty(bean.getDepartId())){
                bean.setDepartId(null);
            }
            List<Map<String,Object>> list = thirdPersonMapper.TrainPersonCount(bean);
            for(Map<String,Object> map : list){
                String departName = (String)map.get("departName");
                String departId = (String)map.get("departId");
                //现在总人数
                long totalPersonNow = (Long)map.get("totalPersonNow");
                //历史总人数
                pMap.put("deptID",departId);
                int totalPersonHistory = thirdPersonMapper.totalPerson(pMap);
                //培训总人数
                long totalPersonTrain = (Long)map.get("totalPersonTrain");
                //累计学时
                double totalTrainHour =(Double)map.get("totalTrainHour") == null?0:(Double)map.get("totalTrainHour");
                //累计培训人次
                long totalTrain = (Long)map.get("totalTrain");
                ChartResultVo vo = new ChartResultVo();
                if(value == 0){
                    //现在总人数
                    vo.setQty(departName+":"+(int) totalPersonNow);
                    vos.add(vo);
                }else if(value == 1) {
                    List<Integer> arrs = new ArrayList<Integer>();
                    //培训总人数
                    vo.setName(departName);
                    arrs.add((int)totalPersonNow);
                    arrs.add((int)totalPersonTrain);
                    vo.setQty(arrs);
                    vos.add(vo);
                }else if(value == 2){
                    //培训率
                    String trainPercent ="";
                    if(totalPersonHistory !=0){
                        trainPercent = numberFormat.format((float)totalPersonTrain/(float)totalPersonHistory*100);
                    }
                    vo.setQty(trainPercent);
                    vo.setName(departName);
                    vos.add(vo);
                }else if(value == 3){
                    vo.setQty((int)totalTrain);
                    vo.setName(departName);
                    vos.add(vo);
                }else if(value == 4){
                    //人均培训次数
                    String avgPersonTrain ="";
                    if(totalPersonTrain != 0){
                        avgPersonTrain = numberFormat.format((float)totalTrain/(float)totalPersonTrain);
                    }
                    vo.setQty(avgPersonTrain);
                    vo.setName(departName);
                    vos.add(vo);
                }else if(value == 5){
                    vo.setQty((int)totalTrainHour);
                    vo.setName(departName);
                    vos.add(vo);
                }else if(value == 6){
                    //人均培训学时
                    int avgPersonTrainHour = 0;
                    if(totalPersonNow != 0){
                        avgPersonTrainHour = (int)totalTrainHour/(int)totalPersonNow;
                    }
                    vo.setQty(avgPersonTrainHour);
                    vo.setName(departName);
                    vos.add(vo);
                }else if(value == 7){
                    //考试人数
                    paraMap.put("deptID",departId);
                    paraMap.put("isPasses","0,1");
                    int totalPersonExam = thirdPersonMapper.TotalExamOrPass(paraMap);
                    vo.setName(departName);
                    vo.setQty(totalPersonExam);
                    vos.add(vo);
                }else if(value == 8){
                    //合格人数
                    dataMap.put("deptID",departId);
                    dataMap.put("isPasses","1");
                    int totalExamPass = thirdPersonMapper.TotalExamOrPass(dataMap);
                    vo.setQty(totalExamPass);
                    vo.setName(departName);
                    vos.add(vo);
                }else if(value == 9){
                    paraMap.put("deptID",departId);
                    paraMap.put("isPasses","0,1");
                    int totalPersonExam = thirdPersonMapper.TotalExamOrPass(paraMap);
                    dataMap.put("deptID",departId);
                    dataMap.put("isPasses","1");
                    int totalExamPass = thirdPersonMapper.TotalExamOrPass(dataMap);
                    //合格率
                    String examPassPercent = numberFormat.format((float)totalExamPass/(float)totalPersonExam*100);
                    vo.setQty(examPassPercent);
                    vo.setName(departName);
                    vos.add(vo);
                }
            }
        }catch (Exception e){
            throw new EscstException("获取图表数据异常"+e.getMessage(),e);
        }
        return vos;
    }


    /**
    * @desc 导出EXCEL
    * @param bean
    * @return
    * @author dwj
    * @date 7/8/2018 17:52
    */
    public List<TrainPersonExcelEntity> queryExportPersonList(ThridPersonBean bean) {
        List<TrainPersonExcelEntity> rst = new ArrayList<TrainPersonExcelEntity>();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("type",1);
        map.put("personName",bean.getPersonName());
        map.put("category",bean.getCategory());
        map.put("startDate",bean.getStartDate());
        map.put("endDate",bean.getEndDate());
        map.put("unitID",bean.getUnitID());
        map.put("ownerDeptID",bean.getOwnerDeptID());
        map.put("train",bean.getTrain());
        List<ThirdPersonVo> list =  thirdPersonMapper.selectAll(map);
        if (CollectionUtils.isEmpty(list)) {
            return rst;
        }
        for (ThirdPersonVo vo : list) {
            TrainPersonExcelEntity entity = new TrainPersonExcelEntity();
            entity.setName(vo.getPersonName());
            entity.setSex(vo.getSex());
            entity.setNation(vo.getNation());
            entity.setCategory(vo.getCategory());
            entity.setStation(vo.getStation());
            entity.setUnitName(vo.getUnitName());
            entity.setIdentifyId(vo.getIdentifyID());
            entity.setReadCardId(vo.getReadCardId());
            entity.setRegisterDate(vo.getRegisterDate());
            rst.add(entity);
        }
        return rst;
    }



}
