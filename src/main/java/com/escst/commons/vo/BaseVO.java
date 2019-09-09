package com.escst.commons.vo;

import java.io.Serializable;

/**
 * @desc
 * @author zhouwei
 * @date 2017年7月20日 下午1:18:46
 */
public class BaseVO implements Serializable {
	
	private String id;

	private String name;


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


}
