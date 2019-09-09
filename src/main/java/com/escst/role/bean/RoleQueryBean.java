package com.escst.role.bean;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc 
 * @author niejing
 * @date 2017年9月7日 下午2:03:35
 */
public class RoleQueryBean extends BaseEntity{

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 2363261478317376375L;

	private String orgId;
	
	private String constructionId;

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
	
	
}
