package com.escst.highformwork.vo;


import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 13:48 17/7/2018
 */
public class FlowVo {

    /**
     *@Fileds id
     **/
    private String id;

    /**
     *@Fileds name 流水段名
     **/
    private String name;

    /**
     *@Fileds areaId 区域id
     **/
    private String areaId;

    /**
     *@Fileds areaName 区域名
     **/
    private String areaName;


    /**
     *@Fileds constructionId 工地id
     **/
    private String constructionId;

    /**
     *@Fileds constructionName 工地名
     **/
    private String constructionName;

    /**
     *@Fileds qty 采集器数量
     **/
    private int qty;

    /**
     *@Fileds startTime 开始时间
     **/
    private String startTime;

    /**
     *@Fileds endTime 结束时间
     **/
    private String endTime;

    /**
     *@Fileds status
     **/
    private int status;

    /**
     *@Fileds filePath  测点部署图文件路径
     **/
    private String filePath;

    /**
     *@Fileds fileId 文件Id
     **/
    private String fileId;

    /**
     *@Fileds  collectorVos 采集器结合
     **/
    private List<CollectorVo> collectorVos;


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

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
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

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public List<CollectorVo> getCollectorVos() {
        return collectorVos;
    }

    public void setCollectorVos(List<CollectorVo> collectorVos) {
        this.collectorVos = collectorVos;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }
}
