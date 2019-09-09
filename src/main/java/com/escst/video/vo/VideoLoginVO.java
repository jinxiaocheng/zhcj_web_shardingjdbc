package com.escst.video.vo;

import java.io.Serializable;

/**
 * @desc 视频登录信息
 * @author zhouwei
 * @date 2017年8月24日 上午9:12:27
 */
public class VideoLoginVO implements Serializable {
	
	/**
	 * @Fields ip NVR的IP
	 */
	private String ip;
	
	/**
	 * @Fields port 端口
	 */
	private int port;
	
	/**
	 * @Fields userName 用户名
	 */
	private String userName;
	
	/**
	 * @Fields password 密码
	 */
	private String password;

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public int getPort() {
		return port;
	}

	public void setPort(int port) {
		this.port = port;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
