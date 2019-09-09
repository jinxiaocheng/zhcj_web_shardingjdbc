package com.escst.lifter.vo;

import java.math.BigDecimal;

/**
 * @desc 升降机实时监测数据
 * @author zhouwei
 * @date 2017年8月21日 下午4:10:19
 */
public class LifterRealtimeVO {

	private String id;

	/**
	 * @Fields time 采集时间
	 */
	private String time;
	
	/**
	 * @Fields height 高度
	 */
	private BigDecimal height;

	private Integer heightWarning;
	
	/**
	 * @Fields weight 重量
	 */
	private BigDecimal weight;

	private Integer weightWarning;
	
	/**
	 * @Fields obliquity 倾斜
	 */
	private BigDecimal obliquity;

	private Integer obliquityWarning;
	
	/**
	 * @Fields peopleNum 人数
	 */
	private Integer peopleNum;

	private Integer peopleNumWarning;
	
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

	private String constructionId;

	private String userId;

	private String name;

	private Integer type;

	private Integer warning;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public Integer getHeightWarning() {
		return heightWarning;
	}

	public void setHeightWarning(Integer heightWarning) {
		this.heightWarning = heightWarning;
	}

	public Integer getWeightWarning() {
		return weightWarning;
	}

	public void setWeightWarning(Integer weightWarning) {
		this.weightWarning = weightWarning;
	}

	public Integer getObliquityWarning() {
		return obliquityWarning;
	}

	public void setObliquityWarning(Integer obliquityWarning) {
		this.obliquityWarning = obliquityWarning;
	}

	public Integer getPeopleNumWarning() {
		return peopleNumWarning;
	}

	public void setPeopleNumWarning(Integer peopleNumWarning) {
		this.peopleNumWarning = peopleNumWarning;
	}

	public Integer getWarning() {
		return warning;
	}

	public void setWarning(Integer warning) {
		this.warning = warning;
	}
}
