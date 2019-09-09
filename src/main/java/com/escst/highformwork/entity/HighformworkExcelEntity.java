package com.escst.highformwork.entity;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.io.Serializable;

/**
 * @author jincheng
 * @desc 高支模数据Excel导出
 * @date 2018/2/9 14:41
 */
@ExcelTarget("highformworkExcelEntity")
public class HighformworkExcelEntity implements Serializable {

    @Excel(name = "测点名称", orderNum = "1", width = 25)
    private String equipmentName;

    @Excel(name = "测点编号", orderNum = "2", width = 25)
    private String number;

    @Excel(name = "测量时间", orderNum = "3", width = 25)
    private String createTime;

    @Excel(name = "X倾角(°)", orderNum = "4", width = 25)
    private String xAngle;

    @Excel(name = "Y倾角(°)", orderNum = "5", width = 25)
    private String yAngle;

    @Excel(name = "压力(KN)", orderNum = "6", width = 25)
    private String kap;

    @Excel(name = "位移(mm)", orderNum = "7", width = 25)
    private String displace;

    @Excel(name = "可用电量(%)", orderNum = "8", width = 25)
    private String electric;

    @Excel(name = "温度(℃)", orderNum = "9", width = 25)
    private String temperature;

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getxAngle() {
        return xAngle;
    }

    public void setxAngle(String xAngle) {
        this.xAngle = xAngle;
    }

    public String getyAngle() {
        return yAngle;
    }

    public void setyAngle(String yAngle) {
        this.yAngle = yAngle;
    }

    public String getKap() {
        return kap;
    }

    public void setKap(String kap) {
        this.kap = kap;
    }

    public String getDisplace() {
        return displace;
    }

    public void setDisplace(String displace) {
        this.displace = displace;
    }

    public String getElectric() {
        return electric;
    }

    public void setElectric(String electric) {
        this.electric = electric;
    }

    public String getTemperature() {
        return temperature;
    }

    public void setTemperature(String temperature) {
        this.temperature = temperature;
    }
}
