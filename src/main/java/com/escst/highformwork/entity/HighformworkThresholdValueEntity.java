package com.escst.highformwork.entity;

import com.escst.commons.entity.BasePageEntity;

/**
 * @desc 
 * @author niejing
 * @date 2018年7月17日 上午9:55:26
 */
public class HighformworkThresholdValueEntity extends BasePageEntity{

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 2327218923025532840L;
	private String id;
	private String constructionId;
	private String constructionName;
	private String equipmentId;
	private Integer type;
	private Double xAngle;
	private Double yAngle;
	private Double kpa;
	private Double displace;
	private Double temperature;
	private Double electric;
	private String flowName;
	
	public String getFlowName() {
		return flowName;
	}
	public void setFlowName(String flowName) {
		this.flowName = flowName;
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
	public Double getxAngle() {
		return xAngle;
	}
	public void setxAngle(Double xAngle) {
		this.xAngle = xAngle;
	}
	public Double getyAngle() {
		return yAngle;
	}
	public void setyAngle(Double yAngle) {
		this.yAngle = yAngle;
	}
	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
	public String getConstructionName() {
		return constructionName;
	}
	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Double getKpa() {
		return kpa;
	}
	public void setKpa(Double kpa) {
		this.kpa = kpa;
	}
	public Double getDisplace() {
		return displace;
	}
	public void setDisplace(Double displace) {
		this.displace = displace;
	}
	public Double getTemperature() {
		return temperature;
	}
	public void setTemperature(Double temperature) {
		this.temperature = temperature;
	}
	public Double getElectric() {
		return electric;
	}
	public void setElectric(Double electric) {
		this.electric = electric;
	}
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	
}
