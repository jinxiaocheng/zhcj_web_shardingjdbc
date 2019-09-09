package com.escst.attendance.vo;

import com.escst.commons.vo.BaseAuthVO;

/**
 * @desc
 * @author niejing
 * @date 2017年11月8日 上午11:02:09
 */
public class AttendanceNumQueryVO extends BaseAuthVO {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -8775121033076876481L;

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
