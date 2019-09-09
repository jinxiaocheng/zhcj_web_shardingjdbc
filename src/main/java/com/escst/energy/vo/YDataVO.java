package com.escst.energy.vo;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 14:35 2017/9/19
 */
public class YDataVO {

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
