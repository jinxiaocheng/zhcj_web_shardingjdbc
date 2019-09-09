package com.escst.inspection.vo;

import com.escst.file.vo.FilePathVO;
import com.escst.person.entity.PersonEntity;
import com.escst.person.vo.PersonVO;

import java.util.Date;
import java.util.List;

/**
 * @author caozx
 * @desc  事故报告展示对象
 * @date 2017/08/10
 */
public class AccidentReportDetailVO {

    /**事故日期**/
    private String accidentDate;

    /**事故简述**/
    private String resume;

    /**初步处理意见**/
    private String firstTreatment;

    /**检查部位**/
    private String projectStructureName;

    /**分包公司**/
    private String projectCompanyName;

    /**班组**/
    private String teamName;

    /**联系人**/
    private String contactName;

    /**联系人电话**/
    private String contactMobile;

    /**事故等级
     质量:1:质量问题;2:一般事故;3:严重事故;4:重大事故;5:特别重大事故;
     安全:1:一般事故;2:较大事故;3:重大事故;4:特别重大事故;**/
    private Integer level;

    /**是否有附件**/
    private Integer isAttach;

    /**受伤人员集合**/
    private List<PersonVO> injuredPersonList;

    /**死亡人员集合**/
    private List<PersonVO> deathPersonList;

    /**图片附件路径集合**/
    private List<FilePathVO> picList;


    public String getAccidentDate() {
        return accidentDate;
    }

    public void setAccidentDate(String accidentDate) {
        this.accidentDate = accidentDate;
    }

    public String getResume() {
        return resume;
    }

    public void setResume(String resume) {
        this.resume = resume;
    }

    public String getFirstTreatment() {
        return firstTreatment;
    }

    public void setFirstTreatment(String firstTreatment) {
        this.firstTreatment = firstTreatment;
    }

    public String getProjectStructureName() {
        return projectStructureName;
    }

    public void setProjectStructureName(String projectStructureName) {
        this.projectStructureName = projectStructureName;
    }

    public String getProjectCompanyName() {
        return projectCompanyName;
    }

    public void setProjectCompanyName(String projectCompanyName) {
        this.projectCompanyName = projectCompanyName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public Integer getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(Integer isAttach) {
        this.isAttach = isAttach;
    }

    public List<FilePathVO> getPicList() {
        return picList;
    }

    public void setPicList(List<FilePathVO> picList) {
        this.picList = picList;
    }

    public List<PersonVO> getInjuredPersonList() {
        return injuredPersonList;
    }

    public void setInjuredPersonList(List<PersonVO> injuredPersonList) {
        this.injuredPersonList = injuredPersonList;
    }

    public List<PersonVO> getDeathPersonList() {
        return deathPersonList;
    }

    public void setDeathPersonList(List<PersonVO> deathPersonList) {
        this.deathPersonList = deathPersonList;
    }
}
