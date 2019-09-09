package com.escst.teamTime.entity;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.io.Serializable;

/**
 * @author jincheng
 * @desc 人员工时Excel导出
 * @date 2018/2/9 14:41
 */
@ExcelTarget("personTimeExcelEntity")
public class PersonTimeExcelEntity implements Serializable {

    @Excel(name = "姓名", orderNum = "1", width = 25)
    private String personName;

    @Excel(name = "进场时间", orderNum = "2", width = 25)
    private String upTime;

    @Excel(name = "出场时间", orderNum = "3", width = 25)
    private String downTime;

    @Excel(name = "工时(小时)", orderNum = "4", width = 25)
    private String workTime;

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getUpTime() {
        return upTime;
    }

    public void setUpTime(String upTime) {
        this.upTime = upTime;
    }

    public String getDownTime() {
        return downTime;
    }

    public void setDownTime(String downTime) {
        this.downTime = downTime;
    }

    public String getWorkTime() {
        return workTime;
    }

    public void setWorkTime(String workTime) {
        this.workTime = workTime;
    }
}
