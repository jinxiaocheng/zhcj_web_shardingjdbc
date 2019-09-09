package com.escst.menu.vo;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年7月27日 下午3:05:25
 */
public class MenuVO {

	/**
	 * @Fields parentId 父节点ID
	 */
	private String parentId;
	
	/**
	 * @Fields userId 用户ID
	 */
	private String userId;
	
	/**
	 * @Fields flag 标识.1:平台菜单;2:APP菜单
	 */
	private Integer flag;

	public String getParentId() {
		return parentId;
	}

	public void setParentId(String parentId) {
		this.parentId = parentId;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public Integer getFlag() {
		return flag;
	}

	public void setFlag(Integer flag) {
		this.flag = flag;
	}
}
