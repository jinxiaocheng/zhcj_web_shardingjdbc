package com.escst.document.bean;

import com.escst.commons.entity.BaseEntity;

import java.util.Date;

/**
 * @author dwj
 * @desc
 * @date 14:24 2017/12/6
 */
public class DocumentBean extends BaseEntity {

    private String id;
    private String constructionId;
    private String remark;
    private int status;
    private Date createTime;
    private String typeId;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
}
