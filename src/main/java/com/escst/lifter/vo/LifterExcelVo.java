package com.escst.lifter.vo;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author dwj
 * @desc
 * @date 14:38 2018/3/16
 */
@ExcelTarget("lifterExcelVo")
public class LifterExcelVo implements Serializable {

    /**
     * @Fields time 采集时间
     */
    @Excel(name = "采集时间",orderNum = "1",width = 25)
    private String time;

    /**
     * @Fields height 高度
     */
    @Excel(name = "高度",orderNum = "1",width = 25)
    private BigDecimal height;

    /**
     * @Fields weight 重量
     */
    @Excel(name = "重量",orderNum = "1",width = 25)
    private BigDecimal weight;

    /**
     * @Fields obliquity 倾斜
     */
    @Excel(name = "倾斜",orderNum = "1",width = 25)
    private BigDecimal obliquity;

    /**
     * @Fields peopleNum 人数
     */
    @Excel(name = "人数",orderNum = "1",width = 25)
    private Integer peopleNum;

    /**
     * @Fields speed 速度
     */
    @Excel(name = "速度",orderNum = "1",width = 25)
    private BigDecimal speed;

    /**
     * @Fields driverName 司机名称
     */
    @Excel(name = "司机名称",orderNum = "1",width = 25)
    private String driverName;

    /**
     * @Fields driverNo 司机身份证号
     */
    @Excel(name = "司机身份证号",orderNum = "1",width = 25)
    private String driverNo;

    /**
     * @Fields floorNo 楼层
     */
    @Excel(name = "楼层",orderNum = "1",width = 25)
    private int floorNo;

    /**
     * @Fields frontDoorLockState 前门锁报警开关
     */
    @Excel(name = "前门锁报警开关",orderNum = "1",width = 25)
    private int frontDoorLockState;

    /**
     * @Fields backDoorLockState 后门锁报警开关
     */
    @Excel(name = "后门锁报警开关",orderNum = "1",width = 25)
    private int backDoorLockState;

    /**
     * @Fields highLimitState 高限位报警开关
     */
    @Excel(name = "高限位报警开关",orderNum = "1",width = 25)
    private int highLimitState;

    /**
     * @Fields lowLimitState 低限位报警开关
     */
    @Excel(name = "低限位报警开关",orderNum = "1",width = 25)
    private int lowLimitState;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
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

    public BigDecimal getObliquity() {
        return obliquity;
    }

    public void setObliquity(BigDecimal obliquity) {
        this.obliquity = obliquity;
    }

    public Integer getPeopleNum() {
        return peopleNum;
    }

    public void setPeopleNum(Integer peopleNum) {
        this.peopleNum = peopleNum;
    }

    public BigDecimal getSpeed() {
        return speed;
    }

    public void setSpeed(BigDecimal speed) {
        this.speed = speed;
    }

    public String getDriverName() {
        return driverName;
    }

    public void setDriverName(String driverName) {
        this.driverName = driverName;
    }

    public String getDriverNo() {
        return driverNo;
    }

    public void setDriverNo(String driverNo) {
        this.driverNo = driverNo;
    }

    public int getFloorNo() {
        return floorNo;
    }

    public void setFloorNo(int floorNo) {
        this.floorNo = floorNo;
    }

    public int getFrontDoorLockState() {
        return frontDoorLockState;
    }

    public void setFrontDoorLockState(int frontDoorLockState) {
        this.frontDoorLockState = frontDoorLockState;
    }

    public int getBackDoorLockState() {
        return backDoorLockState;
    }

    public void setBackDoorLockState(int backDoorLockState) {
        this.backDoorLockState = backDoorLockState;
    }

    public int getHighLimitState() {
        return highLimitState;
    }

    public void setHighLimitState(int highLimitState) {
        this.highLimitState = highLimitState;
    }

    public int getLowLimitState() {
        return lowLimitState;
    }

    public void setLowLimitState(int lowLimitState) {
        this.lowLimitState = lowLimitState;
    }
}
