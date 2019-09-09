package com.escst.attendance.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 考勤
 * @author zhouwei
 * @date 2017年7月12日 下午1:31:52
 */
public class AttendanceClockRecordEntity implements Serializable {

	private Integer id;
	
	/**
	 * @Fields personId 人员ID
	 */
	private String personId;
	
	/**
	 * @Fields constructionId 工地ID
	 */
	private String constructionId;
	
	/**
	 * @Fields clockTime 打卡时间
	 */
	private Date clockTime;
	
	/**
	 * @Fields dataSource 数据来源.1:接口传入;2:后台excel导入
	 */
	private int dataSource;
	
	/**
	 * @Fields type 打卡类型。1：进场;2：出场
	 */
	private int type;
	
	/**
	 * @Fields equipmentId 设备ID
	 */
	private String equipmentId;
	
	/**
	 * @Fields jobNumber 工号
	 */
	private String jobNumber;
	
	/**
	 * @Fields recogType 思源科安打卡类型.(iris:虹膜;face:人脸;ic:刷卡;password:密码;)
	 */
	private String recogType;
	
	/**
	 * @Fields clockDate 打卡日期,格式:2017-07-12
	 */
	private String clockDate;
	
	/**
	 * @Fields clockMinute 打卡时分信息.格式:13:02
	 */
	private String clockMinute;
	
	private Date createTime;
	
	private String createBy;

	private String peojectCompanyId;
	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 1514666092242498509L;

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

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public Date getClockTime() {
		return clockTime;
	}

	public void setClockTime(Date clockTime) {
		this.clockTime = clockTime;
	}

	public int getDataSource() {
		return dataSource;
	}

	public void setDataSource(int dataSource) {
		this.dataSource = dataSource;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getRecogType() {
		return recogType;
	}

	public void setRecogType(String recogType) {
		this.recogType = recogType;
	}

	public String getClockDate() {
		return clockDate;
	}

	public void setClockDate(String clockDate) {
		this.clockDate = clockDate;
	}

	public String getClockMinute() {
		return clockMinute;
	}

	public void setClockMinute(String clockMinute) {
		this.clockMinute = clockMinute;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getPeojectCompanyId() {
		return peojectCompanyId;
	}

	public void setPeojectCompanyId(String peojectCompanyId) {
		this.peojectCompanyId = peojectCompanyId;
	}
}
