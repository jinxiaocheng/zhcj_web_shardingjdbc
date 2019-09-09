package com.escst.person.entity;

import java.util.Date;

/**
 * @desc 资质信息
 * @author zhouwei
 * @date 2017年11月2日 下午6:03:04
 */
public class QualificatioEntity {

	private String id;
	// 证书编号
	private String number;
	// 证书类别
	private Integer type;
	// 签发日期
	private String issueDate;
	// 有效期
	private String validDate;
	// 发证机关
	private String issueOrgans;

	//工地ID
	private String constructionId;

	// 用户ID
	private String userId;

	// 状态1:有效;0:无效
	private int status;

	private Date createTime;
	private String createBy;
	private Date updateTime;
	private String updateBy;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(String issueDate) {
		this.issueDate = issueDate;
	}

	public String getValidDate() {
		return validDate;
	}

	public void setValidDate(String validDate) {
		this.validDate = validDate;
	}

	public String getIssueOrgans() {
		return issueOrgans;
	}

	public void setIssueOrgans(String issueOrgans) {
		this.issueOrgans = issueOrgans;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
}
