package com.escst.energy.vo;

import com.escst.commons.vo.PageAuthVO;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年9月21日 下午1:00:28
 */
public class EnergyQueryVO extends PageAuthVO {
	
	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -8638258409215988235L;

	/**
	 * @Fields equipmentType 设备类型.来源EquipmentTypeEnum
	 */
	private Integer equipmentType;
	
	/**
	 * @Fields startDate 开始时间.格式2017-08-17
	 */
	private String startDate;
	
	/**
	 * @Fields endDate 结束时间.格式2017-08-17
	 */
	private String endDate;

	public Integer getEquipmentType() {
		return equipmentType;
	}

	public void setEquipmentType(Integer equipmentType) {
		this.equipmentType = equipmentType;
	}

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
}
