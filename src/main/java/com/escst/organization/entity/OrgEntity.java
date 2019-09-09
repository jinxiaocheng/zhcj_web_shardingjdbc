package com.escst.organization.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * 机构实体类
 */
public class OrgEntity extends BaseEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -2868818156698523946L;
    private String id;
    /**
     * 父节点ID
     **/
    private String parentId;
    /**
     * 名称
     **/
    private String name;
    /**
     * 级别
     */
    private Integer level;
    /**
     * 组织编码
     **/
    private String sysCode;
    /**
     * 排序
     **/
    private Integer sortNum;
    /**
     * 状态
     **/
    private Integer status;

    /**
     * 工地ID
     */
    private String constructionId;

    /**
     * @Fields logoName logo名称
     */
    private String logoName;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getSysCode() {
        return sysCode;
    }

    public void setSysCode(String sysCode) {
        this.sysCode = sysCode;
    }

    public Integer getSortNum() {
        return sortNum;
    }

    public void setSortNum(Integer sortNum) {
        this.sortNum = sortNum;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getLogoName() {
        return logoName;
    }

    public void setLogoName(String logoName) {
        this.logoName = logoName;
    }


}
