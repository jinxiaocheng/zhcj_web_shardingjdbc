package com.escst.construction.vo;

import com.escst.commons.vo.PageAuthVO;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年9月21日 上午10:42:10
 */
public class ConstructionPageVO extends PageAuthVO {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 3594551575827599043L;
	/**
	 * @Fields name 工地名称
	 */
	private String name;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
