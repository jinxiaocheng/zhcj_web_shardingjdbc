package com.escst.towerCrane.entity;

import java.util.Date;

import com.escst.commons.entity.BaseEntity;

/**
 * 
 * @desc
 * @author niejing
 * @date 2017年4月19日 上午10:14:54
 * 
 */
public class TowerCraneEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -6700963100525265187L;

	// 唯一标识，不用传
	private String id;

	// 工地ID
	private String constructionId;

	// 终端设备 SN
	private String equipmentId;

	// 采集时间
	private Date acquisitionTime;

	// 角度
	private float angle;

	// 幅度
	private float extent;

	// 高度
	private float height;

	// 风速
	private float windSpeed;

	// 重量
	private float weight;

	// 倾角
	private float obliquity;

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

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}

	public Date getAcquisitionTime() {
		return acquisitionTime;
	}

	public void setAcquisitionTime(Date acquisitionTime) {
		this.acquisitionTime = acquisitionTime;
	}

	public float getAngle() {
		return angle;
	}

	public void setAngle(float angle) {
		this.angle = angle;
	}

	public float getExtent() {
		return extent;
	}

	public void setExtent(float extent) {
		this.extent = extent;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		this.height = height;
	}

	public float getWindSpeed() {
		return windSpeed;
	}

	public void setWindSpeed(float windSpeed) {
		this.windSpeed = windSpeed;
	}

	public float getWeight() {
		return weight;
	}

	public void setWeight(float weight) {
		this.weight = weight;
	}

	public float getObliquity() {
		return obliquity;
	}

	public void setObliquity(float obliquity) {
		this.obliquity = obliquity;
	}

}
