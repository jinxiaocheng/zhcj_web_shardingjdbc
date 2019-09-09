package com.escst.towerCrane.vo;

import com.escst.commons.vo.PageVo;

import java.math.BigDecimal;

/**
 * @author zhouwei
 * @desc 塔吊实时监测数据
 * @date 2017年8月21日 下午4:10:30
 */
public class TowercraneRealtimeVO extends PageVo {


    private String id;

    private String constructionId;

    private String equipmentId;

    /**
     * @Fields time 采集时间
     */
    private String time;

    /**
     * @Fields angle 角度
     */
    private BigDecimal angle;

    private Integer angleWarning;

    /**
     * @Fields extent 幅度
     */
    private BigDecimal extent;

    private Integer extentWarning;

    /**
     * @Fields height 高度
     */
    private BigDecimal height;

    private Integer heightWarning;

    /**
     * @Fields windSpeed 风速
     */
    private BigDecimal windSpeed;

    private Integer windSpeedWarning;

    /**
     * @Fields weight 重量
     */
    private BigDecimal weight;

    private Integer weightWarning;

    /**
     * @Fields obliquity 倾角
     */
    private BigDecimal obliquity;

    private Integer obliquityWarning;

    /**
     * @Fields percent 力矩百分比
     */
    private BigDecimal percent;

    /**
     * @Fields name 设备名称
     */
    private String name;

    private String startTime;

    private String endTime;

    private Integer type;

    private Integer warning;

    private int count;


    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public BigDecimal getAngle() {
        return angle;
    }

    public void setAngle(BigDecimal angle) {
        this.angle = angle;
    }

    public BigDecimal getExtent() {
        return extent;
    }

    public void setExtent(BigDecimal extent) {
        this.extent = extent;
    }

    public BigDecimal getHeight() {
        return height;
    }

    public void setHeight(BigDecimal height) {
        this.height = height;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
    }

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getObliquity() {
        return obliquity;
    }

    public void setObliquity(BigDecimal obliquity) {
        this.obliquity = obliquity;
    }

    public BigDecimal getPercent() {
        return percent;
    }

    public void setPercent(BigDecimal percent) {
        this.percent = percent;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getAngleWarning() {
        return angleWarning;
    }

    public void setAngleWarning(Integer angleWarning) {
        this.angleWarning = angleWarning;
    }

    public Integer getExtentWarning() {
        return extentWarning;
    }

    public void setExtentWarning(Integer extentWarning) {
        this.extentWarning = extentWarning;
    }

    public Integer getHeightWarning() {
        return heightWarning;
    }

    public void setHeightWarning(Integer heightWarning) {
        this.heightWarning = heightWarning;
    }

    public Integer getWindSpeedWarning() {
        return windSpeedWarning;
    }

    public void setWindSpeedWarning(Integer windSpeedWarning) {
        this.windSpeedWarning = windSpeedWarning;
    }

    public Integer getWeightWarning() {
        return weightWarning;
    }

    public void setWeightWarning(Integer weightWarning) {
        this.weightWarning = weightWarning;
    }

    public Integer getObliquityWarning() {
        return obliquityWarning;
    }

    public void setObliquityWarning(Integer obliquityWarning) {
        this.obliquityWarning = obliquityWarning;
    }

    public Integer getWarning() {
        return warning;
    }

    public void setWarning(Integer warning) {
        this.warning = warning;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public void setCount(int count) {
        this.count = count;
    }
}
