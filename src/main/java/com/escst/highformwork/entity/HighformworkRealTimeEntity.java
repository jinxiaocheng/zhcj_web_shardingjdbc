package com.escst.highformwork.entity;

import com.escst.commons.entity.BasePageEntity;

import java.util.Date;

/**
 * @desc 
 * @author niejing
 * @date 2018年7月17日 上午9:45:53
 */
public class HighformworkRealTimeEntity extends BasePageEntity{
	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -3392778003342442346L;
	private String id;
	private String constructionId;
	private String constructionName;
	private String equipmentId;
	//1:正常；2：预警；3：报警；4、控制
	private Integer type;
	private double xAngle;
	private double yAngle;
	private double kpa;
	private double displace;
	private double temperature;
	private double electric;
	private Date acquisitionTime;
	private int xType;
	private int yType;
	private int kpaType;
	private int displaceType;
	private int temperatureType;
	private int electricType;
	private String fileId;
	private String path;

	private String startTime;
	private String endTime;
	public String getId() {
		return id;
	}
	public String getConstructionId() {
		return constructionId;
	}
	public String getConstructionName() {
		return constructionName;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public Integer getType() {
		return type;
	}
	public double getxAngle() {
		return xAngle;
	}
	public double getyAngle() {
		return yAngle;
	}
	public double getKpa() {
		return kpa;
	}
	public double getDisplace() {
		return displace;
	}
	public double getTemperature() {
		return temperature;
	}
	public double getElectric() {
		return electric;
	}
	public Date getAcquisitionTime() {
		return acquisitionTime;
	}
	public int getxType() {
		return xType;
	}
	public int getyType() {
		return yType;
	}
	public int getKpaType() {
		return kpaType;
	}
	public int getDisplaceType() {
		return displaceType;
	}
	public int getTemperatureType() {
		return temperatureType;
	}
	public int getElectricType() {
		return electricType;
	}
	public String getFileId() {
		return fileId;
	}
	public String getPath() {
		return path;
	}
	public String getStartTime() {
		return startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setId(String id) {
		this.id = id;
	}
	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public void setxAngle(double xAngle) {
		this.xAngle = xAngle;
	}
	public void setyAngle(double yAngle) {
		this.yAngle = yAngle;
	}
	public void setKpa(double kpa) {
		this.kpa = kpa;
	}
	public void setDisplace(double displace) {
		this.displace = displace;
	}
	public void setTemperature(double temperature) {
		this.temperature = temperature;
	}
	public void setElectric(double electric) {
		this.electric = electric;
	}
	public void setAcquisitionTime(Date acquisitionTime) {
		this.acquisitionTime = acquisitionTime;
	}
	public void setxType(int xType) {
		this.xType = xType;
	}
	public void setyType(int yType) {
		this.yType = yType;
	}
	public void setKpaType(int kpaType) {
		this.kpaType = kpaType;
	}
	public void setDisplaceType(int displaceType) {
		this.displaceType = displaceType;
	}
	public void setTemperatureType(int temperatureType) {
		this.temperatureType = temperatureType;
	}
	public void setElectricType(int electricType) {
		this.electricType = electricType;
	}
	public void setFileId(String fileId) {
		this.fileId = fileId;
	}
	public void setPath(String path) {
		this.path = path;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}


	
}
