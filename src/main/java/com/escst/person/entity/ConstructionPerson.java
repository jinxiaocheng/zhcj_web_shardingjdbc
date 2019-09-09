package com.escst.person.entity;

import java.io.Serializable;

/**
 * @author caozx
 * @desc
 * @date 2017/3/1 18:46
 */
public class ConstructionPerson implements Serializable {
    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 6002879595207154124L;
	private String id;
    /**
     * 人员ID
     **/
    private String personId;
    /**
     * 工地ID
     **/
    private String constructionId;
    /**
     * 工号
     **/
    private int jobNumber;
    /**
     * 状态.1:有效;0:无效
     **/
    private int status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPersonId() {
        return personId;
    }

    public void setPersonId(String personId) {
        this.personId = personId;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public int getJobNumber() {
        return jobNumber;
    }

    public void setJobNumber(int jobNumber) {
        this.jobNumber = jobNumber;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
