package com.escst.positionWork.vo;

/**
 * @desc 
 * @author niejing
 * @date 2018年9月19日 下午1:44:02
 */
public class PositionWorkVo {

	//工地iD
	private String constructionId;
	//岗位工种名称
	private String name;
	//类型
	private int type;
	
	public String getConstructionId() {
		return constructionId;
	}
	public String getName() {
		return name;
	}
	public int getType() {
		return type;
	}
	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
	public void setName(String name) {
		this.name = name;
	}
	public void setType(int type) {
		this.type = type;
	}
	
	
}
