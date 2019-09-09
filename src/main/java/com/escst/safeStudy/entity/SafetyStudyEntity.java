package com.escst.safeStudy.entity;

import com.escst.commons.entity.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author caozx
 * @desc
 * @date 2017/3/7 15:03
 */
public class SafetyStudyEntity extends BaseEntity {
    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 6494890556782073046L;
	private String id;
    /**工地ID**/
    private String constructionId;
    /**类型.1:安全培训;2:安全演习**/
    private int type;
    /**开始时间**/
    private String startDate;
    /**结束时间**/
    private String endDate;
    /**主题**/
    private String theme;
    /**地点**/
    private String place;
    /**负责人姓名**/
    private String contactPerson;

    /**是否有附件**/
    private int isAttach;

    /**
     * 附件照片,有附件的时候才有
     **/
    private MultipartFile[] files;

    /**参与人员id集合**/
    private String[] personIds;


    /**备注**/
    private String remark;

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

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getTheme() {
        return theme;
    }

    public void setTheme(String theme) {
        this.theme = theme;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getContactPerson() {
        return contactPerson;
    }

    public void setContactPerson(String contactPerson) {
        this.contactPerson = contactPerson;
    }

    public int getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(int isAttach) {
        this.isAttach = isAttach;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public String[] getPersonIds() {
        return personIds;
    }

    public void setPersonIds(String[] personIds) {
        this.personIds = personIds;
    }

}
