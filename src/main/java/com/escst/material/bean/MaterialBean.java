package com.escst.material.bean;

import com.escst.commons.entity.BaseEntity;

public class MaterialBean extends BaseEntity{

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 6253208224976110728L;
	private String constructionId;
	/**工地名称*/
    private String constructName;
    /**区名称*/
    private String areaName;
    /**区编码*/
    private String areaCode;
    /**
     * 进场开始时间
     */
    private String approachDateStart;
    /**
     * 进场结束时间
     */
    private String approachDateEnd;
    /**生产厂家*/
    private String manufacturer;
    /**使用部位*/
    private String usePosition;
    
    //材料名称
    private String name;
	//数量
	private String quantity;
	//材料总价
	private String amount;
	
	/**
	 * @Fields model 材料型号
	 */
	private String model;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
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
	public String getConstructionId() {
		return constructionId;
	}
	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	public String getUsePosition() {
		return usePosition;
	}
	public void setUsePosition(String usePosition) {
		this.usePosition = usePosition;
	}
	public String getConstructName() {
		return constructName;
	}
	public void setConstructName(String constructName) {
		this.constructName = constructName;
	}
	public String getAreaName() {
		return areaName;
	}
	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}
	public String getAreaCode() {
		return areaCode;
	}
	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}
	public String getApproachDateStart() {
		return approachDateStart;
	}
	public void setApproachDateStart(String approachDateStart) {
		this.approachDateStart = approachDateStart;
	}
	public String getApproachDateEnd() {
		return approachDateEnd;
	}
	public void setApproachDateEnd(String approachDateEnd) {
		this.approachDateEnd = approachDateEnd;
	}
	public String getModel() {
		return model;
	}
	public void setModel(String model) {
		this.model = model;
	}
    
}
