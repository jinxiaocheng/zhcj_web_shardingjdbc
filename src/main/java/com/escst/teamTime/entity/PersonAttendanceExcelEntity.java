package com.escst.teamTime.entity;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import java.io.Serializable;

/**
 * @author jincheng
 * @desc 人员打卡Excel导出
 * @date 2018/2/9 14:41
 */
@ExcelTarget("personAttendanceExcelEntity")
public class PersonAttendanceExcelEntity implements Serializable {

    @Excel(name = "姓名", orderNum = "1", width = 25)
    private String personName;

    @Excel(name = "卡号", orderNum = "2", width = 25)
    private String cardNumber;

    @Excel(name = "打卡时间", orderNum = "3", width = 25)
    private String cardTime;

    @Excel(name = "进场/出场", orderNum = "4", width = 25)
    private String type;


    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public void setCardNumber(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    public String getCardTime() {
        return cardTime;
    }

    public void setCardTime(String cardTime) {
        this.cardTime = cardTime;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
