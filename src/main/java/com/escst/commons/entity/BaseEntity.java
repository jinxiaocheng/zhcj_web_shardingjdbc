package com.escst.commons.entity;

import java.io.Serializable;
import java.util.Date;

/**
 * @desc  实体类基类
 * @author caozx
 * @date 2017/3/10 16:06
 */
public class BaseEntity implements Serializable {

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -5006309349600899739L;
	
	private Date createTime;
    private String createBy;
    private Date updateTime;
    private String updateBy;

    /**页号,默认显示第一页**/
    private int page;
    /**默认每页显示10条数据**/
    private int rowNum;

    /**每页第一条记录的索引**/
    private int startIndex = 0;

    //当前用户ID（权限过滤需要用到）
    private String userId;

    //区域Id（区域过滤时用）
    private String areaId;

    //区域名
    private String areaName;

    public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
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

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }
}
