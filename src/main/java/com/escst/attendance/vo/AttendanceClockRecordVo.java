package com.escst.attendance.vo;

import com.escst.commons.entity.BaseEntity;

/**
 * @author dwj
 * @desc
 * @date 9:14 2018/1/17
 */
public class AttendanceClockRecordVo extends BaseEntity {

    /**
     * @Fields serialVersionUID
	 */
    private static final long serialVersionUID = -8775121033076876481L;
    /**
     *@Fields 人员姓名
     */
    private String personName;
 
    /**
     *@Fields 工地名称
     */
    private String constructionName;
    /**
     *@Fields 区域名称
     */
    private String areaName;
    /**
     *@Fields 打卡日期
     */
    private String attendanceDate;
    /**
     *@Fields 分包公司名称
     */
    private String companyName;

    /**
     * @Fields 分包公司Id
     **/
    private String companyId;

    /**
     *@Fields 卡号
     */
    private String cardNum;
    /**
     *@Fields  1进场2出场
     */
    private int type;
    /**
     *@Fields 岗位/工种
     */
    private String positionWorktype;
    /**
     *@Fields 班组
     */
    private String teamName;
    /**
     *@Fields 工地Id
     */
    private String constructionId;

    /**
     *@Fields 开始时间
     */
    private String startDate;
    /**
     *@Fields 结束时间
     */
    private String endDate;

    private int state;

    private String personId;

    private String positionWorkId;
    
    
    public String getPositionWorkId() {
		return positionWorkId;
	}

	public void setPositionWorkId(String positionWorkId) {
		this.positionWorkId = positionWorkId;
	}

	public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public String getAttendanceDate() {
        return attendanceDate;
    }

    public void setAttendanceDate(String attendanceDate) {
        this.attendanceDate = attendanceDate;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCardNum() {
        return cardNum;
    }

    public void setCardNum(String cardNum) {
        this.cardNum = cardNum;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getPositionWorktype() {
		return positionWorktype;
	}

	public void setPositionWorktype(String positionWorktype) {
		this.positionWorktype = positionWorktype;
	}

	public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }
}
