package com.escst.team.bean;

/**
 * @desc 班组责任人信息
 * @author zhouwei
 * @date 2017年7月12日 下午4:20:22
 */
public class TeamLeaderVO {
	
	/**
	 * @Fields id 班组ID
	 */
	private String id;
	
	/**
	 * @Fields name 班组名称
	 */
	private String name;

	/**
	 * @Fields userId 班组长或者工长ID
	 */
	private String userId;

	/**
	 * @Fields userName 班组长或者工长名称
	 */
	private String userName;
	
	/**
	 * @Fields mobile 班组长或者工长手机号
	 */
	private String mobile;
	
	/**
	 * @Fields companyId 合作单位ID
	 */
	private String companyId;

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

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getCompanyId() {
		return companyId;
	}

	public void setCompanyId(String companyId) {
		this.companyId = companyId;
	}
}
