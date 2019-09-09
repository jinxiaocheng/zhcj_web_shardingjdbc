package com.escst.attendance.entity;

import java.io.Serializable;

import javax.validation.constraints.NotNull;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

/**
 * @desc 
 * @author niejing
 * @date 2018年9月27日 上午11:00:17
 */
@ExcelTarget("attendanceExcelEntity")
public class AttendanceExcelEntity implements Serializable{

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 1615270697562669194L;
	
	@Excel(name = "用户名称", orderNum = "1", width = 25)
	@NotNull
	private String name;
	
	@Excel(name = "卡号", orderNum = "2", width = 25)
	@NotNull
	private String cardNum;
	
	@Excel(name = "打卡时间", orderNum = "3", width = 25)
	private String clockTime;
	
	@Excel(name = "进场/出场", orderNum = "4", width = 25)
	private String type;
	
	@Excel(name = "岗位/工种", orderNum = "5", width = 25)
	private String position;
	
	@Excel(name = "班组", orderNum = "6", width = 25)
	private String team;
	
	@Excel(name = "所属公司", orderNum = "7", width = 25)
	private String company;

	public String getName() {
		return name;
	}

	public String getCardNum() {
		return cardNum;
	}

	public String getClockTime() {
		return clockTime;
	}


	public String getPosition() {
		return position;
	}

	public String getTeam() {
		return team;
	}

	public String getCompany() {
		return company;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setCardNum(String cardNum) {
		this.cardNum = cardNum;
	}

	public void setClockTime(String clockTime) {
		this.clockTime = clockTime;
	}


	public void setPosition(String position) {
		this.position = position;
	}

	public void setTeam(String team) {
		this.team = team;
	}

	public void setCompany(String company) {
		this.company = company;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
	
	
}
