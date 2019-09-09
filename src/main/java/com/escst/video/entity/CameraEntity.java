package com.escst.video.entity;

import java.io.Serializable;

/**
 * @author caozx
 * @desc
 * @date 2017/2/16 15:16
 */
    public class CameraEntity implements Serializable {

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 6544797455824151998L;
	private String id;
    /**工地ID**/
    private String constructionId;
    /**工地名称**/
    private String constructionName;
    /**摄像头名称**/
    private String name;
    /**摄像头品牌（1:海康; 2:宇视）**/
    private int supplier;
    /**标识.1:可云台控制;0:不能云台控制**/
    private int flag;
    /**通道号**/
    private int channelNo;
    /**状态.1:在线;0:离线**/
    private Integer status;
    /**海康摄像头ID**/
    private int hikCameraId;
    /**海康平台编码**/
    private String hikSysCode;
    /**海康平台国标IndexCode**/
    private String hikGbIndexcode;
    /**海康平台外网IP**/
    private String hikOutsideIp;
    /**海康平台摄像头用户名**/
    private String hikCameraUsername;
    /**海康平台摄像头密码**/
    private String hikCameraPassword;

    private String hikXml;

    private String sign;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(int channelNo) {
        this.channelNo = channelNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public int getHikCameraId() {
        return hikCameraId;
    }

    public void setHikCameraId(int hikCameraId) {
        this.hikCameraId = hikCameraId;
    }

    public String getHikSysCode() {
        return hikSysCode;
    }

    public void setHikSysCode(String hikSysCode) {
        this.hikSysCode = hikSysCode;
    }

    public String getHikGbIndexcode() {
        return hikGbIndexcode;
    }

    public void setHikGbIndexcode(String hikGbIndexcode) {
        this.hikGbIndexcode = hikGbIndexcode;
    }

    public String getHikOutsideIp() {
        return hikOutsideIp;
    }

    public void setHikOutsideIp(String hikOutsideIp) {
        this.hikOutsideIp = hikOutsideIp;
    }

    public String getHikCameraUsername() {
        return hikCameraUsername;
    }

    public void setHikCameraUsername(String hikCameraUsername) {
        this.hikCameraUsername = hikCameraUsername;
    }

    public String getHikCameraPassword() {
        return hikCameraPassword;
    }

    public void setHikCameraPassword(String hikCameraPassword) {
        this.hikCameraPassword = hikCameraPassword;
    }

	public String getHikXml() {
		return hikXml;
	}

	public void setHikXml(String hikXml) {
		this.hikXml = hikXml;
	}

    public String getSign() {
        return sign;
    }

    public void setSign(String sign) {
        this.sign = sign;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CameraEntity that = (CameraEntity) o;

        if (supplier != that.supplier) return false;
        if (flag != that.flag) return false;
        if (channelNo != that.channelNo) return false;
        if (hikCameraId != that.hikCameraId) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (constructionId != null ? !constructionId.equals(that.constructionId) : that.constructionId != null)
            return false;
        if (constructionName != null ? !constructionName.equals(that.constructionName) : that.constructionName != null)
            return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (hikSysCode != null ? !hikSysCode.equals(that.hikSysCode) : that.hikSysCode != null) return false;
        if (hikGbIndexcode != null ? !hikGbIndexcode.equals(that.hikGbIndexcode) : that.hikGbIndexcode != null)
            return false;
        if (hikOutsideIp != null ? !hikOutsideIp.equals(that.hikOutsideIp) : that.hikOutsideIp != null) return false;
        if (hikCameraUsername != null ? !hikCameraUsername.equals(that.hikCameraUsername) : that.hikCameraUsername != null)
            return false;
        if (hikCameraPassword != null ? !hikCameraPassword.equals(that.hikCameraPassword) : that.hikCameraPassword != null)
            return false;
        if (hikXml != null ? !hikXml.equals(that.hikXml) : that.hikXml != null) return false;
        return sign != null ? sign.equals(that.sign) : that.sign == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (constructionId != null ? constructionId.hashCode() : 0);
        result = 31 * result + (constructionName != null ? constructionName.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + supplier;
        result = 31 * result + flag;
        result = 31 * result + channelNo;
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + hikCameraId;
        result = 31 * result + (hikSysCode != null ? hikSysCode.hashCode() : 0);
        result = 31 * result + (hikGbIndexcode != null ? hikGbIndexcode.hashCode() : 0);
        result = 31 * result + (hikOutsideIp != null ? hikOutsideIp.hashCode() : 0);
        result = 31 * result + (hikCameraUsername != null ? hikCameraUsername.hashCode() : 0);
        result = 31 * result + (hikCameraPassword != null ? hikCameraPassword.hashCode() : 0);
        result = 31 * result + (hikXml != null ? hikXml.hashCode() : 0);
        result = 31 * result + (sign != null ? sign.hashCode() : 0);
        return result;
    }
}
