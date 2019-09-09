package com.escst.highformwork.vo;

/**
 * @author dwj
 * @desc
 * @date 14:47 17/7/2018
 */
public class CollectorVo {

    /**
     *@Fileds id
     **/
    private String id;

    /**
     *@Fileds name 采集器名
     **/
    private String name;

    /**
     *@Fileds number 采集器编号
     **/
    private String number;

    /**
     *@Fileds status 状态  1、启用；0：停用
     **/

    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
