package com.escst.video.vo;

import com.escst.commons.vo.BaseVO;

import java.io.Serializable;
import java.util.List;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年8月24日 上午9:12:27
 */
public class NvrVO implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -6324034097084207748L;

	/**
	 *@Fields id nvrId
	 **/
	private String id;

	/**
	 * @Fields ip NVR的IP
	 */
	private String ip;
	
	/**
	 * @Fields port 端口
	 */
	private int port;

	private int appPort;
	
	/**
	 * @Fields userName 用户名
	 */
	private String userName;
	
	/**
	 * @Fields password 密码
	 */
	private String password;
	
	/**
	 * @Fields constructionId 工地ID
	 */
	private String constructionId;

	/**
	 * @Fields constructionId 工地名称
	 */
	private String constructionName;

	private String areaId;

	private String areaName;

	private List<CameraVo> vos;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

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

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public List<CameraVo> getVos() {
		return vos;
	}

	public void setVos(List<CameraVo> vos) {
		this.vos = vos;
	}

	public int getAppPort() {
		return appPort;
	}

	public void setAppPort(int appPort) {
		this.appPort = appPort;
	}
}
