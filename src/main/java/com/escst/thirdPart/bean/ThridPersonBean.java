package com.escst.thirdPart.bean;

import com.escst.commons.entity.BaseEntity;

/**
 * @author dwj
 * @desc
 * @date 17:09 30/7/2018
 */
public class ThridPersonBean  extends BaseEntity{

    /**
     *@Fileds id
     **/
    private String id;

    /**
     *@Fileds category 岗位/工种
     **/
    private String category;
    /**
     *@Fileds  personName 姓名
     **/
    private String personName;

    /**
     *@Fileds startDate 开始时间
     **/
    private String startDate;
    /**
     *@Fileds endDate 结束时间
     **/
    private String endDate;
    /**
     *@Fileds unitID 所属单位ID
     **/
    private String unitID;
    /**
     *@Fileds ownerDeptID 所属项目部Id
     **/
    private String ownerDeptID;
    /**
     *@Fileds train 培训
     **/
    private int train;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
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

    public String getUnitID() {
        return unitID;
    }

    public void setUnitID(String unitID) {
        this.unitID = unitID;
    }

    public String getOwnerDeptID() {
        return ownerDeptID;
    }

    public void setOwnerDeptID(String ownerDeptID) {
        this.ownerDeptID = ownerDeptID;
    }

    public int getTrain() {
        return train;
    }

    public void setTrain(int train) {
        this.train = train;
    }
}
