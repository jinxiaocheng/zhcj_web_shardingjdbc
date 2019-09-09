package com.escst.person.vo;

import com.escst.commons.vo.BaseAuthVO;

/**
 * @desc 
 * @author niejing
 * @date 2017年11月6日 下午4:14:19
 */
public class WorkTypeQueryVO extends BaseAuthVO{
	
	private String startDate;
	
	private String endDate;

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
