package com.escst.video.entity;

import com.escst.commons.entity.BaseEntity;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 14:05 2018/3/19
 */
public class NvrDeployEntity extends BaseEntity {

    /**
     *@Fileds id
     **/
    private String id;
    /**
     *@Fileds name 设备名称
     **/
    private String name;
    /**
     *@Fileds  model 设备类型
     **/
    private String model;
    /**
     *@Fileds ip 设备IP地址
     **/
    private String ip;
    /**
     *@Fileds webPort 设备Web端端口
     **/
    private int webPort;
    /**
     *@Fileds appPort 设备app端端口
     **/
    private int appPort;
    /**
     *@Fileds userName 设备用户名
     **/
    private String userName;
    /**
     *@Fileds userPassword 设备密码
     **/
    private String password;
    /**
     *@Fileds constructionId 工地Id
     **/
    private String constructionId;
    /**
     *设备端口
     **/
    private int port;
    /**
     *摄像头通道集合
     **/
    private List<CameraEntity> cameraEntityList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public int getWebPort() {
        return webPort;
    }

    public void setWebPort(int webPort) {
        this.webPort = webPort;
    }

    public int getAppPort() {
        return appPort;
    }

    public void setAppPort(int appPort) {
        this.appPort = appPort;
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

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public List<CameraEntity> getCameraEntityList() {
        return cameraEntityList;
    }

    public void setCameraEntityList(List<CameraEntity> cameraEntityList) {
        this.cameraEntityList = cameraEntityList;
    }
}
