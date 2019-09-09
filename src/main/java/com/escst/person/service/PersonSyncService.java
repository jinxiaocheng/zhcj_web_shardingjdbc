package com.escst.person.service;

import com.alibaba.fastjson.JSONObject;
import com.escst.commons.utils.HttpClientUtils;
import com.escst.commons.utils.ResourceUtil;
import com.escst.commons.utils.StringUtils;
import com.escst.commons.vo.BaseAuthVO;
import com.escst.equipment.mapper.EquipmentManagerMapper;
import com.escst.equipment.vo.QueryVO;
import com.escst.excelimport.vo.UpdatePersonVo;
import com.escst.person.entity.ConstructionPersonEntity;
import com.escst.person.entity.PersonEntity;
import com.escst.person.mapper.PersonMapper;
import com.escst.person.vo.PersonSyncVo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dwj
 * @desc 同步人员信息
 * @date 15:37 2018/5/24
 */
@Service
public class PersonSyncService {

    private static final Logger logger = LoggerFactory.getLogger(PersonSyncService.class);



    @Autowired
    private PersonMapper personMapper;

    /**
    * @desc 同步到新增人员信息到门禁系统
    * @param personEntity
    * @return
    * @author dwj
    * @date 2018/6/6 13:18
    */
    public  String syncPersonToAccess(PersonEntity personEntity){
        logger.info("同步人员start person is {}", personEntity.getName());
        try{
            PersonSyncVo vo = packData(personEntity);
            //通过工地Id获取到同步的URL
            String url = personMapper.selectSyncUrlByConstructionId(personEntity.getConstructionId());
            if(StringUtils.isBlank(url)){
                return null;
            }
            url += "/api/saveOrUpdateUser";
            logger.info("门禁系统的接口地址:" + JSONObject.toJSON(vo).toString());
            logger.info("门禁系统的接口地址:" + url);
             String response = HttpClientUtils.httpPostWithJSON(url, vo);
            logger.info("门禁系统返回的结果:" + response);
            JSONObject ob = JSONObject.parseObject(response);
            int status = ob.getIntValue("status");
            if (status == 1) {
                if(StringUtils.isBlank(personEntity.getClientUserId())){
                    List<UpdatePersonVo> personVos = new ArrayList<UpdatePersonVo>();
                    //如果是新增同步成功修改client_user_id
                    UpdatePersonVo entity = new UpdatePersonVo();
                    entity.setId(personEntity.getId());
                    entity.setClientUserId(ob.getString("value"));
                    personVos.add(entity);
                    personMapper.updateBatchConstructionClientUserId(personVos);
                }
                return ob.getString("value");
            }
        }catch (Exception e){
            logger.error("同步新增或者修改人员异常：", e);
        }
        logger.info("同步新增或者修改人员end");
        return null;
    }

     //封装同步数据
    private PersonSyncVo packData(PersonEntity personEntity){
        QueryVO queryVO = new QueryVO();
        queryVO.setConstructionId(personEntity.getConstructionId());
        queryVO.setType(2);
        PersonSyncVo vo = new PersonSyncVo();
        if(personEntity.getState() == 3){
            vo.setPersonId(personEntity.getClientUserId());
            vo.setPersonName(personEntity.getName());
            vo.setStatus(personEntity.getState());
        }else{
            vo.setPersonName(personEntity.getName());
            vo.setCardNo(personEntity.getCardNumber());
            vo.setMobile(personEntity.getMobile());
            vo.setCompanyName(personEntity.getProjectCompanyName());
            vo.setPositionWorktype(personEntity.getPositionWorkTypeName());
            vo.setIdCardNo(personEntity.getIdCard());
            vo.setJobNo(personEntity.getCardNumber());
            vo.setTeamName(personEntity.getTeamName());
            int sex = personEntity.getSex() ==0 ? 2:1;
            vo.setSex(sex);
            vo.setStatus(personEntity.getState());
            if(StringUtils.isNotBlank(personEntity.getHeadFilePath())){
                vo.setHeadFilePath(personEntity.getHeadFilePath());
            }
            if(StringUtils.isNotBlank(personEntity.getClientUserId())){
                vo.setPersonId(personEntity.getClientUserId());
            }else {
                vo.setPersonId("");
            }
        }
        return vo;
    }

}
