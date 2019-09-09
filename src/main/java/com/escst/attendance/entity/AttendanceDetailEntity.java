package com.escst.attendance.entity;


/**
 * @author dwj
 * @desc
 * @date 15:34 2018/4/2
 */
public class AttendanceDetailEntity {

    private String personId;
    private String personName;
    private String projectCompanyId;
    private String teamId;
    private String construction_id;

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getProjectCompanyId() {
        return projectCompanyId;
    }

    public void setProjectCompanyId(String projectCompanyId) {
        this.projectCompanyId = projectCompanyId;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getConstruction_id() {
        return construction_id;
    }

    public void setConstruction_id(String construction_id) {
        this.construction_id = construction_id;
    }
}
