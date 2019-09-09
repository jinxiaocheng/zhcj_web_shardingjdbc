package com.escst.message.entity;

import com.escst.commons.entity.BaseEntity;

import java.util.List;

/**
 * @desc
 * @author niejing
 * @date 2018年2月6日 下午2:56:26
 */
public class MessageEntity extends BaseEntity{

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 4434707031684448870L;
	private String id;
	private String title;
	private String content;
	private Integer type;
	private Integer isRead;
	private String constructionId;
	private String billId;
	private String acceptId;
	private String[] acceptIds;
	private List<String> acceptNames;
	public String getAcceptId() {
		return acceptId;
	}
	public void setAcceptId(String acceptId) {
		this.acceptId = acceptId;
	}
	public String[] getAcceptIds() {
		return acceptIds;
	}
	public void setAcceptIds(String[] acceptIds) {
		this.acceptIds = acceptIds;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	public Integer getIsRead() {
		return isRead;
	}
	public void setIsRead(Integer isRead) {
		this.isRead = isRead;
	}
	public String getConstructionId() {
		return constructionId;
	}
	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
	public String getBillId() {
		return billId;
	}
	public void setBillId(String billId) {
		this.billId = billId;
	}

	public List<String> getAcceptNames() {
		return acceptNames;
	}

	public void setAcceptNames(List<String> acceptNames) {
		this.acceptNames = acceptNames;
	}
}
