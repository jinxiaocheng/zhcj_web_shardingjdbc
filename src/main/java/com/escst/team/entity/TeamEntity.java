package com.escst.team.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc
 * @author niejing
 * @date 2017年7月13日 下午2:59:28
 */
public class TeamEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 683997254660621233L;

	private String id;

	private String constructionId;

	private String projectCompanyId;

	private String name;

	private Integer sortNum;
	
	/**
	 * @Fields positionWorkType 岗位/工种
	 */
	private Integer positionWorkType;

	private String positionWorkTypeName;

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

	public String getProjectCompanyId() {
		return projectCompanyId;
	}

	public void setProjectCompanyId(String projectCompanyId) {
		this.projectCompanyId = projectCompanyId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getPositionWorkType() {
		return positionWorkType;
	}

	public void setPositionWorkType(Integer positionWorkType) {
		this.positionWorkType = positionWorkType;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public String getPositionWorkTypeName() {
		return positionWorkTypeName;
	}

	public void setPositionWorkTypeName(String positionWorkTypeName) {
		this.positionWorkTypeName = positionWorkTypeName;
	}
}
