package com.escst.safeStudy.vo;

import com.escst.file.vo.FilePathVO;
import com.escst.person.entity.PersonEntity;
import java.util.List;
import java.util.Map;

/**
 * @author caozx
 * @desc
 * @date 2017/3/7 15:03
 */
public class SafetyStudyDetailVO {
    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 6494890556782073046L;
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

    /**附件路径集合**/
    private List<FilePathVO> picList;

    /**参与人员集合**/
    private List<Map<String,Object>> personList;

    /**备注**/
    private String remark;

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

    public List<FilePathVO> getPicList() {
        return picList;
    }

    public void setPicList(List<FilePathVO> picList) {
        this.picList = picList;
    }

    public List<Map<String, Object>> getPersonList() {
        return personList;
    }

    public void setPersonList(List<Map<String, Object>> personList) {
        this.personList = personList;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
