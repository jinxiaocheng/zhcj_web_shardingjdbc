package com.escst.towerCrane.vo;

import com.escst.commons.vo.PageVo;

/**
 * @author jincheng
 * @desc 查询条件相关
 * @date 2018-11-6 14:23
 */
public class QueryConditionVo extends PageVo {

    private String equipmentId;

    private String startTime;

    private String endTime;

    private Integer type;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

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

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
