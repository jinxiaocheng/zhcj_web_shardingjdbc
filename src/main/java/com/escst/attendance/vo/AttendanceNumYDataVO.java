package com.escst.attendance.vo;

import java.util.List;

/**
 * @desc 
 * @author niejing
 * @date 2017年11月7日 下午5:46:41
 */
public class AttendanceNumYDataVO {
	/**
	 * @Fields name 数据名称
	 */
	private String name;
	
	/**
	 * @Fields data y轴的数据
	 */
	private List<String> data;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<String> getData() {
		return data;
	}

	public void setData(List<String> data) {
		this.data = data;
	}

	
}
