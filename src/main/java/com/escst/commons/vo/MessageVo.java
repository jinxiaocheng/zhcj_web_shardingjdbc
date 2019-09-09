package com.escst.commons.vo;

import java.util.Set;

/**
 * 发送消息请求VO
 * @author Administrator
 *
 */
public class MessageVo {

    /**标题**/
    private String title;

    /**内容**/
    private String content;

    /**类型 0：系统消息；1：质量整改；2：安全整改；3：任务派发；4：塔吊预约；5：升降机预约**/
    private Integer type;

    /**是否已读，1：已读；0:未读**/
    private int isRead;

    /**工地ID**/
    private String constructionId;

    /**业务单据ID**/
    private String billId;

    private Integer status;
    
    //需要发送消息的userId集合
    private Set<String> users;
    /**创建人createBy**/
    private String createBy;
    /**id**/
    private String id;
    /**创建时间createTime**/
    private String createTime;
    /**接收人数量acceptNum**/
    private int acceptNum;
    /**操作权限auth**/
    private Boolean auth;

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

	public int getIsRead() {
		return isRead;
	}

	public void setIsRead(int isRead) {
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

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Set<String> getUsers() {
		return users;
	}

	public void setUsers(Set<String> users) {
		this.users = users;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public int getAcceptNum() {
		return acceptNum;
	}

	public void setAcceptNum(int acceptNum) {
		this.acceptNum = acceptNum;
	}

	public Boolean getAuth() {
		return auth;
	}

	public void setAuth(Boolean auth) {
		this.auth = auth;
	}
}
