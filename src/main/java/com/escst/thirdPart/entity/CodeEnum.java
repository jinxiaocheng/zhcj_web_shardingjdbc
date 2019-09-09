package com.escst.thirdPart.entity;

/**
 * @desc
 * @author niejing
 * @date 2018年8月1日 上午10:50:07
 */
public enum CodeEnum {

	JOB(1, "岗位"),WORKTYPE(2,"工种"),MARITAL_STATUS(3,"婚姻状况"),BIRTHPLACE(4, "籍贯"),EDUCATION(5,"文化水平");

	private Integer value;
	private String lable;

	private CodeEnum(Integer value, String lable) {
		this.value = value;
		this.lable = lable;
	}

	public Integer getValue() {
		return value;
	}

	public void setValue(Integer value) {
		this.value = value;
	}

	public String getLable() {
		return lable;
	}

	public void setLable(String lable) {
		this.lable = lable;
	}
}
