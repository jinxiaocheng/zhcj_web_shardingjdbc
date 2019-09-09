package com.escst.material.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc 材料预算实体类
 * @author niejing
 * @date 2017年8月26日 下午4:34:50
 */
public class MaterialBudgetEntity  extends BaseEntity{

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -803852958226445225L;

	private String id;
	
	private String constructionId;
	
	//材料ID
	private String materialId;
	
	private String modelId;
	//数量
	private String quantity;
	
	//材料总价
	private String amount;

	public String getModelId() {
		return modelId;
	}

	public void setModelId(String modelId) {
		this.modelId = modelId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
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
