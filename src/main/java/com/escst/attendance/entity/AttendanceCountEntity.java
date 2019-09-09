package com.escst.attendance.entity;

import com.escst.commons.entity.BaseEntity;

import java.util.Date;

/**
 * @author dwj
 * @desc
 * @date 9:49 2017/10/30
 */
public class AttendanceCountEntity extends BaseEntity {

    private String id;

    //工地Id
    private String constructionId;

    private String constructionName;

    private String areaName;

    //班组名
    private String teamName;

    //考勤日期
    private String attendanceDate;

    //出勤总人数
    private int count;

    private String [] teamNameArray;
    private String[] countArray;

    //创建时间
    private Date createTime;

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

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }




    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String[] getTeamNameArray() {
        return teamNameArray;
    }

    public void setTeamNameArray(String[] teamNameArray) {
        this.teamNameArray = teamNameArray;
    }

    public String[] getCountArray() {
        return countArray;
    }

    public void setCountArray(String[] countArray) {
        this.countArray = countArray;
    }
}
