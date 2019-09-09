package com.escst.construction.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @author caozx
 * @desc
 * @date 2017/2/16 16:19
 */
public class ConstructionEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -501143778015236084L;
	private String id;
	/** 工地名称 **/
	private String name;
	//工地类型
	private String type;
	/** 所属市 **/
	private String belongCity;
	/** 所属区 **/
	private String belongArea;
	/** 父节点ID.对工地进行分组使用 **/
	private String parentId;
	/** 施工许可证ID **/
	private String constructLicenseId;
	/** 施工许可证号 **/
	private String constructLicenseNo;
	/** 发证日期 **/
	private String licenseDate;
	/** 建设单位 **/
	private String development;
	/** 施工单位 **/
	private String building;
	/** 监理单位 **/
	private String supervision;
	/** 项目经理 **/
	private String projectManager;
	/** 合同开工日期 **/
	private String contractStartDate;
	/** 合同竣工日期 **/
	private String contractEndDate;
	/**计划开工日期 **/
	private String planContractStartDate;
	/** 计划竣工日期 **/
	private String planContractEndDate;
	/** 工地是否在线 1:在线;0:离线 **/
	private Integer isOnline;
	/** 状态.1:有效;0:无效 **/
	private Integer status;

	private String iconId;

	private String lng;
	private String lat;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getBelongCity() {
		return belongCity;
	}

	public String getPlanContractStartDate() {
		return planContractStartDate;
	}

	public void setPlanContractStartDate(String planContractStartDate) {
		this.planContractStartDate = planContractStartDate;
	}

	public String getPlanContractEndDate() {
		return planContractEndDate;
	}

	public void setPlanContractEndDate(String planContractEndDate) {
		this.planContractEndDate = planContractEndDate;
	}

	public void setBelongCity(String belongCity) {
		this.belongCity = belongCity;
	}

	public String getBelongArea() {
		return belongArea;
	}

	public void setBelongArea(String belongArea) {
		this.belongArea = belongArea;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getConstructLicenseId() {
		return constructLicenseId;
	}

	public void setConstructLicenseId(String constructLicenseId) {
		this.constructLicenseId = constructLicenseId;
	}

	public String getConstructLicenseNo() {
		return constructLicenseNo;
	}

	public void setConstructLicenseNo(String constructLicenseNo) {
		this.constructLicenseNo = constructLicenseNo;
	}

	public String getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(String licenseDate) {
		this.licenseDate = licenseDate;
	}

	public String getDevelopment() {
		return development;
	}

	public void setDevelopment(String development) {
		this.development = development;
	}

	public String getBuilding() {
		return building;
	}

	public void setBuilding(String building) {
		this.building = building;
	}

	public String getSupervision() {
		return supervision;
	}

	public void setSupervision(String supervision) {
		this.supervision = supervision;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getContractStartDate() {
		return contractStartDate;
	}

	public void setContractStartDate(String contractStartDate) {
		this.contractStartDate = contractStartDate;
	}

	public String getContractEndDate() {
		return contractEndDate;
	}

	public void setContractEndDate(String contractEndDate) {
		this.contractEndDate = contractEndDate;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getLng() {
		return lng;
	}

	public void setLng(String lng) {
		this.lng = lng;
	}

	public String getLat() {
		return lat;
	}

	public void setLat(String lat) {
		this.lat = lat;
	}

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}
}
