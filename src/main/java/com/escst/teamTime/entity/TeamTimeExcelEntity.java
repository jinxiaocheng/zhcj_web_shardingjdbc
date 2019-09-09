package com.escst.teamTime.entity;

import org.jeecgframework.poi.excel.annotation.Excel;
import org.jeecgframework.poi.excel.annotation.ExcelTarget;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * @author jincheng
 * @desc 班组工时Excel导出
 * @date 2018/2/9 14:41
 */
@ExcelTarget("teamTimeExcelEntity")
public class TeamTimeExcelEntity implements Serializable {

    @Excel(name = "日期", orderNum = "1", width = 25)
    private String time;

    /**
     * 班组名称
     **/
    @Excel(name = "班组名称", orderNum = "2", width = 25)
    private String name;

    @Excel(name = "出勤人数", orderNum = "3", width = 25)
    private String total;

    @Excel(name = "总工时(小时)", orderNum = "4", width = 25)
    private String totalTime;

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTotal() {
        return total;
    }

    public void setTotal(String total) {
        this.total = total;
    }

    public String getTotalTime() {
        return totalTime;
    }

    public void setTotalTime(String totalTime) {
        this.totalTime = totalTime;
    }
}
