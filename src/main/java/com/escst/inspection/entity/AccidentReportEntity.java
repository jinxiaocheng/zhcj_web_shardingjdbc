package com.escst.inspection.entity;

import com.escst.commons.entity.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author caozx
 * @desc
 * @date 2017/3/6 15:57
 */
public class AccidentReportEntity extends BaseEntity {

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 6113884390653088443L;

	/**质量事故报告**/
    public static final int ACCIDENT_REPORT_QUALITY = 1;

    /**安全事故报告**/
    public static final int ACCIDENT_REPORT_SAFETY = 2;

    private String id;
    /**
     * 工地ID
     **/
    private String constructionId;
    /**
     * 类型.1:质量事故报告;2:安全事故报告
     **/
    private int type;
    /**
     * 事故日期
     **/
    private String accidentDate;
    /**
     * 事故简述
     **/
    private String resume;
    /**
     * 初步处理意见
     **/
    private String firstTreatment;
    /**
     * 事故等级
     * 质量:1:质量问题;2:一般事故;3:严重事故;4:重大事故;5:特别重大事故;
     * 安全:1:一般事故;2:较大事故;3:重大事故;4:特别重大事故;
     **/
    private int level;

    /**检查部位ID**/
    private String projectStructureId;
    /**分包公司ID**/
    private String projectCompanyId;
    /**班组ID**/
    private String teamId;
    /**联系人**/
    private String contactId;
    /**联系电话**/
    private String contactMobile;

    /**
     * 是否有附件.1:有附件;0:没有附件
     **/
    private int isAttach;

    /**
     * 附件照片,有附件的时候才有
     **/
    private MultipartFile[] files;

    /**伤亡类型集合**/
    private int[] lossTypes;


    /**受伤人员id集合**/
    private String[] injuredPersonIds;

    /**死亡人员id集合**/
    private String[] deathPersonIds;

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

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

    public String getProjectStructureId() {
        return projectStructureId;
    }

    public void setProjectStructureId(String projectStructureId) {
        this.projectStructureId = projectStructureId;
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

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public int getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(int isAttach) {
        this.isAttach = isAttach;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public int[] getLossTypes() {
        return lossTypes;
    }

    public void setLossTypes(int[] lossTypes) {
        this.lossTypes = lossTypes;
    }

    public String[] getInjuredPersonIds() {
        return injuredPersonIds;
    }

    public void setInjuredPersonIds(String[] injuredPersonIds) {
        this.injuredPersonIds = injuredPersonIds;
    }

    public String[] getDeathPersonIds() {
        return deathPersonIds;
    }

    public void setDeathPersonIds(String[] deathPersonIds) {
        this.deathPersonIds = deathPersonIds;
    }

}
