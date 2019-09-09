package com.escst.person.entity;

/**
 * @author dwj
 * @desc
 * @date 14:47 2018/4/2
 */
public class UserEntity {

    private String name;
    private int sex;
    private String cardNo;
    private String jobNo;
    private String idCaedNo;
    private String mobile;
    private String companyName;
    private String positionWorkTypeName;
    private String teamName;
    private int status;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSex() {
        return sex;
    }

    public void setSex(int sex) {
        this.sex = sex;
    }

    public String getCardNo() {
        return cardNo;
    }

    public void setCardNo(String cardNo) {
        this.cardNo = cardNo;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getIdCaedNo() {
        return idCaedNo;
    }

    public void setIdCaedNo(String idCaedNo) {
        this.idCaedNo = idCaedNo;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPositionWorkTypeName() {
        return positionWorkTypeName;
    }

    public void setPositionWorkTypeName(String positionWorkTypeName) {
        this.positionWorkTypeName = positionWorkTypeName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
