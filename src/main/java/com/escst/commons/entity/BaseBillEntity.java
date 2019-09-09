package com.escst.commons.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年12月20日 下午2:13:09
 */
public class BaseBillEntity implements Serializable {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -3196253954326019671L;
	private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;
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
}
