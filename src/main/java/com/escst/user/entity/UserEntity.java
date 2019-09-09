package com.escst.user.entity;

import com.escst.commons.entity.BaseEntity;
import com.escst.construction.vo.SimpleConstructionVO;

import java.util.List;

public class UserEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -353333779791166269L;
	private String id;
	/**组织ID**/
	private String orgId;
	/**用户账号**/
	private String userName;
	/**用户密码**/
	private String userPassword;
	/**用户状态**/
	private Integer status;
	/**用户姓名**/
	private String name;
	/**用户性别**/
	private Integer sex;
	/**用户年龄**/
	private Integer age;
	/**手机号码**/
	private String mobile;
	/**邮箱**/
	private String email;
	/**身份证**/
	private String idCard;

	/**用户所属工地集合**/
	private List<SimpleConstructionVO> constructionList;
	//工地id
	private String constructionId;
	
	
	public String getConstructionId() {
		return constructionId;
	}
	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getOrgId() {
		return orgId;
	}
	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserPassword() {
		return userPassword;
	}
	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}
	public Integer getStatus() {
		return status;
	}
	public void setStatus(Integer status) {
		this.status = status;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getSex() {
		return sex;
	}
	public void setSex(Integer sex) {
		this.sex = sex;
	}
	public Integer getAge() {
		return age;
	}
	public void setAge(Integer age) {
		this.age = age;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getIdCard() {
		return idCard;
	}
	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public List<SimpleConstructionVO> getConstructionList() {
		return constructionList;
	}

	public void setConstructionList(List<SimpleConstructionVO> constructionList) {
		this.constructionList = constructionList;
	}
}
