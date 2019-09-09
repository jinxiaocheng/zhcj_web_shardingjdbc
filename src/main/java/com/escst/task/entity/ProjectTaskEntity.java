package com.escst.task.entity;

import com.escst.commons.entity.BaseEntity;

import java.util.Date;

/**
 * @author dwj
 * @desc
 * @date 19:25 2017/4/10
 */
public class ProjectTaskEntity extends BaseEntity{
    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 7698382308183607335L;
	private String id;
    private String constructionId;
    private String projectStructureId;
    private String projectQuantityUnit;
    private float projectQuantityEstimate;
    private String projectCompanyId;
    private String personId;
    private String remark;
    private String userId;
    private String teamId;
    //状态 1:待受理; 2:处理中;3:待检查;4:检查不通过；5:已完成
    private int status;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
    private String areaId;

    /**
     *任务派发单号
     **/
    private String orderNo;


    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getProjectStructureId() {
        return projectStructureId;
    }

    public void setProjectStructureId(String projectStructureId) {
        this.projectStructureId = projectStructureId;
    }

    public String getProjectQuantityUnit() {
        return projectQuantityUnit;
    }

    public void setProjectQuantityUnit(String projectQuantityUnit) {
        this.projectQuantityUnit = projectQuantityUnit;
    }

    public float getProjectQuantityEstimate() {
        return projectQuantityEstimate;
    }

    public void setProjectQuantityEstimate(float projectQuantityEstimate) {
        this.projectQuantityEstimate = projectQuantityEstimate;
    }

    public String getProjectCompanyId() {
        return projectCompanyId;
    }

    public void setProjectCompanyId(String projectCompanyId) {
        this.projectCompanyId = projectCompanyId;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public String getCreateBy() {
        return createBy;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }


    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    @Override
    public String getAreaId() {
        return areaId;
    }

    @Override
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
