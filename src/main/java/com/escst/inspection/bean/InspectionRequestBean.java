package com.escst.inspection.bean;

import com.escst.commons.vo.PageAuthVO;

/**
 * @author caozx
 * @desc
 * @date 2017/10/30 14:12
 */
public class InspectionRequestBean extends PageAuthVO {

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -6062629764693836237L;

    /**质量检查**/
    public static final Integer QUALITY_INSPECTION = 1;

    /**安全检查**/
    public static final Integer SAFETY_INSPECTION = 2;

    /**日常安全检查**/
    public static final Integer DALIY_SAFETY_INSPECTION = 3;
    
	
    /**类型.1:质量检查;2:安全检查**/
    private Integer type;

    /**处理意见.1:通过;2:警告;3:整改**/
    private Integer processingOpinion;

    /**联系人（用户ID）**/
    private String contactId;

    /**整改状态 1:待整改;2:待检查;3:检查不通过;4:已整改**/
    private Integer correctiveStatus;

    /**检查开始日期**/
    private String startDate;

    /**检查结束日期**/
    private String endDate;

    /**检查项,质量检查时表示检查项目名称，安全检查表示检查项目ID**/
    private String items;

    /**整改开始时间**/
    private String correctStartDate;

    /**整改结束时间**/
    private String correctEndDate;

    private String projectCompanyId;

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public Integer getProcessingOpinion() {
        return processingOpinion;
    }

    public void setProcessingOpinion(Integer processingOpinion) {
        this.processingOpinion = processingOpinion;
    }

    public String getContactId() {
        return contactId;
    }

    public void setContactId(String contactId) {
        this.contactId = contactId;
    }

    public Integer getCorrectiveStatus() {
        return correctiveStatus;
    }

    public void setCorrectiveStatus(Integer correctiveStatus) {
        this.correctiveStatus = correctiveStatus;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getCorrectStartDate() {
        return correctStartDate;
    }

    public void setCorrectStartDate(String correctStartDate) {
        this.correctStartDate = correctStartDate;
    }

    public String getCorrectEndDate() {
        return correctEndDate;
    }

    public void setCorrectEndDate(String correctEndDate) {
        this.correctEndDate = correctEndDate;
    }

    public String getProjectCompanyId() {
        return projectCompanyId;
    }

    public void setProjectCompanyId(String projectCompanyId) {
        this.projectCompanyId = projectCompanyId;
    }
}
