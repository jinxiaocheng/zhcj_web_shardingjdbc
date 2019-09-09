package com.escst.attendance.bean;

/**
 * @author dwj
 * @desc
 * @date 16:02 20/7/2018
 */
public class AttendanceNewBean {

    /**
     *@Fileds belongArea 区域编码
     **/
    private String belongArea;
    /**
     *@Fileds constructionId 工地Id
     **/
    private String constructionId;

    /**
     *@Fileds areaName 区域名称
     **/
    private String areaName;
    /**
     *@Fileds constructionName 工地名
     **/
    private String constructionName;
    /**
     *@Fileds companyId 分包名
     **/
    private String companyId;

    /**
     *@Fileds companyName 公司名
     **/
    private String companyName;
    /**
     *@Fileds Integer totalNum 总人数
     **/
    private Integer totalNum;


    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
