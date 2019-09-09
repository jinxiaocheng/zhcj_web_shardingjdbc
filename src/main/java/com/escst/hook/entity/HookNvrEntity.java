package com.escst.hook.entity;


import com.escst.commons.entity.BaseEntity;
import com.escst.hook.bean.HookCameraBean;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 16:23 22/10/2018
 */
public class HookNvrEntity extends BaseEntity {

    /**
     *@Fileds id
     **/
    private String id;
    /**
     *@Fileds name NVR名称
     **/
    private String name;
    /**
     *@Fileds constructionId 工地Id
     **/
    private String constructionId;
    /**
     *@Fileds constructionName 工地名
     **/
    private String constructionName;
    /**
     *@Fileds ip ip地址
     **/
    private String ip;
    /**
     *@Fileds webPort web端口
     **/
    private String webPort;
    /**
     *@Fileds appPort app端口
     **/
    private String appPort;
    /**
     *@Fileds userName 用户名
     **/
    private String userName;
    /**
     *@Fileds userPassword 密码
     **/
    private String userPassword;
    /**
     *@Fileds port 端口
     **/
    private String port;
    /**
     *@Fileds list 设备通道集合
     **/
    private List<HookCameraBean> list;

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

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getWebPort() {
        return webPort;
    }

    public void setWebPort(String webPort) {
        this.webPort = webPort;
    }

    public String getAppPort() {
        return appPort;
    }

    public void setAppPort(String appPort) {
        this.appPort = appPort;
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

    public String getPort() {
        return port;
    }

    public void setPort(String port) {
        this.port = port;
    }

    public List<HookCameraBean> getList() {
        return list;
    }

    public void setList(List<HookCameraBean> list) {
        this.list = list;
    }
}
