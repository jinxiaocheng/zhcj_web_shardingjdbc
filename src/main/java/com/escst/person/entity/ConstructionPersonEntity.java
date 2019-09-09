package com.escst.person.entity;

import java.io.Serializable;

/**
 * @author caozx
 * @desc
 * @date 2017/3/1 18:46
 */
public class ConstructionPersonEntity implements Serializable {
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
     * 卡号
     */
    private String cardNumber;
    /**
     * 状态.1:有效;0:无效
     **/
    private int status;

    //分包公司ID
    private String projectCompanyId;
    
    //班组ID
    private String teamId;
    
    /**
     * @Fields isLeader 是否班组长
     */
    private Integer isLeader;

    private String name;

    private String clientUserId;

    /**
     * 银行卡号
     */
    private String bankCard;
    

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public String getProjectCompanyId() {
		return projectCompanyId;
	}

	public void setProjectCompanyId(String projectCompanyId) {
		this.projectCompanyId = projectCompanyId;
	}

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

	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClientUserId() {
        return clientUserId;
    }

    public void setClientUserId(String clientUserId) {
        this.clientUserId = clientUserId;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }
}
