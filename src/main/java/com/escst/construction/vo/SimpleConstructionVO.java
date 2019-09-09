package com.escst.construction.vo;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月2日 上午9:52:44
 */
public class SimpleConstructionVO extends BaseEntity{

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -4053257759061880305L;

	/**
	 * @Fields id 工地ID
	 */
	private String id;
	
	/**
	 * @Fields name 工地名称
	 */
	private String name;
	
	private String areaId;
	
	private String cityId;
	
	private String areaName;
	
	private String cityName;

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

	/** 经度**/
	private String lng;

	/**纬度**/
	private String lat;

	/**工地图标路径**/
	private String iconPath;

	private String iconId;

	/**
	 * @Fields isOnline 是否在线。1：在线；0：离线
	 */
	private Integer isOnline;
	
	/**
	 * @Fields bimId BIM模型的ID
	 */
	private String bimId;

	/**
	 * @Fields floorPlanId 平面图ID
	 */
	private String floorPlanId;

	/**
	 * @Fields constructionScheduleVo 工程进度
	 */
	private ConstructionScheduleVo constructionScheduleVo;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getIsOnline() {
		return isOnline;
	}

	public void setIsOnline(Integer isOnline) {
		this.isOnline = isOnline;
	}

	public String getCityId() {
		return cityId;
	}

	public void setCityId(String cityId) {
		this.cityId = cityId;
	}

	public String getCityName() {
		return cityName;
	}

	public void setCityName(String cityName) {
		this.cityName = cityName;
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

	public String getIconPath() {
		return iconPath;
	}

	public void setIconPath(String iconPath) {
		this.iconPath = iconPath;
	}

	public String getIconId() {
		return iconId;
	}

	public void setIconId(String iconId) {
		this.iconId = iconId;
	}

	public String getBimId() {
		return bimId;
	}

	public void setBimId(String bimId) {
		this.bimId = bimId;
	}

	public ConstructionScheduleVo getConstructionScheduleVo() {
		return constructionScheduleVo;
	}

	public void setConstructionScheduleVo(ConstructionScheduleVo constructionScheduleVo) {
		this.constructionScheduleVo = constructionScheduleVo;
	}

	public String getFloorPlanId() {
		return floorPlanId;
	}

	public void setFloorPlanId(String floorPlanId) {
		this.floorPlanId = floorPlanId;
	}
}
