package com.escst.highformwork.entity;

import com.escst.commons.entity.BaseEntity;
import com.escst.commons.entity.BasePageEntity;

import java.util.Date;

/**
 * @author dwj
 * @desc
 * @date 16:24 16/7/2018
 */
public class FlowEntity extends BaseEntity {

    /**
     *@Fileds id
     **/
    private String id;
    /**
     *@Fileds constructionId
     **/
    private String constructionId;
    /**
     *@Fileds  constructionName 工地名
     **/
    private String constructionName;
    /**
     *@Fileds name 流水段名
     **/
    private String name;
    /**
     *@Fileds fileId 测点部署图文件Id
     **/
    private String fileId;

    /**
     *@Fileds startTime 开始时间
     **/
    private Date startTime;
    /**
     *@Fileds endTime 结束时间
     **/
    private Date endTime;
    /**
     *@Fileds status 状态  1:测量中；2：结束测量
     **/
    private int status;


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

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }


    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


}
