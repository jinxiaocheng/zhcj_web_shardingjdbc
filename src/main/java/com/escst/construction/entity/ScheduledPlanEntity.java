package com.escst.construction.entity;

import java.util.Date;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc
 * @author niejing
 * @date 2017年8月18日 下午1:26:48
 */
public class ScheduledPlanEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -1450950634192740078L;

	private String id;

	private String parentId;
	// 进度名称
	private String name;

	// 工地ID
	private String constructionId;

	// 开始时间
	private Date startTime;

	// 结束时间
	private Date endTime;

	//状态0：已完成 1：进行中
	private String status;

	private int sortNum;
	
	
	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public Date getStartTime() {
		return startTime;
	}

	public void setStartTime(Date startTime) {
		this.startTime = startTime;
	}

	public Date getEndTime() {
		return endTime;
	}

	public void setEndTime(Date endTime) {
		this.endTime = endTime;
	}

	public int getSortNum() {
		return sortNum;
	}

	public void setSortNum(int sortNum) {
		this.sortNum = sortNum;
	}
}
