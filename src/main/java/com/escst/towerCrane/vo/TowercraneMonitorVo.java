package com.escst.towerCrane.vo;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 17:01 2018/4/11
 */
public class TowercraneMonitorVo {

    private String constructionId;

    private String constructionName;

    private List<TowercraneRealtimeVO> data;


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

    public List<TowercraneRealtimeVO> getData() {
        return data;
    }

    public void setData(List<TowercraneRealtimeVO> data) {
        this.data = data;
    }
}
