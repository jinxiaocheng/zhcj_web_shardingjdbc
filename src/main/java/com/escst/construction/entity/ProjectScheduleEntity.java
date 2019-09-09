package com.escst.construction.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @author niejing
 * @desc 工程进度相关
 * @date 2017年8月18日 下午1:23:17
 */
public class ProjectScheduleEntity extends BaseEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -3025108105675243687L;

    private String id;

    /**
     * 父ID
     */
    private String pId;

    /**
     * 工地名称
     */
    private String constructionName;

    // 总体进度计划ID
    private String scheduledPlanId;

    // 工地ID
    private String constructionId;

    //计划名称
    private String name;

    //计划工期（天）
    private int planDays;

    //实际工期（天）
    private int realDays;

    //工程进度（%）
    private Float percent;

    /**
     * 子节点数量
     */
    private String count;

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    // 状态0：未开始 1：进行中 2已完成
    private String status;

    /**
     * 是否是父节点,open为子节点,closed为父节点
     */
    private String state = "open";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getScheduledPlanId() {
        return scheduledPlanId;
    }

    public void setScheduledPlanId(String scheduledPlanId) {
        this.scheduledPlanId = scheduledPlanId;
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

    public int getPlanDays() {
        return planDays;
    }

    public void setPlanDays(int planDays) {
        this.planDays = planDays;
    }

    public int getRealDays() {
        return realDays;
    }

    public void setRealDays(int realDays) {
        this.realDays = realDays;
    }

    public Float getPercent() {
        return percent;
    }

    public void setPercent(Float percent) {
        this.percent = percent;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }
}
