package com.escst.person.entity;

import com.escst.commons.entity.BaseEntity;

import java.util.List;

/**
 * @author jincheng
 * @desc 门相关
 * @date 2018-5-20 12:43
 */
public class DoorEntity extends BaseEntity {


    private String id;

    private String name;

    private String doorName;

    private String areaName;

    /**
     * 门对应的控制器
     */
    private String controller;


    /**
     * 门对应的控制器ID数组
     */
    private List<String> controllerId;


    /**
     * 工地ID
     */
    private String constructionId;


    /**
     * 工地名称
     */
    private String constructionName;


    /**
     * 门ID
     */
    private String doorId;

    private String personId;

    private List<String> doorIds;

    private List<AcsControlEntity> acsControlEntityList;

    private List<DoorEntity> doorList;

    public String getController() {
        return controller;
    }

    public void setController(String controller) {
        this.controller = controller;
    }

    public List<String> getControllerId() {
        return controllerId;
    }

    public void setControllerId(List<String> controllerId) {
        this.controllerId = controllerId;
    }

    public String getDoorId() {
        return doorId;
    }

    public void setDoorId(String doorId) {
        this.doorId = doorId;
    }

    public List<AcsControlEntity> getAcsControlEntityList() {
        return acsControlEntityList;
    }

    public void setAcsControlEntityList(List<AcsControlEntity> acsControlEntityList) {
        this.acsControlEntityList = acsControlEntityList;
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


    public List<String> getDoorIds() {
        return doorIds;
    }

    public void setDoorIds(List<String> doorIds) {
        this.doorIds = doorIds;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getDoorName() {
        return doorName;
    }

    public void setDoorName(String doorName) {
        this.doorName = doorName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<DoorEntity> getDoorList() {
        return doorList;
    }

    public void setDoorList(List<DoorEntity> doorList) {
        this.doorList = doorList;
    }
}
