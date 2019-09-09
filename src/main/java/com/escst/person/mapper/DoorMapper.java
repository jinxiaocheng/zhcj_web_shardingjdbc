package com.escst.person.mapper;

import com.escst.commons.mapper.BaseMapper;
import com.escst.person.entity.DoorEntity;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * @author jincheng
 * @desc 门相关
 * @date 2018-5-20 12:38
 */
@Repository
public interface DoorMapper extends BaseMapper<DoorEntity> {

    /**
     * @param serial
     * @return
     * @desc 通过控制器编号查询门的ID
     * @author jincheng
     * @date 2018-5-20 13:24
     */
    String getDoorIdBySerial(String serial);


    /**
     * @param
     * @return
     * @desc 查询门的数量
     * @author jincheng
     * @date 2018-5-20 13:53
     */
    int getCount(String constructionId);


    /**
     * @param
     * @return
     * @desc 查询所有门
     * @author jincheng
     * @date 2018-5-20 13:48
     */
    List<DoorEntity> listAll(DoorEntity doorEntity);


    List<DoorEntity> listDoor(String constructionId);

    List<DoorEntity> listDoorByPersonId(String personId);


    /**
     * @param doorId
     * @return
     * @desc 查询门对应的控制器列表名称
     * @author jincheng
     * @date 2018-5-20 14:19
     */
    List<String> listControllerName(String doorId);


    void add(DoorEntity entity);

    /**
     * 保存门和控制器的关系
     */
    void saveDoorAndController(DoorEntity entity);


    List<DoorEntity> listController(String doorId);

    void delete(DoorEntity entity);

    String getConstructionId(String personId);

    void deleteByPersonId(String personId);


    void saveDoorAndPerson(List<DoorEntity> list);

    DoorEntity getDoorDetail(String doorId);
}
