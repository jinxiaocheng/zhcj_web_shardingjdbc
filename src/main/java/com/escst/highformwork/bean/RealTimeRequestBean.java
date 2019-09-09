package com.escst.highformwork.bean;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc 
 * @author niejing
 * @date 2018年10月12日 下午4:00:07
 */
public class RealTimeRequestBean extends BaseEntity{

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 669705093706723447L;
	private String flowId;

	public String getFlowId() {
		return flowId;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}
	
	
}
