package com.escst.equipment.vo;

import com.escst.commons.vo.PageAuthVO;

/**
 * @desc 设备巡检查询条件
 * @author zhouwei
 * @date 2017年8月23日 上午11:18:49
 */
public class InspectionQueryVO extends PageAuthVO {
	
	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -3562940581058687043L;

	/**
	 * @Fields constructionName 工地名称
	 */
	private String constructionName;
	
	/**
	 * @Fields startDate 开始时间.格式2017-08-17
	 */
	private String startDate;
	
	/**
	 * @Fields endDate 结束时间.格式2017-08-17
	 */
	private String endDate;
	
	/**
	 * @Fields results 巡检结果.1:合格;2:不合格
	 */
	private Integer results;
	
	/**
	 * @Fields number 设备编号
	 */
	private String number;

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
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

	public Integer getResults() {
		return results;
	}

	public void setResults(Integer results) {
		this.results = results;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}
}
