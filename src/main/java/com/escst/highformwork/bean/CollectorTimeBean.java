package com.escst.highformwork.bean;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 13:49 17/10/2018
 */
public class CollectorTimeBean {

    /**
     *@Fileds flowId 流水段Id
     **/
    private String flowId;
    /**
     *@Fileds ids  采集器Id
     **/
    private List<String> ids;

    public String getFlowId() {
        return flowId;
    }

    public void setFlowId(String flowId) {
        this.flowId = flowId;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
