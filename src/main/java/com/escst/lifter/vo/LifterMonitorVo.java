package com.escst.lifter.vo;

import java.util.List;

/**
 * @author jincheng
 * @desc 升降机监测相关
 * @date 2018-11-5 13:55
 */
public class LifterMonitorVo {

    private String constructionId;

    private String constructionName;

    private List<LifterRealtimeVO> data;


    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public List<LifterRealtimeVO> getData() {
        return data;
    }

    public void setData(List<LifterRealtimeVO> data) {
        this.data = data;
    }
}
