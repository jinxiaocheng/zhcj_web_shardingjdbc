package com.escst.worktrend.entity;

import com.escst.file.vo.FilePathVO;

import java.util.Date;
import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 16:21 2018/2/22
 */
public class WorkTrendEntity {

    /**
     * @Fields id
     **/
    private String id;
    /**
     * @Fields 企业内容
     **/
    private String companyContent;
    /**
     * @Fields 业务Id
     **/
    private String businessId;
    /**
     * @Fields 业务时间
     **/
    private String businessTime;
    /**
     *@Fields 业务人员Id
     **/
    private String userId;
    /**
     * @Fields 项目Id
     **/
    private String constructionId;
    /**
     * @Fields 创建时间
     **/
    private Date createTime;
    /**
     * @Fields 人员姓名
     * */
    private String userName;

    /**图片附件路径集合**/
    private List<FilePathVO> picList;
    /**
     *@Fields 项目内容
     **/
    private String constructionContent;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCompanyContent() {
        return companyContent;
    }

    public void setCompanyContent(String companyContent) {
        this.companyContent = companyContent;
    }

    public String getConstructionContent() {
        return constructionContent;
    }

    public void setConstructionContent(String constructionContent) {
        this.constructionContent = constructionContent;
    }

    public String getBusinessId() {
        return businessId;
    }

    public void setBusinessId(String businessId) {
        this.businessId = businessId;
    }

    public String getBusinessTime() {
        return businessTime;
    }

    public void setBusinessTime(String businessTime) {
        this.businessTime = businessTime;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public List<FilePathVO> getPicList() {
        return picList;
    }

    public void setPicList(List<FilePathVO> picList) {
        this.picList = picList;
    }
}
