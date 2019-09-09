package com.escst.inspection.vo;

import com.escst.file.vo.FilePathVO;

import java.util.Date;
import java.util.List;

/**
 * @author caozx
 * @desc
 * @date 2017/08/10
 */
public class CorrectiveDetailVO {

    private String id;

    /**整改日期**/
    private Date createTime;

    /**整改日期字符串**/
    private String createTimeStr;

    /**备注**/
    private String remark;

    /**是否有附件**/
    private Integer isAttach;

    /**整改状态**/
    private Integer correctiveStatus;

    /**整改提交人**/
    private String createBy;

    /**图片附件路径集合**/
    private List<FilePathVO> picList;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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

    public Integer getCorrectiveStatus() {
        return correctiveStatus;
    }

    public void setCorrectiveStatus(Integer correctiveStatus) {
        this.correctiveStatus = correctiveStatus;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public List<FilePathVO> getPicList() {
        return picList;
    }

    public void setPicList(List<FilePathVO> picList) {
        this.picList = picList;
    }

    public String getCreateTimeStr() {
        return createTimeStr;
    }

    public void setCreateTimeStr(String createTimeStr) {
        this.createTimeStr = createTimeStr;
    }
}
