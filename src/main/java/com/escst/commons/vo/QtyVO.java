package com.escst.commons.vo;

import java.math.BigDecimal;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月15日 下午4:40:26
 */
public class QtyVO {

	private String id;
	
	private String name;
	
	private BigDecimal qty;

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

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
}
