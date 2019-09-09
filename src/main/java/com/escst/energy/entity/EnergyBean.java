package com.escst.energy.entity;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 9:56 2017/9/13
 */
public class EnergyBean {

    private String name;

    private List<?> data;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<?> getData() {
        return data;
    }

    public void setData(List<?> data) {
        this.data = data;
    }
}
