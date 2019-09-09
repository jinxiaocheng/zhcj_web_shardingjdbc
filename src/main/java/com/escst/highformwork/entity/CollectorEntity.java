package com.escst.highformwork.entity;


import com.escst.commons.entity.BaseEntity;
import com.escst.commons.entity.BasePageEntity;

/**
 * @author dwj
 * @desc
 * @date 16:09 16/7/2018
 */
public class CollectorEntity extends BaseEntity {

    /**
     *@Fileds id
     **/
    private String id;
    /**
     *@Fileds constructionId 工地Id
     **/
    private String constructionId;

    /**
     *@Fileds constructionName 工地名
     **/
    private String constructionName;
    /**
     *@Fileds name 采集器名称
     **/
    private String name;
    /**
     *@Fileds number 采集器编号
     **/
    private String number;
    /**
     *@Fileds status 状态  1、启用；0：停用
     **/
    private int status;

    /**
     *@Fileds flowId 流水段Id
     **/
    private String flowId;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }
}
