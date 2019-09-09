package com.escst.roleMenu.entity;

import java.io.Serializable;

public class RoleMenuEntity implements Serializable {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 5398721246174922727L;
    private String id;
    private String roleId;
    private String menuId;
    private String operationAuthority;

    //菜单名称
    private String name;
    //父菜单名称
    private String topName;

    /**
     * 排序
     */
    private int sortNum;

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

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRoleId() {
        return roleId;
    }

    public void setRoleId(String roleId) {
        this.roleId = roleId;
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

    public int getSortNum() {
        return sortNum;
    }

    public void setSortNum(int sortNum) {
        this.sortNum = sortNum;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoleMenuEntity that = (RoleMenuEntity) o;

        return menuId != null ? menuId.equals(that.menuId) : that.menuId == null;
    }

    @Override
    public int hashCode() {
        return menuId != null ? menuId.hashCode() : 0;
    }
}
