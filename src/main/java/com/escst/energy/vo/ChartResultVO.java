package com.escst.energy.vo;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 14:26 2017/9/19
 */
public class ChartResultVO {

	/**
	 * @Fields xdata x轴的值
	 */
	private List<String> xdata;
	
	/**
	 * @Fields ydata y轴的名称和数值
	 */
	private List<YDataVO> ydata;
	
	/**
	 * @Fields count 所有y轴的值的汇总
	 */
	private List<String> count;

	public List<String> getXdata() {
		return xdata;
	}

	public void setXdata(List<String> xdata) {
		this.xdata = xdata;
	}

	public List<YDataVO> getYdata() {
		return ydata;
	}

	public void setYdata(List<YDataVO> ydata) {
		this.ydata = ydata;
	}

	public List<String> getCount() {
		return count;
	}

	public void setCount(List<String> count) {
		this.count = count;
	}
}
