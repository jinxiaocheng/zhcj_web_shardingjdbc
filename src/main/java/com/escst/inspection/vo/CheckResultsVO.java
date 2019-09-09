package com.escst.inspection.vo;

import com.escst.commons.vo.BaseVO;

import java.io.Serializable;

/**
 * @desc 检查结果VO
 * @author zhouwei
 * @date 2017年10月18日 下午2:51:53
 */
public class CheckResultsVO extends BaseVO implements Serializable {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -1843977872973229395L;

	/**
	 * @Fields itemsId 检查项ID
	 */
	private String itemsId;
	
	/**
	 * @Fields itemsName 检查项名称
	 */
	private String itemsName;

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
