package com.escst.inspection.vo;

import java.math.BigDecimal;

/**
 * @desc 检查数量统计
 * @author zhouwei
 * @date 2017年8月26日 上午10:08:49
 */
public class InspectionQtyVO {

	/**
	 * @Fields date 统计时间
	 */
	private String date;
	
	/**
	 * @Fields qty 检查数量
	 */
	private BigDecimal qty;
	
	/**
	 * @Fields correctiveQty 整改数量
	 */
	private BigDecimal correctiveQty;

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}

	public BigDecimal getCorrectiveQty() {
		return correctiveQty;
	}

	public void setCorrectiveQty(BigDecimal correctiveQty) {
		this.correctiveQty = correctiveQty;
	}
}
