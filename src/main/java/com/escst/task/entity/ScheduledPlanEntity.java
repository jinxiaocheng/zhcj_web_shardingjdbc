package com.escst.task.entity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 10:49 2017/3/27
 */
public class ScheduledPlanEntity implements Cloneable {

    private String id;
    //父节点ID
    private String parentId;
    private String constructionId;
    //工程结构名称
    private String name;
    private String startDate;
    private String endDate;
    private String createTime;
    private String createBy;
    private String updateBy;
    private String updateTime;

    private List<ScheduledPlanEntity> data;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }

    public List<ScheduledPlanEntity> getData() {
        return data;
    }

    public void setData(List<ScheduledPlanEntity> data) {
        this.data = data;
    }


    @Override
    public Object clone() throws CloneNotSupportedException {
        ScheduledPlanEntity obj = (ScheduledPlanEntity) super.clone();
        List<ScheduledPlanEntity> structureList = new ArrayList<ScheduledPlanEntity>();
        List<ScheduledPlanEntity> list = getData();
        if (list != null) {
            for (ScheduledPlanEntity bean : list) {
                ScheduledPlanEntity b = (ScheduledPlanEntity) bean.clone();
                structureList.add(b);
            }
            obj.setData(structureList);
        }
        return obj;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof ScheduledPlanEntity) {
            ScheduledPlanEntity t = (ScheduledPlanEntity) obj;
            return /*this.name.equals(t.name) &&*/ this.id.equals(t.id);
        }
        return super.equals(obj);
    }


}
