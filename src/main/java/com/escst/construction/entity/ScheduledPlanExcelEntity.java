package com.escst.construction.entity;

/**
 * @author dwj
 * @desc
 * @date 13:44 2017/4/24
 */
public class ScheduledPlanExcelEntity {

	private String name;
	//级别只有1,2两个级别
	private String level;
	// 开始时间
	private String startDate;
	// 开始时间
	private String endDate;

	public String getLevel() {
		return level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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
}
