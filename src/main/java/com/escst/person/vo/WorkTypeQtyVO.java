package com.escst.person.vo;

import java.util.List;
import java.util.Map;

/**
 * @desc
 * @author niejing
 * @date 2017年11月6日 下午2:42:34
 */
public class WorkTypeQtyVO {

	private List<WorkTypeDateVO> dataVo;

	private List<Map<String,Object>> dicList;

	public List<WorkTypeDateVO> getDataVo() {
		return dataVo;
	}

	public void setDataVo(List<WorkTypeDateVO> dataVo) {
		this.dataVo = dataVo;
	}

	public List<Map<String, Object>> getDicList() {
		return dicList;
	}

	public void setDicList(List<Map<String, Object>> dicList) {
		this.dicList = dicList;
	}

}
