package com.escst.person.vo;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 10:12 2018/5/28
 */
public class PersonSyncVo {


    private String id;
    private String personId;

    private String personName;

    private List<String> number;

    private String companyName;

    private String teamName;

    private String positionWorktype;

    private String mobile;

    private int sex;

    private String cardNo;

    private String jobNo;

    private String IdCardNo;

    private String ConstructionId;

//    private String headPortraitFile;

    private String headFilePath;

    //1是新增2是修改3是删除
    private int status;


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

    public List<String> getNumber() {
        return number;
    }

    public void setNumber(List<String> number) {
        this.number = number;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getPositionWorktype() {
        return positionWorktype;
    }

    public void setPositionWorktype(String positionWorktype) {
        this.positionWorktype = positionWorktype;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
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

    public String getIdCardNo() {
        return IdCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        IdCardNo = idCardNo;
    }

    public String getConstructionId() {
        return ConstructionId;
    }

    public void setConstructionId(String constructionId) {
        ConstructionId = constructionId;
    }

    public String getJobNo() {
        return jobNo;
    }

    public void setJobNo(String jobNo) {
        this.jobNo = jobNo;
    }

    public String getHeadFilePath() {
        return headFilePath;
    }

    public void setHeadFilePath(String headFilePath) {
        this.headFilePath = headFilePath;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
