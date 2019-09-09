package com.escst.inspection.enums;

/**
 * @desc 检查类型
 * @author zhouwei
 * @date 2017年8月26日 下午3:17:01
 */
public enum InspectionTypeEnum {

	/**
	 * @Fields QUALITY 质量
	 */
	QUALITY(1, "质量检查"),

	/**
	 * @Fields SAFETY 安全
	 */
	SAFETY_ITEMS(2, "施工安全分项检查"),

	/**
	 * @Fields SAFETY 安全
	 */
	SAFETY_EVERYDAY(3, "日常安全检查");


	private int value = 0;
	
	private String label = null;
	
	private InspectionTypeEnum(int value, String label) {
		this.value = value;
		this.label = label;
	}

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}
}
