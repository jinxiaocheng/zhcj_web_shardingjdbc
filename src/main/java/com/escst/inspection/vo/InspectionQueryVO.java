package com.escst.inspection.vo;

import com.escst.commons.vo.PageAuthVO;

/**
 * @desc 质量和安全检查的查询VO
 * @author zhouwei
 * @date 2017年8月26日 上午10:13:23
 */
public class InspectionQueryVO extends PageAuthVO {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -8006450331919270874L;

	/**质量检查**/
	public static final Integer QUALITY_INSPECTION = 1;

	/**安全检查**/
	public static final Integer SAFETY_INSPECTION = 2;
	
	/**安全检查**/
	public static final Integer DAILY_SAFETY_INSPECTION = 3;
	/**
	 * @Fields itemsId 检查项ID
	 */
	private String itemsId;

	/**
	 * @Fields type 类型.1:质量检查;2:施工安全分项检查;3:日常安全检查
	 */
	private String types;
	
	/**
	 * @Fields startDate 检查开始时间.格式2017-08-17
	 */
	private String startDate;
	
	/**
	 * @Fields endDate 检查结束时间.格式2017-08-17
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

	public String getItemsId() {
		return itemsId;
	}

	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}

	public String getTypes() {
		return types;
	}

	public void setTypes(String types) {
		this.types = types;
	}
}
