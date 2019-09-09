package com.escst.construction.vo;

/**
 * @author dwj
 * @desc
 * @date 9:24 2018/2/23
 */
public class ConstructionScheduleVo {

    /**
     * @Fields percent 工程进度百分比
     **/
    private String percent;
    /**
     * @Fields daysRemain 剩余天数
     **/
    private int daysRemain;
    /**
     *@Fields name 项目名称
     **/
    private String name;

    /**
     *@Fields startDate 项目开始时间
     **/
    private String startDate;
    /**
     *@Fields endDate 项目结束时间
     **/
    private String endDate;


    public String getPercent() {
        return percent;
    }

    public void setPercent(String percent) {
        this.percent = percent;
    }

    public int getDaysRemain() {
        return daysRemain;
    }

    public void setDaysRemain(int daysRemain) {
        this.daysRemain = daysRemain;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
