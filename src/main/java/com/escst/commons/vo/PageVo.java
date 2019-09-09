package com.escst.commons.vo;

import java.util.List;

/**
 * @author caozx
 * @desc
 * @date 2017/3/13 10:26
 */
public class PageVo {


    /**
     * 当前页码
     **/
    private int currentPage;

    /**
     * 总页数
     **/
    private int totalPage;

    /**
     * 总记录数
     **/
    private int totalRecord;

    /**
     * 每页显示多少条记录
     **/
    private int pageSize;

    /**
     * 当前查询页的数据集合
     **/
    private List<?> rows;

    private int startIndex;

    private Integer page;

    private Integer rowNum;

    private int count;
    
    public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(int currentPage) {
        this.currentPage = currentPage;
    }

    public int getTotalPage() {
    	int pageSize = getPageSize();
    	if (pageSize == 0) {
    		return 0;
    	}
        return (getTotalRecord() + pageSize - 1) / pageSize;
    }

    public void setTotalPage(int totalPage) {
        this.totalPage = totalPage;
    }

    public int getTotalRecord() {
        return totalRecord;
    }

    public void setTotalRecord(int totalRecord) {
        this.totalRecord = totalRecord;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public List<?> getRows() {
        return rows;
    }

    public void setRows(List<?> rows) {
        this.rows = rows;
    }

    public int getStartIndex() {
        return startIndex;
    }

    public void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }
}
