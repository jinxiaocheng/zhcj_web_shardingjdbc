package com.escst.highformwork.bean;

import org.springframework.web.multipart.MultipartFile;


/**
 * @author dwj
 * @desc
 * @date 10:28 17/7/2018
 */
public class FlowBean {

    /**
     *@Fileds id
     **/
    private String id;
    /**
     *@Fileds  name 流水段名称
     **/
    private String name;
    /**
     *@Fileds number 编号
     **/
    private String number;
    /**
     *@Fileds file 文件
     **/
    private MultipartFile file;

    /**
     *@Fileds startTime 开始时间
     **/
    private String startTime;

    /**
     *@Fileds endTime 结束时间
     **/
    private String endTime;

    /**
     *@Fileds constructionId 工地Id
     **/
    private String constructionId;

    /**
     *@Fileds constructionName 工地名
     **/
    private String constructionName;

    /**
     *@Fileds ids 采集器id集合
     **/
    private String ids ;

    /**
     *@Fileds createBy 操作人
     **/
    private String createBy;

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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
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

    public String getIds() {
        return ids;
    }

    public void setIds(String ids) {
        this.ids = ids;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
