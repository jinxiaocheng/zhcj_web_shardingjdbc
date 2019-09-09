package com.escst.task.bean;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 19:38 2017/4/10
 */
public class PersonProjectCompanyBean {

    private String projectCompanyId;
    private String projectCompanyName;
    private Integer projectCompanyType;
    private String userId;
    private String personName;
    private String personMobile;
    private String teamId;
    private String teamName;
    private List<?> data;

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }

    public String getProjectCompanyId() {
        return projectCompanyId;
    }

    public void setProjectCompanyId(String projectCompanyId) {
        this.projectCompanyId = projectCompanyId;
    }

    public String getProjectCompanyName() {
        return projectCompanyName;
    }

    public void setProjectCompanyName(String projectCompanyName) {
        this.projectCompanyName = projectCompanyName;
    }

    public Integer getProjectCompanyType() {
        return projectCompanyType;
    }

    public void setProjectCompanyType(Integer projectCompanyType) {
        this.projectCompanyType = projectCompanyType;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getPersonMobile() {
        return personMobile;
    }

    public void setPersonMobile(String personMobile) {
        this.personMobile = personMobile;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
