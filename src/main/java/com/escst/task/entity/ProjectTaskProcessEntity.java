package com.escst.task.entity;

import com.escst.commons.entity.BaseEntity;
import com.escst.file.vo.FilePathVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 19:27 2017/4/10
 */
public class ProjectTaskProcessEntity  {

    /**工程任务状态*/
    private static final long serialVersionUID = 8218924553212759396L;
    /**工程任务状态*/
    /**
     * @Fields TASK_STATUS_DISPATCHEDWORKERS 待受理
     */
    public static final int TASK_STATUS_DISPATCHEDWORKERS = 1;   //待受理
    /**
     * @Fields TASK_STATUS_ALREADYDISPATCHED 处理中
     */
    public static final int TASK_STATUS_ALREADYDISPATCHED= 2;    //处理中
    /**
     * @Fields TASK_STATUS_INTREATMNET 待检查
     */
    public static final int TASK_STATUS_INTREATMNET= 3;          //待检查
    /**
     * @Fields TASK_STATUS_PRELIMINARYINSPECTION 检查不通过
     */
    public static final int TASK_STATUS_PRELIMINARYINSPECTION = 4;   //检查不通过
    /**
     * @Fields TASK_STATUS_FINISHED 已完成
     */
    public static final int TASK_STATUS_FINISHED = 5;      //已完成
    private String id;
    private String taskId;
    private String personId;
    private String contactsName;
    private String contactsMobile;
    // 状态 1:待受理; 2:处理中;3:待检查;4:检查不通过；5:已完成
    private int taskStatus;
    private String remark;
    private int isAttach;
    private String createTime;
    /**图片附件路径集合**/
    private List<FilePathVO> picList;
    /**
     * 附件照片,有附件的时候才有
     **/
    private MultipartFile[] files;

    /**
     *@Fileds 单号
     **/
    private String orderNo;


    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
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

    public int getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(int isAttach) {
        this.isAttach = isAttach;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }



    public String getContactsName() {
        return contactsName;
    }

    public void setContactsName(String contactsName) {
        this.contactsName = contactsName;
    }

    public String getContactsMobile() {
        return contactsMobile;
    }

    public void setContactsMobile(String contactsMobile) {
        this.contactsMobile = contactsMobile;
    }

    public List<FilePathVO> getPicList() {
        return picList;
    }

    public void setPicList(List<FilePathVO> picList) {
        this.picList = picList;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
