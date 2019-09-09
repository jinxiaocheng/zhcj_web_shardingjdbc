package com.escst.inspection.entity;
/** 
* @author hukang
* @version 创建时间：2017年11月21日 下午4:50:58 
* 类说明    通知(抄送人)
*/
public class NotifyEntity {
	private String id;
	private String inspectionId;
	private String userId;
	private String projectCompanyId;
	private String teamId;
	private String mobile;
	
	/**
	 * @Fields name 用户名称
	 */
	private String name;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getInspectionId() {
		return inspectionId;
	}
	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}
	public String getUserId() {
		return userId;
	}
	public void setUserId(String userId) {
		this.userId = userId;
	}
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
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
}
