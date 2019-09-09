package com.escst.lifter.vo;

import java.math.BigDecimal;

/**
 * @desc 
 * @author niejing
 * @date 2018年4月23日 下午2:32:22
 */
public class UnloadRealtimeVO {

	/**
	 * @Fields time 采集时间
	 */
	private String time;
	
	/**
	 * @Fields height 高度
	 */
	private BigDecimal height;
	
	/**
	 * @Fields weight 重量
	 */
	private BigDecimal weight;
	
	/**
	 * @Fields obliquity 倾斜
	 */
	private BigDecimal obliquity;
	
	/**
	 * @Fields peopleNum 人数
	 */
	private Integer peopleNum;
	
	/**
	 * @Fields speed 速度
	 */
	private BigDecimal speed;
	
	/**
	 * @Fields driverName 司机名称
	 */
	private String driverName;
	
	/**
	 * @Fields driverNo 司机身份证号
	 */
	private String driverNo;
	
	/**
	 * @Fields floorNo 楼层
	 */
	private int floorNo;
	
	/**
	 * @Fields frontDoorLockState 前门锁报警开关
	 */
	private int frontDoorLockState;
	
	/**
	 * @Fields backDoorLockState 后门锁报警开关
	 */
	private int backDoorLockState;
	
	/**
	 * @Fields highLimitState 高限位报警开关
	 */
	private int highLimitState;
	
	/**
	 * @Fields lowLimitState 低限位报警开关
	 */
	private int lowLimitState;

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public BigDecimal getHeight() {
		return height;
	}

	public void setHeight(BigDecimal height) {
		this.height = height;
	}

	public BigDecimal getWeight() {
		return weight;
	}

	public void setWeight(BigDecimal weight) {
		this.weight = weight;
	}

	public BigDecimal getObliquity() {
		return obliquity;
	}

	public void setObliquity(BigDecimal obliquity) {
		this.obliquity = obliquity;
	}

	public Integer getPeopleNum() {
		return peopleNum;
	}

	public void setPeopleNum(Integer peopleNum) {
		this.peopleNum = peopleNum;
	}

	public BigDecimal getSpeed() {
		return speed;
	}

	public void setSpeed(BigDecimal speed) {
		this.speed = speed;
	}

	public String getDriverName() {
		return driverName;
	}

	public void setDriverName(String driverName) {
		this.driverName = driverName;
	}

	public String getDriverNo() {
		return driverNo;
	}

	public void setDriverNo(String driverNo) {
		this.driverNo = driverNo;
	}

	public int getFloorNo() {
		return floorNo;
	}

	public void setFloorNo(int floorNo) {
		this.floorNo = floorNo;
	}

	public int getFrontDoorLockState() {
		return frontDoorLockState;
	}

	public void setFrontDoorLockState(int frontDoorLockState) {
		this.frontDoorLockState = frontDoorLockState;
	}

	public int getBackDoorLockState() {
		return backDoorLockState;
	}

	public void setBackDoorLockState(int backDoorLockState) {
		this.backDoorLockState = backDoorLockState;
	}

	public int getHighLimitState() {
		return highLimitState;
	}

	public void setHighLimitState(int highLimitState) {
		this.highLimitState = highLimitState;
	}

	public int getLowLimitState() {
		return lowLimitState;
	}

	public void setLowLimitState(int lowLimitState) {
		this.lowLimitState = lowLimitState;
	} 
	
}
