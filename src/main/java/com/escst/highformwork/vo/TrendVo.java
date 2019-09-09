package com.escst.highformwork.vo;

import java.util.List;

/**
 * @desc 
 * @author niejing
 * @date 2018年7月20日 下午5:05:57
 */
public class TrendVo {

	private String createTime;
	private List<TrendResultVo> list;
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public List<TrendResultVo> getList() {
		return list;
	}
	public void setList(List<TrendResultVo> list) {
		this.list = list;
	}
	
	
}
