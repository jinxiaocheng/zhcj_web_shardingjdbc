package com.escst.person.entity;

import org.apache.commons.lang3.StringUtils;

/**
 * @desc 人员导出实体类
 * @Author caozx
 * @createDate 2017/3/15 11:22
 */
public class PersonExcelEntity {

    /**
     * 人员名称
     **/
    private String name;

    /**
     * 手机号码
     **/
    private String mobile;

    /**
     * @Fields positionWorkTypeName 岗位/工种名称
     */
    private String positionWorkTypeName;
    
    /**
     * @Fields companyName 所属公司名称
     */
    private String companyName;
    
    /**
     * @Fields jobNumber 工号/门禁卡号
     */
    private String jobNumber;
    
    /**
     * @Fields teamName 班组名称
     */
    private String teamName;
    
    /**
     * @Fields userName 用户帐号
     */
    private String userName;
    
    /**
     * @Fields constructionName 所属工地名称
     */
    private String constructionName;

    /**
     * @desc 判断该对象的所有属性是否都为空
     * @return 
     * @author zhouwei
     * @date 2017年10月27日 上午10:59:41
     */
    public boolean isNull() {
    	String[] properties = new String[]{name, mobile, positionWorkTypeName, companyName, teamName};
    	for (int i = 0; i < properties.length; i++) {
    		if (StringUtils.isNotBlank(name)) {
    			return false;
    		}
    	}
    	return true;
    }
    
	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPositionWorkTypeName() {
		return positionWorkTypeName;
	}

	public void setPositionWorkTypeName(String positionWorkTypeName) {
		this.positionWorkTypeName = positionWorkTypeName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}
}
