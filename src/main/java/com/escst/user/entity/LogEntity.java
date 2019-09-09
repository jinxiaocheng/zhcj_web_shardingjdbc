package com.escst.user.entity;

import java.io.Serializable;

public class LogEntity implements Serializable {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -1961706344712876838L;
	private String id;
	/**浏览器名称**/
	private String broswe;
	/**日志内容**/
	private String content;
	/**日志级别 1:正常;2:警告;3:错误**/
	private int level;
	/**日志类型 1:登陆;2:退出;3:日志类型 1:登陆;2:退出;3:插入;4:删除;5:更新;6:上传;7:其他**/
	private int operateType;
	/**操作时间**/
	private String createTime;
	/**用户**/
	private UserEntity userEntity;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getBroswe() {
		return broswe;
	}

	public void setBroswe(String broswe) {
		this.broswe = broswe;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getOperateType() {
		return operateType;
	}

	public void setOperateType(int operateType) {
		this.operateType = operateType;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

}