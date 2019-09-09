package com.escst.person.entity;

import com.escst.commons.entity.BaseEntity;

import java.util.Date;

/**
 * @author dwj
 * @desc
 * @date 13:42 2017/12/18
 */
public class AcsControlEntity extends BaseEntity {


    /**
     * 工地ID
     */
    private String constructionId;


    private String areaName;


    /**
     * 工地名称
     */
    private String constructionName;

    /**
     *门ID
     */
    private String doorId;

    private String id;

    private String name;

    /**
     * 设备编号
     */
    private String number;

    /**控制器的序列号*/
    private String equipmentSerial;

    /**控制器的自定义标识符*/
    private String equipmentId;

    /**创建时间*/
    private Date createTime;

    public String getEquipmentSerial() {
        return equipmentSerial;
    }

    public void setEquipmentSerial(String equipmentSerial) {
        this.equipmentSerial = equipmentSerial;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getDoorId() {
        return doorId;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }


}
