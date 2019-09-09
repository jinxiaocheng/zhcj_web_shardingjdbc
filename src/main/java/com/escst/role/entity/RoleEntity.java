package com.escst.role.entity;

import com.escst.commons.entity.BaseEntity;

public class RoleEntity extends BaseEntity {
    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = -8982974238216495335L;
    private String id;
    // 角色名称
    private String name;
    // 描述
    private String remark;
    // 组织id
    private String orgId;



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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
