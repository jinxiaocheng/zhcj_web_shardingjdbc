package com.escst.person.vo;

import com.escst.commons.vo.PageAuthVO;

/**
 * @desc 人员的查询条件
 * @author zhouwei
 * @date 2017年11月2日 下午9:08:43
 */
public class PersonQueryVO extends PageAuthVO {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 1189446999595235531L;

	/**
	 * @Fields constructionId 工地ID
	 */
	private String constructionId;
	
	/**
	 * @Fields projectCompanyId 公司ID
	 */
	private String projectCompanyId;

	/**
	 * @Fields teamId 班组ID
	 */
	private String teamId;

	/**
	 * @Fields name 人员姓名
	 */
	private String name;
	
	/**
	 * @Fields positionWorkType 岗位/工种
	 */
	private Integer positionWorkType;

	private String companyTypes;

	private int type;

	public String getProjectCompanyId() {
		return projectCompanyId;
	}

	public void setProjectCompanyId(String projectCompanyId) {
		this.projectCompanyId = projectCompanyId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
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

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public String getCompanyTypes() {
		return companyTypes;
	}

	public void setCompanyTypes(String companyTypes) {
		this.companyTypes = companyTypes;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}
}
