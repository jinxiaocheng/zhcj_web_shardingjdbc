package com.escst.thirdPart.bean;

/**
 * @author dwj
 * @desc
 * @date 10:29 2/8/2018
 */
public class CountBean {

    /**
     *@Fileds startTime 开始时间
     **/
    private String startTime;
    /**
     *@Fileds endTime 结束时间
     **/
    private String endTime;
    /**
     *@Fileds type 类型
     **/
    private int type;
    /**
     *@Fileds departId 项目Id
     **/
    private String departId;
    /**
     *@Files value
     **/
    private int value;


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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getDepartId() {
        return departId;
    }

    public void setDepartId(String departId) {
        this.departId = departId;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }
}
