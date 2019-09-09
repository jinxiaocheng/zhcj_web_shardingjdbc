package com.escst.inspection.vo;

import java.io.Serializable;

import com.escst.commons.vo.BaseVO;

/**
 * @desc 检查项目VO
 * @author zhouwei
 * @date 2017年10月18日 下午2:51:53
 */
public class ItemsVO extends BaseVO implements Serializable {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -1843977872973229395L;
	
	private String resultsId;
	private String resultsName;
	public String getResultsId() {
		return resultsId;
	}
	public void setResultsId(String resultsId) {
		this.resultsId = resultsId;
	}
	public String getResultsName() {
		return resultsName;
	}
	public void setResultsName(String resultsName) {
		this.resultsName = resultsName;
	}

	
	
	

	
}
