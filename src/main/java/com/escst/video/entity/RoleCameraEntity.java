package com.escst.video.entity;

import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author dwj
 * @desc
 * @date 11:31 2018/5/2
 */
public class RoleCameraEntity {

    /**
     *@Fileds id
     */
    private String id;

    /**
     * @Fileds roleId 用户Id
     */
    private String roleId;

    /**
     *@Fileds cameraId 开始摄像头Id集合
     */
    private List<String> firstCameraIds;

    /**
     *@Fileds cameraId 结束摄像头Id集合
     */
    private List<String> lastCameraIds;

    /**
     *@Fileds cameraId 摄像头Id
     */
    private String cameraId;

    /**
     *@Fileds createTime 创建时间
     */
    private Date createTime;

    /**
     *@Fileds updateTime 创建时间
     */
    private Date updateTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
    }

    public List<String> getFirstCameraIds() {
        return firstCameraIds;
    }

    public void setFirstCameraIds(List<String> firstCameraIds) {
        this.firstCameraIds = firstCameraIds;
    }

    public List<String> getLastCameraIds() {
        return lastCameraIds;
    }

    public void setLastCameraIds(List<String> lastCameraIds) {
        this.lastCameraIds = lastCameraIds;
    }

    public String getCameraId() {
        return cameraId;
    }

    public void setCameraId(String cameraId) {
        this.cameraId = cameraId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
