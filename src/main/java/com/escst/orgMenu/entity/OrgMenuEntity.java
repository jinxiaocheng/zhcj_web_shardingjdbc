package com.escst.orgMenu.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @author jincheng
 * @desc 机构菜单关系实体类
 * @date 2018/1/17 11:56
 */
public class OrgMenuEntity implements Serializable {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 5398721246174922727L;
    // 主键
    private String id;
    // 机构id
    private String orgId;
    // 菜单id
    private String menuId;
    // 按钮权限
    private String operationAuthority;
    //菜单名称
    private String name;
    //父菜单名称
    private String topName;

    /**
     * 排序
     */
    private int sortNum;

    private List<String> beDeleteButton;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

    public String getMenuId() {
        return menuId;
    }

    public void setMenuId(String menuId) {
        this.menuId = menuId;
    }

    public String getOperationAuthority() {
        return operationAuthority;
    }

    public void setOperationAuthority(String operationAuthority) {
        this.operationAuthority = operationAuthority;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTopName() {
        return topName;
    }

    public void setTopName(String topName) {
        this.topName = topName;
    }

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    public List<String> getBeDeleteButton() {
        return beDeleteButton;
    }

    public void setBeDeleteButton(List<String> beDeleteButton) {
        this.beDeleteButton = beDeleteButton;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OrgMenuEntity that = (OrgMenuEntity) o;

        return menuId != null ? menuId.equals(that.menuId) : that.menuId == null;
    }

    @Override
    public int hashCode() {
        return menuId != null ? menuId.hashCode() : 0;
    }
}
