package com.escst.energy.vo;

import com.escst.commons.vo.QtyVO;

/**
 * @author dwj
 * @desc
 * @date 14:30 2017/9/19
 */
public class DateQtyVO extends QtyVO {

	// id:设备ID; name:设备名称; qty:统计数量
	
	/**
	 * @Fields date 统计时间
	 */
	private String date;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}
}
