package com.escst.hook.vo;

import com.escst.commons.vo.PageVo;

/**
 * @author jincheng
 * @desc 吊钩数据监测相关
 * @date 2018-10-22 13:48
 */
public class HookDataVo extends PageVo {

    private String constructionId;
    private String constructionName;
    private String hookId;
    private String hookName;
    /**
     * 采集时间
     */
    private String acquisitionTime;
    /**
     * 幅度
     */
    private Float extent;
    /**
     * 高度
     */
    private Float height;
    private String createTime;

    private String startTime;

    private String endTime;

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

    public String getHookId() {
        return hookId;
    }

    public void setHookId(String hookId) {
        this.hookId = hookId;
    }

    public String getHookName() {
        return hookName;
    }

    public void setHookName(String hookName) {
        this.hookName = hookName;
    }

    public String getAcquisitionTime() {
        return acquisitionTime;
    }

    public void setAcquisitionTime(String acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
    }

    public Float getExtent() {
        return extent;
    }

    public void setExtent(Float extent) {
        this.extent = extent;
    }

    public Float getHeight() {
        return height;
    }

    public void setHeight(Float height) {
        this.height = height;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
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
}
