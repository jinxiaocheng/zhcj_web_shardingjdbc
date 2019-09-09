package com.escst.attendance.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc
 * @author niejing
 * @date 2017年8月9日 下午3:18:28
 */
public class AttendanceResultEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -7782830621313139637L;

	private Integer id;

	/**
	 * @Fields personId 人员ID
	 */
	private String personId;

	/**
	 * @Fields cardNum 卡号
	 */
	private String cardNum;

	//人员姓名
	private String personName;

	/**
	 * @Fields constructionId 工地ID
	 */
	private String constructionId;

	//所属公司ID
	private String projectCompanyId;

	//班组ID
	private String teamId;

	//考勤日期
	private String attendanceDate;

	//上午进入时间
	private String amUpTime;

	//上午离开时间
	private String amDownTime;

	//下午进入时间
	private String pmUpTime;

	//下午离开时间
	private String pmDownTime;

	//工作时长
	private String workTime;

	//加班时长
	private String overTime;

	//区域
	private String areaId;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

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

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
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

	public String getAttendanceDate() {
		return attendanceDate;
	}

	public void setAttendanceDate(String attendanceDate) {
		this.attendanceDate = attendanceDate;
	}

	public String getAmUpTime() {
		return amUpTime;
	}

	public void setAmUpTime(String amUpTime) {
		this.amUpTime = amUpTime;
	}

	public String getAmDownTime() {
		return amDownTime;
	}

	public void setAmDownTime(String amDownTime) {
		this.amDownTime = amDownTime;
	}

	public String getPmUpTime() {
		return pmUpTime;
	}

	public void setPmUpTime(String pmUpTime) {
		this.pmUpTime = pmUpTime;
	}

	public String getPmDownTime() {
		return pmDownTime;
	}

	public void setPmDownTime(String pmDownTime) {
		this.pmDownTime = pmDownTime;
	}

	public String getWorkTime() {
		return workTime;
	}

	public void setWorkTime(String workTime) {
		this.workTime = workTime;
	}

	public String getOverTime() {
		return overTime;
	}

	public void setOverTime(String overTime) {
		this.overTime = overTime;
	}

	@Override
	public String getAreaId() {
		return areaId;
	}

	@Override
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getCardNum() {
		return cardNum;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}
}
