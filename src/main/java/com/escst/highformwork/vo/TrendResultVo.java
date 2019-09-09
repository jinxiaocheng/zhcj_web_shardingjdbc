package com.escst.highformwork.vo;

/**
 * @desc 
 * @author niejing
 * @date 2018年7月20日 下午3:38:51
 */
public class TrendResultVo {

	private String acquisitionTime;
	private String  equipmentId;
	private String equipmentName;
	private Double xAngle=0d;
	private Double yAngle=0d;
	private Double displace=0d;
	private Double kpa=0d;
	
	
	public String getAcquisitionTime() {
		return acquisitionTime;
	}
	public void setAcquisitionTime(String acquisitionTime) {
		this.acquisitionTime = acquisitionTime;
	}
	public String getEquipmentId() {
		return equipmentId;
	}
	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
	public String getEquipmentName() {
		return equipmentName;
	}
	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
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
	public Double getDisplace() {
		return displace;
	}
	public void setDisplace(Double displace) {
		this.displace = displace;
	}
	public Double getKpa() {
		return kpa;
	}
	public void setKpa(Double kpa) {
		this.kpa = kpa;
	}
	@Override
	public String toString() {
		return "TrendResultVo [acquisitionTime=" + acquisitionTime + ", equipmentId=" + equipmentId + ", equipmentName=" + equipmentName + ", xAngle=" + xAngle
				+ ", yAngle=" + yAngle + ", displace=" + displace + ", kpa=" + kpa + "]";
	}
	
	
}
