package com.escst.person.service;

import com.escst.commons.exception.EscstException;
import com.escst.commons.utils.UuidUtils;
import com.escst.commons.vo.PageVo;
import com.escst.person.entity.DoorEntity;
import com.escst.person.mapper.DoorMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import java.util.*;


/**
 * @author jincheng
 * @desc 门相关
 * @date 2018-5-20 13:51
 */
@Service
public class DoorService {

    @Autowired
    private DoorMapper doorMapper;

    /**
     * @return
     * @desc 获得门的列表
     * @author jincheng
     * @date 2018-5-20 13:51
     */
    public PageVo queryList(DoorEntity entity) {
        PageVo pageVo = new PageVo();
        try {
            Integer count = doorMapper.getCount(entity.getConstructionId());
            if (count == 0) {
                return pageVo;
            }
            // 当前页号
            pageVo.setCurrentPage(entity.getPage());
            // 总记录数
            pageVo.setTotalRecord(count);

            pageVo.setPageSize(entity.getRowNum());
            // 每页的第一条记录的索引
            int startIndex = (entity.getPage() - 1) * (entity.getRowNum());
            entity.setStartIndex(startIndex);
            List<DoorEntity> list = doorMapper.listAll(entity);

            for (DoorEntity doorEntity : list) {
                List<String> controllerNameList = doorMapper.listControllerName(doorEntity.getId());
                doorEntity.setController(org.apache.commons.lang3.StringUtils.join(controllerNameList, ","));
            }
            pageVo.setRows(list);

        } catch (Exception e) {
            throw new EscstException("查询门列表失败" + e.getMessage(), e);
        }
        return pageVo;
    }


    /**
     * @return
     * @desc 获得门的列表
     * @author jincheng
     * @date 2018-5-20 13:51
     */
    public Map<Object, Collection> listDoor(String personId) {
        Map<Object, Collection> map = new HashMap<Object, Collection>();
        String constructionId = doorMapper.getConstructionId(personId);
        // 工地对应的门
        List<DoorEntity> constructionDoorList = doorMapper.listDoor(constructionId);
        // 人对应的门
        List<DoorEntity> personDoorList = doorMapper.listDoorByPersonId(personId);
        map.put("cList", constructionDoorList);
        map.put("pList", personDoorList);
        return map;
    }


    /**
     * 新增门
     *
     * @param entity
     */
    @Transactional
    public void add(DoorEntity entity) {
        try {
            String id = UuidUtils.getUuid();
            entity.setId(id);
            entity.setCreateTime(new Date());
            entity.setUpdateTime(new Date());
            doorMapper.add(entity);
            // 保存门和控制器的关系
            if (!CollectionUtils.isEmpty(entity.getControllerId())) {
                doorMapper.saveDoorAndController(entity);
            }
        } catch (Exception e) {
            throw new EscstException("新增门" + e.getMessage(), e);
        }
    }


    /**
     * @param doorId
     * @return
     * @desc 处理门的编辑
     * @author jincheng
     * @date 2018-5-21 10:07
     */
    public DoorEntity listController(String doorId) {
        try {
            DoorEntity entity = new DoorEntity();
            List<DoorEntity> list = doorMapper.listController(doorId);
            if (!CollectionUtils.isEmpty(list)) {
                DoorEntity doorEntity = list.get(0);
                entity.setDoorId(doorEntity.getDoorId());
                entity.setConstructionName(doorEntity.getConstructionName());
                entity.setAreaName(doorEntity.getAreaName());
                entity.setDoorName(doorEntity.getDoorName());
                entity.setDoorList(list);
            } else {
                entity = doorMapper.getDoorDetail(doorId);
            }
            return entity;
        } catch (Exception e) {
            throw new EscstException("处理门的编辑异常" + e.getMessage(), e);
        }
    }


    /**
     * @param entity
     * @return void
     * @desc 编辑门
     * @author jincheng
     * @date 2018-5-21 11:01
     */
    @Transactional
    public void updateDoor(DoorEntity entity) {
        try {
            doorMapper.updateById(entity);
            // 先删除门对应的所有控制器
            doorMapper.delete(entity);
            // 保存门和控制器
            if (!CollectionUtils.isEmpty(entity.getControllerId())) {
                doorMapper.saveDoorAndController(entity);
            }
        } catch (Exception e) {
            throw new EscstException("编辑门异常" + e.getMessage(), e);
        }
    }

    /**
     * @param entity
     * @return void
     * @desc 给人员分配门
     * @author jincheng
     * @date 2018-5-31 17:00
     */
    @Transactional
    public void allotDoor(DoorEntity entity) {
        try {
            // 先删除人员对应的门
            doorMapper.deleteByPersonId(entity.getPersonId());
            if (!CollectionUtils.isEmpty(entity.getDoorIds())) {
                List<DoorEntity> doorList = new ArrayList<DoorEntity>();
                for (String doorId : entity.getDoorIds()) {
                    DoorEntity doorEntity = new DoorEntity();
                    doorEntity.setId(UuidUtils.getUuid());
                    doorEntity.setPersonId(entity.getPersonId());
                    doorEntity.setDoorId(doorId);
                    doorEntity.setCreateTime(new Date());
                    doorList.add(doorEntity);
                }
                doorMapper.saveDoorAndPerson(doorList);
            }

        } catch (Exception e) {
            throw new EscstException("编辑门异常" + e.getMessage(), e);
        }

    }
}
