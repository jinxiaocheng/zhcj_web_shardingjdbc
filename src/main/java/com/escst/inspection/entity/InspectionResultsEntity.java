package com.escst.inspection.entity;

import java.io.Serializable;

/**
 * @desc 检查结果
 * @author zhouwei
 * @date 2017年10月17日 下午5:09:53
 */
public class InspectionResultsEntity implements Serializable {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -2041648290564706809L;

	private String id;
	
	/**
	 * @Fields inspectionId 检查单ID
	 */
	private String inspectionId;

	/**
	 * @Fields parentId 根检查项ID
	 */
	private String parentItemsId;
	
	/**
	 * @Fields parentName 根检查项名称
	 */
	private String parentItemsName;
	

	/**
	 * @Fields itemsId 子检查项id
	 */
	private String itemsId;
	
	/**
	 * @Fields itemsName 子检查项名称
	 */
	private String itemsName;
	
	
	/**
	 * @Fields resultsId 检查结果ID
	 */
	private String resultsId;
	
	/**
	 * @Fields results 检查结果
	 */
	private String results;
	
	/**
	 * @Fields score 得分.如果为正数,则为得分;如果为负数,则为扣分
	 */
	private Integer score;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getResultsId() {
		return resultsId;
	}

	public void setResultsId(String resultsId) {
		this.resultsId = resultsId;
	}

	public String getResults() {
		return results;
	}

	public void setResults(String results) {
		this.results = results;
	}

	public Integer getScore() {
		return score;
	}

	public void setScore(Integer score) {
		this.score = score;
	}

	public String getInspectionId() {
		return inspectionId;
	}

	public void setInspectionId(String inspectionId) {
		this.inspectionId = inspectionId;
	}

	public String getParentId() {
		return parentItemsId;
	}

	public void setParentId(String parentId) {
		this.parentItemsId = parentId;
	}

	public String getParentName() {
		return parentItemsName;
	}

	public void setParentName(String parentName) {
		this.parentItemsName = parentName;
	}

	public String getItemsName() {
		return itemsName;
	}

	public void setItemsName(String itemsName) {
		this.itemsName = itemsName;
	}

	public String getItemsId() {
		return itemsId;
	}

	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}

	
}
