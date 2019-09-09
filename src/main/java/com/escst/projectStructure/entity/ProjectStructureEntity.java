package com.escst.projectStructure.entity;

import java.util.Date;
import java.util.List;

import com.escst.commons.entity.BaseEntity;
import com.escst.projectStructure.vo.StructureVo;

/**
 * @author dwj
 * @desc
 * @date 10:49 2017/4/24
 */
public class ProjectStructureEntity extends BaseEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -8001705903267951823L;
    private String id;
    //工地ID
    private String constructionId;
    //父ID
    private String parentId;
    //检查部位名
    private String name;
    //开始时间
    private String startDate;
    //开始时间
    private String endDate;
    private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;

    //工地名称，仅供展示用
    private String constructionName;

    private int sortNum;

    private List<StructureVo> children;

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
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

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }
}
