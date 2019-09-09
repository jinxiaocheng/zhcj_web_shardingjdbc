package com.escst.excelimport.service;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.utils.HttpClientUtils;
import com.escst.commons.utils.StringUtils;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.equipment.mapper.EquipmentManagerMapper;
import com.escst.equipment.vo.QueryVO;
import com.escst.excelimport.vo.PersonImportVO;
import com.escst.excelimport.vo.UpdatePersonVo;
import com.escst.person.entity.ConstructionPersonEntity;
import com.escst.person.entity.PersonEntity;
import com.escst.person.mapper.PersonMapper;
import com.escst.person.vo.PersonSyncVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dwj
 * @desc 同步人员信息
 * @date 15:37 2018/5/24
 */
@Service
public class PersonExcelImportSyncService {

    private static final Logger logger = LoggerFactory.getLogger(PersonExcelImportSyncService.class);

    @Autowired
    private PersonMapper personMapper;

    /**
    * @desc 同步Excel新增或者修改人员
    * @param list
    * @return
    * @author dwj
    * @date 2018/6/4 13:53
    */
    public  String syncPersonExcelImportToAccess(List<PersonEntity> list, String constructionId){
        logger.info("同步人员start person is {}", JSONObject.toJSON(list).toString());
        try{
            List<PersonSyncVo> vos = packData(list);
            //通过工地Id获取到同步的URL
            String url = personMapper.selectSyncUrlByConstructionId(constructionId);
            if(StringUtils.isBlank(url)){
                return null;
            }
            url += "/api/batchInsert";
            logger.info("门禁系统的接口地址:" + url);
             String response = HttpClientUtils.httpPostWithJSON(url, vos);
            logger.info("门禁系统返回的结果:" + response);
            JSONObject ob = JSONObject.parseObject(response);
            int status = ob.getIntValue("status");
            if (status == 1) {
                String value = ob.getString("value");
               List<UpdatePersonVo> personVos = JSONObject.parseArray(value,UpdatePersonVo.class);
               //批量修改同步返回信息
               if(!CollectionUtils.isEmpty(personVos)){
                   personMapper.updateBatchConstructionClientUserId(personVos);
               }
                return ob.getString("value");
            }
        }catch (Exception e){
            logger.error("同步Excel新增或者修改人员异常：", e);
        }
        logger.info("同步Excel新增或者修改人员end");
        return null;
    }

     //封装同步数据
    private List<PersonSyncVo> packData(List<PersonEntity> list){
        List<PersonSyncVo> vos = new ArrayList<PersonSyncVo>();
        for(PersonEntity vo : list){
            PersonSyncVo syncVo = new PersonSyncVo();
            syncVo.setPersonName(vo.getName());
            syncVo.setCardNo(vo.getJobNumber());
            syncVo.setMobile(vo.getMobile());
            syncVo.setCompanyName(vo.getProjectCompanyName());
            syncVo.setPositionWorktype(vo.getPositionWorkTypeName());
            syncVo.setIdCardNo(vo.getIdCard());
            syncVo.setJobNo(vo.getJobNumber());
            syncVo.setTeamName(vo.getTeamName());
            syncVo.setStatus(1);
            syncVo.setId(vo.getId());
            if(StringUtils.isNotBlank(vo.getHeadFilePath())){
             syncVo.setHeadFilePath(vo.getHeadFilePath());
            }
            if(StringUtils.isNotBlank(vo.getClientUserId())){
                syncVo.setPersonId(vo.getClientUserId());
            }else {
                syncVo.setPersonId("");
            }
            vos.add(syncVo);
        }

        return vos;
    }

}
