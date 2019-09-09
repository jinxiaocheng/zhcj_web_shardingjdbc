package com.escst.commons.vo;

import java.io.Serializable;

/**
 * @desc 基础的权限VO
 * @author zhouwei
 * @date 2017年8月15日 下午4:42:18
 */
public class BaseAuthVO implements Serializable {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -789266866819698680L;

	/**
	 * @Fields userId 用户ID
	 */
	private String userId;

	//区域Id（区域过滤时用）
	private String areaId;
	
	private String constructionId;
	
	/**
	 * @Fields id 业务单据ID
	 */
	private String id;

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
}
