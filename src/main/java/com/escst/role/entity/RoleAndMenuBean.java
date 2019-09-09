package com.escst.role.entity;

import java.io.Serializable;

/**
 * @author niejing
 * @desc
 * @date 2017年7月6日 下午3:40:13
 */
public class RoleAndMenuBean implements Serializable {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 3176242566821995832L;

    // 角色ID
    private String roleId;
    // 菜单ID
    private String menuId;
    // 角色名称
    private String name;
    // 描述
    private String remark;
    // 组织id
    private String orgId;

    //菜单ID，权限json字符串
    private String menuAuthMap;

    private String roleName;

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
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

    public String getMenuAuthMap() {
        return menuAuthMap;
    }

    public void setMenuAuthMap(String menuAuthMap) {
        this.menuAuthMap = menuAuthMap;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public String getOrgId() {
        return orgId;
    }

    public void setOrgId(String orgId) {
        this.orgId = orgId;
    }

}
