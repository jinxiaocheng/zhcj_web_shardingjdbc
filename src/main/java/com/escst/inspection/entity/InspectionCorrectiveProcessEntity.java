package com.escst.inspection.entity;

import com.escst.commons.entity.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author caozx
 * @desc 整改记录详细信息
 * @date 2017/4/11 13:34
 */
public class InspectionCorrectiveProcessEntity extends BaseEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 5943049594014142862L;
    /**
     * 待整改
     **/
    public static final int CORRECTIVE_STATUS_ONE = 1;
    /**
     * 待检查
     **/
    public static final int CORRECTIVE_STATUS_TWO = 2;
    /**
     * 检查不通过
     **/
    public static final int CORRECTIVE_STATUS_THREE = 3;
    /**
     * 已整改
     **/
    public static final int CORRECTIVE_STATUS_FOURE = 4;

    private String id;
    /**
     * 整改记录ID
     **/
    private String inspectionId;
    /**
     * 处理人
     **/
    private String personId;
    /**
     * 整改状态 1:待整改;2:待检查;3:检查不通过;4:已整改
     **/
    private Integer correctiveStatus;
    /**
     * 备注
     **/
    private String remark;
    /**
     * 是否有附件.1:有附件;0:没有附件
     **/
    private Integer isAttach;
    /**
     * 附件照片,有附件的时候才有
     **/
    private MultipartFile[] files;

    /**
     * 图片路径
     **/
    private List<String> picList;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public Integer getCorrectiveStatus() {
        return correctiveStatus;
    }

    public void setCorrectiveStatus(Integer correctiveStatus) {
        this.correctiveStatus = correctiveStatus;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(Integer isAttach) {
        this.isAttach = isAttach;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }
}
