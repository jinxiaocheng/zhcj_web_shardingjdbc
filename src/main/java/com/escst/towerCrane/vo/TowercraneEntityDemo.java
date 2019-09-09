package com.escst.towerCrane.vo;

import java.util.Date;

public class TowercraneEntityDemo {
    /**
	* 主键
	*/
    private String id;

    /**
	* 工地ID
	*/
    private String constructionId;

    /**
	* 设备ID
	*/
    private String equipmentId;

    /**
	* 采集时间
	*/
    private Date acquisitionTime;

    /**
	* 角度
	*/
    private Double angle;

    /**
	* 幅度
	*/
    private Double extent;

    /**
	* 高度
	*/
    private Double height;

    /**
	* 风速
	*/
    private Double windSpeed;

    /**
	* 重量
	*/
    private Double weight;

    /**
	* 倾角
	*/
    private Double obliquity;

    /**
	* 
	*/
    private Double percent;

    /**
	* 创建时间
	*/
    private Date createTime;

    /**
	* 创建人
	*/
    private String createBy;

    /**
	* 更新时间
	*/
    private Date updateTime;

    /**
	* 更新人
	*/
    private String updateBy;

    /**
	* 
	*/
    private Double alForbiddenZone;

    /**
	* 
	*/
    private Double alObstacleCollision;

    /**
	* 
	*/
    private Double alTowerCollision;

    /**
	* 
	*/
    private Double alIncline;

    /**
	* 
	*/
    private Double alWindSpeed;

    /**
	* 
	*/
    private Double alLoad;

    /**
	* 
	*/
    private Double alLimit;

    /**
	* 
	*/
    private Double alHardwareFault;

    /**
	* 
	*/
    private Double alTorque;

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

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public Date getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(Date acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
    }

    public Double getAngle() {
        return angle;
    }

    public void setAngle(Double angle) {
        this.angle = angle;
    }

    public Double getExtent() {
        return extent;
    }

    public void setExtent(Double extent) {
        this.extent = extent;
    }

    public Double getHeight() {
        return height;
    }

    public void setHeight(Double height) {
        this.height = height;
    }

    public Double getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(Double windSpeed) {
        this.windSpeed = windSpeed;
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Double getObliquity() {
        return obliquity;
    }

    public void setObliquity(Double obliquity) {
        this.obliquity = obliquity;
    }

    public Double getPercent() {
        return percent;
    }

    public void setPercent(Double percent) {
        this.percent = percent;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public Double getAlForbiddenZone() {
        return alForbiddenZone;
    }

    public void setAlForbiddenZone(Double alForbiddenZone) {
        this.alForbiddenZone = alForbiddenZone;
    }

    public Double getAlObstacleCollision() {
        return alObstacleCollision;
    }

    public void setAlObstacleCollision(Double alObstacleCollision) {
        this.alObstacleCollision = alObstacleCollision;
    }

    public Double getAlTowerCollision() {
        return alTowerCollision;
    }

    public void setAlTowerCollision(Double alTowerCollision) {
        this.alTowerCollision = alTowerCollision;
    }

    public Double getAlIncline() {
        return alIncline;
    }

    public void setAlIncline(Double alIncline) {
        this.alIncline = alIncline;
    }

    public Double getAlWindSpeed() {
        return alWindSpeed;
    }

    public void setAlWindSpeed(Double alWindSpeed) {
        this.alWindSpeed = alWindSpeed;
    }

    public Double getAlLoad() {
        return alLoad;
    }

    public void setAlLoad(Double alLoad) {
        this.alLoad = alLoad;
    }

    public Double getAlLimit() {
        return alLimit;
    }

    public void setAlLimit(Double alLimit) {
        this.alLimit = alLimit;
    }

    public Double getAlHardwareFault() {
        return alHardwareFault;
    }

    public void setAlHardwareFault(Double alHardwareFault) {
        this.alHardwareFault = alHardwareFault;
    }

    public Double getAlTorque() {
        return alTorque;
    }

    public void setAlTorque(Double alTorque) {
        this.alTorque = alTorque;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", constructionId=").append(constructionId);
        sb.append(", equipmentId=").append(equipmentId);
        sb.append(", acquisitionTime=").append(acquisitionTime);
        sb.append(", angle=").append(angle);
        sb.append(", extent=").append(extent);
        sb.append(", height=").append(height);
        sb.append(", windSpeed=").append(windSpeed);
        sb.append(", weight=").append(weight);
        sb.append(", obliquity=").append(obliquity);
        sb.append(", percent=").append(percent);
        sb.append(", createTime=").append(createTime);
        sb.append(", createBy=").append(createBy);
        sb.append(", updateTime=").append(updateTime);
        sb.append(", updateBy=").append(updateBy);
        sb.append(", alForbiddenZone=").append(alForbiddenZone);
        sb.append(", alObstacleCollision=").append(alObstacleCollision);
        sb.append(", alTowerCollision=").append(alTowerCollision);
        sb.append(", alIncline=").append(alIncline);
        sb.append(", alWindSpeed=").append(alWindSpeed);
        sb.append(", alLoad=").append(alLoad);
        sb.append(", alLimit=").append(alLimit);
        sb.append(", alHardwareFault=").append(alHardwareFault);
        sb.append(", alTorque=").append(alTorque);
        sb.append("]");
        return sb.toString();
    }
}