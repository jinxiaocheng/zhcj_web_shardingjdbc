package com.escst.taskAppointment.entity;

import com.escst.commons.entity.BaseEntity;

import java.io.Serializable;
import java.util.Date;

/**
 * @author dwj
 * @desc
 * @date 15:45 2017/4/15
 */
public class TaskAppointmentEntity extends BaseEntity implements Serializable {

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -2489169260884658315L;
	private String id;
    private String  projectStructureId;
    private String projectCompanyId;
    //  是否是紧急任务 1:是2::否
    private int isUrgentTask;
    private String constructionId;
    //预约设备类型 1:塔吊;2:升降机
    private int equipmentType;
    private String appointmentDate;
    private String remark;
    private String teamId;
    //预约人Id
    private String appointmentId;
    //状态 1::待处理; 2:待调整;3:处理中;4:已完成
    private int status;
    private Date createTime;
    private Date updateTime;
    private String createBy;
    private String updateBy;
    private String handlePerson;
    //优先级
    private int type;

    private String areaId;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getProjectStructureId() {
        return projectStructureId;
    }

    public void setProjectStructureId(String projectStructureId) {
        this.projectStructureId = projectStructureId;
    }

    public String getProjectCompanyId() {
        return projectCompanyId;
    }

    public void setProjectCompanyId(String projectCompanyId) {
        this.projectCompanyId = projectCompanyId;
    }

    public int getIsUrgentTask() {
        return isUrgentTask;
    }

    public void setIsUrgentTask(int isUrgentTask) {
        this.isUrgentTask = isUrgentTask;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getAppointmentDate() {
        return appointmentDate;
    }

    public void setAppointmentDate(String appointmentDate) {
        this.appointmentDate = appointmentDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getTeamId() {
        return teamId;
    }

    public void setTeamId(String teamId) {
        this.teamId = teamId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }


    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
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


    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public String getHandlePerson() {
        return handlePerson;
    }

    public void setHandlePerson(String handlePerson) {
        this.handlePerson = handlePerson;
    }

    @Override
    public String getAreaId() {
        return areaId;
    }

    @Override
    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }
}
