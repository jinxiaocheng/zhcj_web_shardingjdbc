package com.escst.material.entity;

/**
 * @desc
 * @author niejing
 * @date 2017年12月8日 上午11:43:43
 */
public class MaterialBudgetExcelEntity {

	// 材料名称
	private String name;
	// 型号
	private String modelName;
	// 单位
	private String unit;
	// 数量
	private String quantity;
	// 金额
	private String amount;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}

	public String getModelName() {
		return modelName;
	}

	public void setModelName(String modelName) {
		this.modelName = modelName;
	}

	public String getQuantity() {
		return quantity;
	}

	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

}
