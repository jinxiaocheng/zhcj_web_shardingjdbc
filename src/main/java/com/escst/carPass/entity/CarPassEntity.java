package com.escst.carPass.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc
 * @author niejing
 * @date 2018年8月16日 上午10:02:58
 */
public class CarPassEntity extends BaseEntity {
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 3240513215012357201L;
	private String id;
	private String constructionId;
	// 车牌号码
	private String plateNo;
	// 过车行驶方向 0:入场过车;1:出场过车
	private int direction;
	private String fileId;
	private String passTime;

	private String startTime;
	private String endTime;
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getId() {
		return id;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public String getPlateNo() {
		return plateNo;
	}

	public int getDirection() {
		return direction;
	}

	public String getFileId() {
		return fileId;
	}

	public String getPassTime() {
		return passTime;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public void setPlateNo(String plateNo) {
		this.plateNo = plateNo;
	}

	public void setDirection(int direction) {
		this.direction = direction;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public void setPassTime(String passTime) {
		this.passTime = passTime;
	}

}
