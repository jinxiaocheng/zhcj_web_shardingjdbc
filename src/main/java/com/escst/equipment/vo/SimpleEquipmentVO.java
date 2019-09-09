package com.escst.equipment.vo;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月16日 下午6:16:02
 */
public class SimpleEquipmentVO {

	/**
	 * @Fields id 设备ID
	 */
	private String id;
	
	/**
	 * @Fields name 设备名称
	 */
	private String name;
	
	/**
	 * @Fields number 设备编号
	 */
	private String number;

	/**
	 * @Fields constructionId 工地ID
	 */
	private String constructionId;
	
	/**
	 * @Fields constructionName 工地名称
	 */
	private String constructionName;
	
	/**
	 * @Fields areaName 工地所属区域
	 */
	private String areaName;
	
	/**
	 * @Fields type 设备类型.来源EquipmentTypeEnum
	 */
	private Integer type;

	//人脸识别数量
	private Integer faceCount;
	
	public Integer getFaceCount() {
		return faceCount;
	}

	public void setFaceCount(Integer faceCount) {
		this.faceCount = faceCount;
	}

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

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}
}
