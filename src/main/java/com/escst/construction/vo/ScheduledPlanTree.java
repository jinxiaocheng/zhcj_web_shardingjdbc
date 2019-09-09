package com.escst.construction.vo;

import com.escst.commons.entity.BaseEntity;

/**
 * @author jincheng
 * @desc 进度计划展示树
 * @date 2018-4-8 13:28
 */
public class ScheduledPlanTree extends BaseEntity {

    private String id;
    // 父节点id
    private String pId;
    // 树节点名称
    private String name;
    // 是否勾选状态
    private Boolean checked = false;
    //是否展开
    private Boolean open = false;
    //图标，可以自定义图标展示
    private String icon;

    private Boolean nocheck = false;

    /**
     * 是否是父节点,open为子节点,closed为父节点
     */
    private String state = "open";

    /**
     * 工地ID
     */
    private String constructionId;


    /**
     * 工地名称
     */
    private String constructionName;

    /**
     * 开始时间
     */
    private String startTime;

    /**
     * 结束时间
     */
    private String endTime;

    /**
     * 子节点数量
     */
    private String count;

    /**
     * 排序
     */
    private String sortNum;



    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Boolean getChecked() {
        return checked;
    }

    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public Boolean getOpen() {
        return open;
    }

    public void setOpen(Boolean open) {
        this.open = open;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
    }

    public Boolean getNocheck() {
        return nocheck;
    }

    public void setNocheck(Boolean nocheck) {
        this.nocheck = nocheck;
    }


    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
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

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getSortNum() {
        return sortNum;
    }

    public void setSortNum(String sortNum) {
        this.sortNum = sortNum;
    }
}
