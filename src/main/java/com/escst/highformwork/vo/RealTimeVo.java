package com.escst.highformwork.vo;

import java.util.List;

/**
 * @desc
 * @author niejing
 * @date 2018年7月18日 下午4:56:54
 */
public class RealTimeVo {
	private HighformworkFlowVo flowVo;
	private List<HighformworkRealTimeVo> list;
	public HighformworkFlowVo getFlowVo() {
		return flowVo;
	}
	public void setFlowVo(HighformworkFlowVo flowVo) {
		this.flowVo = flowVo;
	}
	public List<HighformworkRealTimeVo> getList() {
		return list;
	}
	public void setList(List<HighformworkRealTimeVo> list) {
		this.list = list;
	}

	
}
