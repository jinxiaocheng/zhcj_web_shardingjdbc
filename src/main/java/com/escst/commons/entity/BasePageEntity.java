package com.escst.commons.entity;

/**
 * @desc  实体类基类
 * @author caozx
 * @date 2017/3/10 16:06
 */
public class BasePageEntity extends BaseBillEntity {

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 3462599074492905624L;

    /**页号,默认显示第一页**/
    private int pageNum =1;
    /**默认每页显示10条数据**/
    private int pageSize =10;

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }
}
