package com.escst.construction.entity;

import org.springframework.web.multipart.MultipartFile;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc
 * @author niejing
 * @date 2017年8月10日 下午2:30:10
 */
public class ConstructionLicenseEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 5083545919241536863L;

	private String id;
	//施工许可证号
	private String constructLicenseNo;
	//工程名称
	private String projectName;
	//建设地点
	private String developeSite;
	//建设单位
	private String development;
	//施工单位
	private String building;
	//设计单位
	private String designing;
	//监理单位
	private String supervision;
	//勘察单位
	private String prospecting;
	//勘察单位负责人
	private String prospectingHeader;
	//设计单位负责人
	private String designingHeader;
	//项目经理
	private String projectManager;
	//监理总监
	private String superviseDirector;
	//建设规模
	private String developeScale;
	//合同价格
	private String contractPrice;
	//发证机关
	private String licenseIssue;
	//发证日期
	private String licenseDate;
	//备注
	private String remark;

	/** 合同开工日期 **/
	private String contractStartDate;
	/** 合同竣工日期 **/
	private String contractEndDate;

	/**计划开工日期 **/
	private String planContractStartDate;
	/** 计划竣工日期 **/
	private String planContractEndDate;

	private String cityId;
	private String areaId;

	/**经度*/
	private String lng;
	/**纬度*/
	private String lat;

	private String iconId;

	private MultipartFile file;

	/**图片附件路径集合**/
	private String picList;

	private  String cityName;
	private  String areaName;

	private String constructionId;
	
	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConstructLicenseNo() {
		return constructLicenseNo;
	}

	public void setConstructLicenseNo(String constructLicenseNo) {
		this.constructLicenseNo = constructLicenseNo;
	}

	public String getProjectName() {
		return projectName;
	}

	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}

	public String getDevelopeSite() {
		return developeSite;
	}

	public void setDevelopeSite(String developeSite) {
		this.developeSite = developeSite;
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

	public String getDesigning() {
		return designing;
	}

	public void setDesigning(String designing) {
		this.designing = designing;
	}

	public String getSupervision() {
		return supervision;
	}

	public void setSupervision(String supervision) {
		this.supervision = supervision;
	}

	public String getProspecting() {
		return prospecting;
	}

	public void setProspecting(String prospecting) {
		this.prospecting = prospecting;
	}

	public String getProspectingHeader() {
		return prospectingHeader;
	}

	public void setProspectingHeader(String prospectingHeader) {
		this.prospectingHeader = prospectingHeader;
	}

	public String getDesigningHeader() {
		return designingHeader;
	}

	public void setDesigningHeader(String designingHeader) {
		this.designingHeader = designingHeader;
	}

	public String getProjectManager() {
		return projectManager;
	}

	public void setProjectManager(String projectManager) {
		this.projectManager = projectManager;
	}

	public String getSuperviseDirector() {
		return superviseDirector;
	}

	public void setSuperviseDirector(String superviseDirector) {
		this.superviseDirector = superviseDirector;
	}


	public String getDevelopeScale() {
		return developeScale;
	}

	public void setDevelopeScale(String developeScale) {
		this.developeScale = developeScale;
	}

	public String getContractPrice() {
		return contractPrice;
	}

	public void setContractPrice(String contractPrice) {
		this.contractPrice = contractPrice;
	}

	public String getLicenseIssue() {
		return licenseIssue;
	}

	public void setLicenseIssue(String licenseIssue) {
		this.licenseIssue = licenseIssue;
	}

	public String getLicenseDate() {
		return licenseDate;
	}

	public void setLicenseDate(String licenseDate) {
		this.licenseDate = licenseDate;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
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

	public MultipartFile getFile() {
		return file;
	}

	public void setFile(MultipartFile file) {
		this.file = file;
	}

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public String getPicList() {
		return picList;
	}

	public void setPicList(String picList) {
		this.picList = picList;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
}
