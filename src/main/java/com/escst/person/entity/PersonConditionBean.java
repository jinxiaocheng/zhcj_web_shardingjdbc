package com.escst.person.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc 
 * @author niejing
 * @date 2017年7月14日 下午2:02:32
 */
public class PersonConditionBean extends BaseEntity{

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 280588919252742531L;

	private String name;
	
	//手机号
	private String mobile;
	
	//岗位
	private Integer title;
	
	//工种
	private Integer workType;
	
	//合作单位
	private String projectCompanyName;
	
	//所属区
	private String territoryId;

	//工地ID
	private String constructionId;
	private String projectCompanyId;
	//工地名称
	private String constructionName;

	private String userId;
	
	//岗位/工种
	private String positionWorkType;
	
	private String positionWorkId;
	
	//卡号
	private String cardNumber;

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getPositionWorkId() {
		return positionWorkId;
	}

	public void setPositionWorkId(String positionWorkId) {
		this.positionWorkId = positionWorkId;
	}

	public String getProjectCompanyId() {
		return projectCompanyId;
	}

	public void setProjectCompanyId(String projectCompanyId) {
		this.projectCompanyId = projectCompanyId;
	}

	public String getPositionWorkType() {
		return positionWorkType;
	}

	public void setPositionWorkType(String positionWorkType) {
		this.positionWorkType = positionWorkType;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public Integer getWorkType() {
		return workType;
	}

	public void setWorkType(Integer workType) {
		this.workType = workType;
	}

	public String getProjectCompanyName() {
		return projectCompanyName;
	}

	public void setProjectCompanyName(String projectCompanyName) {
		this.projectCompanyName = projectCompanyName;
	}

	public String getTerritoryId() {
		return territoryId;
	}

	public void setTerritoryId(String territoryId) {
		this.territoryId = territoryId;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
}
