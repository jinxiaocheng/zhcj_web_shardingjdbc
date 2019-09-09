package com.escst.inspection.entity;

import java.io.Serializable;

/**
 * @author caozx
 * @desc
 * @date 2017/3/6 15:57
 */
public class AccidentLossDetailEntity implements Serializable {

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 6851187319454694833L;
	/**受伤**/
    public static final int ACCIDENT_LOSS_INJURED = 1;
    /**死亡**/
    public static final int ACCIDENT_LOSS_DEATH = 2;

    private String id;
    /**
     * 类型.1:受伤;2:死亡;
     **/
    private int type;
    /**
     * 事故报告ID
     **/
    private String accidentReportId;
    /**
     * 人员Id
     **/
    private String personId;
    /**
     * 备注
     **/
    private String remark;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getAccidentReportId() {
        return accidentReportId;
    }

    public void setAccidentReportId(String accidentReportId) {
        this.accidentReportId = accidentReportId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
