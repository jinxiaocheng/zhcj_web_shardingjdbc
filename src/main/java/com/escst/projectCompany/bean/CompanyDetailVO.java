package com.escst.projectCompany.bean;

import com.escst.team.bean.TeamLeaderVO;

import java.util.List;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年7月12日 下午4:40:10
 */
public class CompanyDetailVO {

	/**
	 * @Fields id 合作单位ID
	 */
	private String id;
	
	/**
	 * @Fields name 合作单位名称
	 */
	private String name;
	
	/**
	 * @Fields type 合作单位类型
	 */
	private int type;
	
	private List<TeamLeaderVO> data;

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

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public List<TeamLeaderVO> getData() {
		return data;
	}

	public void setData(List<TeamLeaderVO> data) {
		this.data = data;
	}
}
