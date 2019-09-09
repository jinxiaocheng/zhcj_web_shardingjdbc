package com.escst.equipment.vo;

import com.escst.commons.vo.PageAuthVO;

/**
 * @desc 设备查询
 * @author zhouwei
 * @date 2017年8月16日 下午6:37:57
 */
public class QueryVO extends PageAuthVO {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 1251679886526374114L;

	/**
	 * @Fields type 设备类型.EquipmentTypeEnum
	 */
	private Integer type;
	
	/**
	 * @Fields types 设备类型用逗号拼接的字符串
	 */
	private String types;
	
	/**
	 * @Fields name 设备名称
	 */
	private String name;
	
	/**
	 * @Fields number 设备编号
	 */
	private String number;

	/**
	 *@Fields areaId  区域Id
	 */
	private String areaId;
	/**
	 *@Fields constructionId 工地Id
	 */
	private String constructionId;

	/**
	 *@Fields status 判断是否分页
	 **/
	private int status;

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
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

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}

	@Override
	public String getAreaId() {
		return areaId;
	}

	@Override
	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	@Override
	public String getConstructionId() {
		return constructionId;
	}

	@Override
	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}
}
