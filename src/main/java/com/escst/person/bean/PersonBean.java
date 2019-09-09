package com.escst.person.bean;

/**
 * @desc
 * @Author wy
 * @createDate 2017/3/9 15:22
 */
public class PersonBean {
    private String name;
    private String constructionId;
    private int pageNum = 1;
    private int pageSize = 10;


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

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
