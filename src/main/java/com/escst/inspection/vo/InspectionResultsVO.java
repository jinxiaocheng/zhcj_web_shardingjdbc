package com.escst.inspection.vo;

import com.escst.commons.vo.BaseVO;

/**
 * @desc 
 * @author niejing
 * @date 2017年11月24日 上午10:31:01
 */
public class InspectionResultsVO extends BaseVO{
	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 3951930153145637775L;

	/**
	 * 检查项父ID
	 */
	private String parentItemsId;
	
	/**
	 * 检查项父名称
	 */
	private String parentItemsName;

	/**
	 * @Fields itemsId 子检查项ID
	 */
	private String itemsId;
	
	/**
	 * @Fields itemsName 子检查项名称
	 */
	private String itemsName;

	public String getParentItemsId() {
		return parentItemsId;
	}

	public void setParentItemsId(String parentItemsId) {
		this.parentItemsId = parentItemsId;
	}

	public String getParentItemsName() {
		return parentItemsName;
	}

	public void setParentItemsName(String parentItemsName) {
		this.parentItemsName = parentItemsName;
	}

	public String getItemsId() {
		return itemsId;
	}

	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}
	
	
}
