package com.escst.taskAppointment.entity;

import java.util.Date;

/**
 * @author dwj
 * @desc
 * @date 16:28 2017/4/18
 */
public class TaskAppointmentProcessEntity {

    private String id;
    private String taskAppointmentId;
    private String appointmentId;
    //任务预约状态 1::待处理; 2:待调整;3:处理中;4:已完成
    private int taskStatus;
    private String remark;
    private Date createTime;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskAppointmentId() {
        return taskAppointmentId;
    }

    public void setTaskAppointmentId(String taskAppointmentId) {
        this.taskAppointmentId = taskAppointmentId;
    }

    public String getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(String appointmentId) {
        this.appointmentId = appointmentId;
    }

    public int getTaskStatus() {
        return taskStatus;
    }

    public void setTaskStatus(int taskStatus) {
        this.taskStatus = taskStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }


    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

}
