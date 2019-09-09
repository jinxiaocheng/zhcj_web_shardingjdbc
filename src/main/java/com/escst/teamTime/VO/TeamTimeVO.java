package com.escst.teamTime.VO;

import com.escst.team.entity.TeamEntity;

/**
 * @author jincheng
 * @desc 班组工时VO
 * @date 2018/2/5 15:41
 */
public class TeamTimeVO extends TeamEntity {

    /**
     * 开始日期
     */
    private String correctStartDate;

    /**
     * 结束日期
     */
    private String correctEndDate;

    /**
     * 日期
     */
    private String time;

    /**
     * 出勤人数
     */
    private Integer total;

    /**
     *总工时
     */
    private Double totalTime;

    /**
     * 班组ID
     */
    private String teamId;

    /**
     * 人员id
     */
    private String personId;

    /**
     * 人员姓名
     */
    private String personName;

    /**
     * 上午进场时间
     * @return
     */
    private String amUpTime;

    /**
     * 上午出场时间
     * @return
     */
    private String amDownTime;

    /*
    下午进场时间
     */
    private String pmUpTime;

    /*
    下午出场时间
     */
    private String pmDownTime;

    /*
    进场时间
     */
    private String upTime;

    /*
    出场时间
     */
    private String downTime;

    /*
    工作时长
     */
    private String workTime;

    // 工地名称
    private String constructionName;

    // 区域名称
    private String areaName;

    // 班组名称
    private String teamName;

    // 人员卡号
    private String cardNumber;

    // 打卡时间
    private String cardTime;

    // 进场/出场 1进 2出
    private String type;

    public String getCorrectStartDate() {
        return correctStartDate;
    }

    public void setCorrectStartDate(String correctStartDate) {
        this.correctStartDate = correctStartDate;
    }

    public String getCorrectEndDate() {
        return correctEndDate;
    }

    public void setCorrectEndDate(String correctEndDate) {
        this.correctEndDate = correctEndDate;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }

    public Double getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(Double totalTime) {
        this.totalTime = totalTime;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getAmUpTime() {
        return amUpTime;
    }

    public void setAmUpTime(String amUpTime) {
        this.amUpTime = amUpTime;
    }

    public String getAmDownTime() {
        return amDownTime;
    }

    public void setAmDownTime(String amDownTime) {
        this.amDownTime = amDownTime;
    }

    public String getPmUpTime() {
        return pmUpTime;
    }

    public void setPmUpTime(String pmUpTime) {
        this.pmUpTime = pmUpTime;
    }

    public String getPmDownTime() {
        return pmDownTime;
    }

    public void setPmDownTime(String pmDownTime) {
        this.pmDownTime = pmDownTime;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardTime() {
        return cardTime;
    }

    public void setCardTime(String cardTime) {
        this.cardTime = cardTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
