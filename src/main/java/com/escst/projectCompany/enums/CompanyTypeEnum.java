package com.escst.projectCompany.enums;

/**
 * @desc 公司类型
 * @author zhouwei
 * @date 2017年7月12日 下午3:59:13
 */
public enum CompanyTypeEnum {
	
	/**
	 * @Fields PROFESSION 专业工程分包公司
	 */
	PROFESSION(1, "专业工程分包公司"),

	/**
	 * @Fields LABOUR 劳务分包公司
	 */
	LABOUR(2, "劳务分包公司"),

	/**
	 * @Fields LEASE 租赁公司
	 */
	LEASE(3, "租赁公司"),
	
	/**
	 * @Fields DEVELOPMENT 建设单位
	 */
	DEVELOPMENT(4, "建设单位"),
	
	/**
	 * @Fields BUILDING 施工单位
	 */
	BUILDING(5, "施工单位"),
	
	/**
	 * @Fields SUPERVISION 监理单位
	 */
	SUPERVISION(6, "监理单位");
	
	private int value = 0;
	
	private String label = null;
	
	private CompanyTypeEnum(int value, String label) {
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
