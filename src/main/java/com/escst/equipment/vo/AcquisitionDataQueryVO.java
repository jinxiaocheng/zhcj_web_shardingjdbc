package com.escst.equipment.vo;

import com.escst.commons.vo.PageAuthVO;

/**
 * @desc 采集数据的查询VO
 * @author zhouwei
 * @date 2017年8月21日 下午6:20:56
 */
public class AcquisitionDataQueryVO extends PageAuthVO {
	
	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -1386124119987573586L;

	/**
	 * @Fields equipmentId 设备ID
	 */
	private String equipmentId;
	
	/**
	 * @Fields startDate 开始时间.格式2017-08-17
	 */
	private String startDate;
	
	/**
	 * @Fields endDate 结束时间.格式2017-08-17
	 */
	private String endDate;

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getEquipmentId() {
		return equipmentId;
	}

	public void setEquipmentId(String equipmentId) {
		this.equipmentId = equipmentId;
	}
}
