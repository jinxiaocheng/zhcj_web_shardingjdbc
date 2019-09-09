package com.escst.towerCrane.vo;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author dwj
 * @desc
 * @date 13:44 2018/3/16
 */
@ExcelTarget("equipmentExcelVo")
public class TowerCraneExcelVo implements Serializable{

    @Excel(name = "采集时间", orderNum = "1",width = 25)
    private String acquisitionTime;

    @Excel(name = "角度",orderNum = "1", width = 25)
    private BigDecimal angle;

    @Excel(name = "幅度",orderNum = "1",width = 25)
    private BigDecimal extent;

    @Excel(name = "高度",orderNum = "1",width = 25)
    private BigDecimal height;

    @Excel(name = "重量",orderNum = "1",width = 25)
    private BigDecimal weight;

    @Excel(name = "风速",orderNum = "1",width = 25)
    private BigDecimal windSpeed;

    @Excel(name = "倾角",orderNum = "1",width = 25)
    private BigDecimal obliquity;

    @Excel(name ="力矩",orderNum = "1",width = 25)
    private BigDecimal percent;


    public String getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(String acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
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

    public BigDecimal getWeight() {
        return weight;
    }

    public void setWeight(BigDecimal weight) {
        this.weight = weight;
    }

    public BigDecimal getWindSpeed() {
        return windSpeed;
    }

    public void setWindSpeed(BigDecimal windSpeed) {
        this.windSpeed = windSpeed;
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
}
