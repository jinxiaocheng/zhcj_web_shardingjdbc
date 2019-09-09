package com.escst.attendance.vo;

/**
 * @author dwj
 * @desc
 * @date 10:44 20/7/2018
 */
public class AttendanceNewVo {


    /**
     *@Fileds areaName 区域名称
     **/
    private String areaName;

    /**
     * @Fileds constructionId 工地名
     **/
    private String constructionId;
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
    /**
     *@Fileds Integer turnOutNum 出勤人数
     **/
    private Integer turnOutNum;
    /**
     *@Fileds Integer presenceNum 在场人数
     **/
    private Integer persenceNum;
    /**
     *@Fileds Float attendancePercent 出勤率
     **/
    private String attendancePercent;


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

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }

    public Integer getTurnOutNum() {
        return turnOutNum;
    }

    public void setTurnOutNum(Integer turnOutNum) {
        this.turnOutNum = turnOutNum;
    }

    public Integer getPersenceNum() {
        return persenceNum;
    }

    public void setPersenceNum(Integer persenceNum) {
        this.persenceNum = persenceNum;
    }

    public String getAttendancePercent() {
        return attendancePercent;
    }

    public void setAttendancePercent(String attendancePercent) {
        this.attendancePercent = attendancePercent;
    }
}
