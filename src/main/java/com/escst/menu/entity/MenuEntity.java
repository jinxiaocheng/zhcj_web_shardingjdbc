package com.escst.menu.entity;

import com.escst.commons.entity.BaseEntity;

import java.util.List;

/**
 * @author caozx
 * @desc
 * @date 2017/3/6 10:01
 */
public class MenuEntity extends BaseEntity {
	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 8839201633555215411L;

	/**平台菜单父节点ID**/
	public static final String PLATFORM_MENU_PARENT_ID = "1";

	/**APP菜单父节点ID**/
	public static final String APP_MENU_PARENT_ID = "2";

	private String id;
	/** 菜单名称 */
	private String name;
	/** 菜单ur **/
	private String url;
	/** 父菜单ur **/
	private String parentId;
	/** 图标 **/
	private String icon;
	/** 菜单标识.1:平台菜单;2:APP菜单 **/
	private int flag;
	//权限
	private String operationAuthority;
	/** 排序 **/
	private Integer sortNum;

	// 角色ID
	private String roleId;

	// 组织id
	private String orgId;

	/**子菜单**/
	private List<MenuEntity> childMenus;

	
	public String getOperationAuthority() {
		return operationAuthority;
	}

	public void setOperationAuthority(String operationAuthority) {
		this.operationAuthority = operationAuthority;
	}

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

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getIcon() {
		return icon;
	}

	public void setIcon(String icon) {
		this.icon = icon;
	}

	public int getFlag() {
		return flag;
	}

	public void setFlag(int flag) {
		this.flag = flag;
	}

	public Integer getSortNum() {
		return sortNum;
	}

	public void setSortNum(Integer sortNum) {
		this.sortNum = sortNum;
	}

	public List<MenuEntity> getChildMenus() {
		return childMenus;
	}

	public void setChildMenus(List<MenuEntity> childMenus) {
		this.childMenus = childMenus;
	}

	public String getRoleId() {
		return roleId;
	}

	public void setRoleId(String roleId) {
		this.roleId = roleId;
	}

	public String getOrgId() {
		return orgId;
	}

	public void setOrgId(String orgId) {
		this.orgId = orgId;
	}
}
