package com.escst.commons.vo;

import java.util.List;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年10月17日 下午2:38:30
 */
public class ParentVO extends BaseVO {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 6084153614851190780L;
	/**
	 * @Fields data 子集合
	 */
	private List<BaseVO> data;

	public List<BaseVO> getData() {
		return data;
	}

	public void setData(List<BaseVO> data) {
		this.data = data;
	}
}
