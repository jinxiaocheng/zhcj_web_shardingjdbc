package com.escst.user.entity;

import java.io.Serializable;

/**
 * @desc
 * @author niejing
 * @date 2017年7月11日 下午4:23:27
 */
public class UserRoleEntity implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 8705243878726776359L;
	private String id;
	private String userId;
	private String roleId;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

}
