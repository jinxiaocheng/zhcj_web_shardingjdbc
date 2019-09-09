package com.escst.attendance.vo;

import java.util.List;

/**
 * @desc
 * @author niejing
 * @date 2017年11月6日 下午4:58:45
 */
public class AttendanceNumRstVO {
	private List<String> xdata;

	private List<AttendanceNumYDataVO> yDataVo;

	public List<String> getXdata() {
		return xdata;
	}

	public void setXdata(List<String> xdata) {
		this.xdata = xdata;
	}

	public List<AttendanceNumYDataVO> getyDataVo() {
		return yDataVo;
	}

	public void setyDataVo(List<AttendanceNumYDataVO> yDataVo) {
		this.yDataVo = yDataVo;
	}


	
}
