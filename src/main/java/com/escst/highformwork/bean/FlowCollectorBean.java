package com.escst.highformwork.bean;


import com.escst.commons.entity.BaseBillEntity;

/**
 * @author dwj
 * @desc
 * @date 15:00 27/7/2018
 */
public class FlowCollectorBean extends BaseBillEntity {

    /**
     *@Fileds id
     **/
    private String id;
    /**
     *@Fileds collectorId 采集器Id
     **/
    private String collectorId;
    /**
     *@Fileds flowId 流水段Id
     **/
    private String flowId;
    /**
     *@Fileds flowStatus 流水段状态
     **/
    private int flowStatus;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCollectorId() {
        return collectorId;
    }

    public void setCollectorId(String collectorId) {
        this.collectorId = collectorId;
    }

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public int getFlowStatus() {
        return flowStatus;
    }

    public void setFlowStatus(int flowStatus) {
        this.flowStatus = flowStatus;
    }
}
