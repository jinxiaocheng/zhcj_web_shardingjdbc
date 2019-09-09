package com.escst.excelimport.vo;

import org.apache.commons.lang3.StringUtils;

import com.escst.commons.constant.Globals;
import com.escst.commons.utils.MD5Util;

/**
 * @desc 
 * @author zhouwei
 * @date 2017年12月5日 下午4:28:19
 */
public class PersonImportVO implements IExcelImportVO {

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
	 * @Fields teamName 班组名称
	 */
	private String teamName;
    
    /**
     * @Fields jobNumber 工号/门禁卡号
     */
    private String jobNumber;

    /**
     * @Fields cardNumber 身份证号
     */
    private String cardNumber;

    /**
     * @Fields bankCard 银行卡号
     */
    private String bankCard;

    
    private String constructionId;
    
    private String positionWorkTypeId;
    
    /**
     * @Fields constructionName 所属工地名称
     */
    private String constructionName;
    
    /**
     * @Fields userPassword 用户密码
     */
    private String userPassword;


	private String clientUserId;

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

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public String getPositionWorkTypeId() {
		return positionWorkTypeId;
	}

	public void setPositionWorkTypeId(String positionWorkTypeId) {
		this.positionWorkTypeId = positionWorkTypeId;
	}

	public String getUserPassword() {
		String password = getMobile() + Globals.USER_DEFAULT_PASSWORD;
		userPassword = MD5Util.md5Encode(password);
		return userPassword;
	}

	public void setUserPassword(String userPassword) {
		this.userPassword = userPassword;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}
}
