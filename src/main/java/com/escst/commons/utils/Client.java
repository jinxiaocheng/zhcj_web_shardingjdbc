package com.escst.commons.utils;

import com.escst.user.entity.UserEntity;

import java.util.Date;
import java.util.Map;

/**
 * 在线用户对象
 * @author caozx
 * @date 2017-2-14
 * @version 1.0
 */
public class Client implements java.io.Serializable {

	private static final long serialVersionUID = 1L;
	
	private UserEntity userEntity;

	/**
	 * 用户IP
	 */
	private String ip;
	/**
	 *登录时间
	 */
	private Date logindatetime;

	public UserEntity getUserEntity() {
		return userEntity;
	}

	public void setUserEntity(UserEntity userEntity) {
		this.userEntity = userEntity;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public Date getLogindatetime() {
		return logindatetime;
	}

	public void setLogindatetime(Date logindatetime) {
		this.logindatetime = logindatetime;
	}
}
