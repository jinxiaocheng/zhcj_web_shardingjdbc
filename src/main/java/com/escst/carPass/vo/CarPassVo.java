package com.escst.carPass.vo;

/**
 * @desc 
 * @author niejing
 * @date 2018年8月24日 上午11:03:51
 */
public class CarPassVo {
	private String id;
	private String constructionId;
	// 车牌号码
	private String plateNo;
	// 过车行驶方向 0:入场过车;1:出场过车
	private int direction;
	private String fileId;
	private String passTime;
	//车辆图片路径
	private String carPath;
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
	public String getCarPath() {
		return carPath;
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
	public void setCarPath(String carPath) {
		this.carPath = carPath;
	}
	
	
}
