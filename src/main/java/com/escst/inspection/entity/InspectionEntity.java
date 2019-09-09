package com.escst.inspection.entity;

import com.escst.commons.entity.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @desc 
 * @author caozx
 * @date 2017年8月7日 下午5:09:55
 */
public class InspectionEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -6457876259487327876L;
	/**待整改**/
	public static final int CORRECTIVE_STATUS_ONE = 1;
	/**待检查**/
	public static final int CORRECTIVE_STATUS_TWO = 2;
	/**检查不通过**/
	public static final int CORRECTIVE_STATUS_THREE = 3;
	/**已整改**/
	public static final int CORRECTIVE_STATUS_FOUR = 4;

	/**处理意见 3：整改**/
	public static final int PROCESSING_OPINION_CORRECTIVE = 3;
	
	private String id;
	
	/**工地ID**/
	private String constructionId;
	
	/**类型.1:质量检查;2:安全检查**/
	private Integer type;
	
	/**检查日期**/
	private String inspectDate;

	/**检查项ID**/
	private String itemsId;
	
	/**检查项**/
	private String items;
	
	/**检查结果**/
	private String results;
	
	/**检查部位ID**/
	private String projectStructureId;
	
	/**分包公司ID**/
	private String projectCompanyId;
	
	/**班组ID**/
	private String teamId;
	
	/**联系人（用户ID）**/
	private String contactId;
	
	/**联系人电话**/
	private String contactMobile;
	
	/**是否有附件.1:有附件;0:没有附件**/
	private Integer isAttach;
	
	/**处理意见.1:通过;2:警告;3:整改**/
	private Integer processingOpinion;
	
	/**整改状态 1:待整改;2:待检查;3:检查不通过;4:已整改**/
	private Integer correctiveStatus;
	
	/**整改完成日期（处理意见为3时有效）**/
	private String correctiveCompletionDate;
	
	/**整改要求**/
	private String correctiveRequest;
	
	/**附件照片,有附件的时候才有**/
    private MultipartFile[] files;

    /**图片路径**/
    private List<String> picList;

	/**
	 * @Fields resultsList 检查结果/检查结果
	 */
	private String resultsList;
	
	/**检查日期开始时间**/
	
	/**
	 * @Fields remark 备注
	 */
	private String remark;
	
	/**
	 * @Fields  抄送通知  (抄送人userId)
	 */
	private String notifyEntityList;
	
	/**
	 * @Fields  检查项目VO 
	 */
	private String itemsVOList;

	private String sortNum;

	private String constructionName;
	private String contactName;
	private Integer status;
	private String projectStructureList;
	//整改单号： 格式 （质量管理） ZL-0001、(安全管理)AQ-0001
	private String orderNo;

	private String date;
	// 0未推送，1已推送
	private Integer isPush;
	// 0新增，1修改
	private Integer operation;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getProjectStructureList() {
		return projectStructureList;
	}

	public void setProjectStructureList(String projectStructureList) {
		this.projectStructureList = projectStructureList;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the constructionId
	 */
	public String getConstructionId() {
		return constructionId;
	}

	/**
	 * @param constructionId the constructionId to set
	 */
	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	/**
	 * @return the type
	 */
	public Integer getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(Integer type) {
		this.type = type;
	}

	/**
	 * @return the inspectDate
	 */
	public String getInspectDate() {
		return inspectDate;
	}

	/**
	 * @param inspectDate the inspectDate to set
	 */
	public void setInspectDate(String inspectDate) {
		this.inspectDate = inspectDate;
	}

	/**
	 * @return the items
	 */
	public String getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(String items) {
		this.items = items;
	}

	/**
	 * @return the results
	 */
	public String getResults() {
		return results;
	}

	/**
	 * @param results the results to set
	 */
	public void setResults(String results) {
		this.results = results;
	}

	/**
	 * @return the projectStructureId
	 */
	public String getProjectStructureId() {
		return projectStructureId;
	}

	/**
	 * @param projectStructureId the projectStructureId to set
	 */
	public void setProjectStructureId(String projectStructureId) {
		this.projectStructureId = projectStructureId;
	}

	/**
	 * @return the projectCompanyId
	 */
	public String getProjectCompanyId() {
		return projectCompanyId;
	}

	/**
	 * @param projectCompanyId the projectCompanyId to set
	 */
	public void setProjectCompanyId(String projectCompanyId) {
		this.projectCompanyId = projectCompanyId;
	}

	/**
	 * @return the teamId
	 */
	public String getTeamId() {
		return teamId;
	}

	/**
	 * @param teamId the teamId to set
	 */
	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	/**
	 * @return the contactId
	 */
	public String getContactId() {
		return contactId;
	}

	/**
	 * @param contactId the contactId to set
	 */
	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	/**
	 * @return the contactMobile
	 */
	public String getContactMobile() {
		return contactMobile;
	}

	/**
	 * @param contactMobile the contactMobile to set
	 */
	public void setContactMobile(String contactMobile) {
		this.contactMobile = contactMobile;
	}

	/**
	 * @return the isAttach
	 */
	public Integer getIsAttach() {
		return isAttach;
	}

	/**
	 * @param isAttach the isAttach to set
	 */
	public void setIsAttach(Integer isAttach) {
		this.isAttach = isAttach;
	}

	/**
	 * @return the processingOpinion
	 */
	public Integer getProcessingOpinion() {
		return processingOpinion;
	}

	/**
	 * @param processingOpinion the processingOpinion to set
	 */
	public void setProcessingOpinion(Integer processingOpinion) {
		this.processingOpinion = processingOpinion;
	}

	/**
	 * @return the correctiveStatus
	 */
	public Integer getCorrectiveStatus() {
		return correctiveStatus;
	}

	/**
	 * @param correctiveStatus the correctiveStatus to set
	 */
	public void setCorrectiveStatus(Integer correctiveStatus) {
		this.correctiveStatus = correctiveStatus;
	}

	/**
	 * @return the correctiveCompletionDate
	 */
	public String getCorrectiveCompletionDate() {
		return correctiveCompletionDate;
	}

	/**
	 * @param correctiveCompletionDate the correctiveCompletionDate to set
	 */
	public void setCorrectiveCompletionDate(String correctiveCompletionDate) {
		this.correctiveCompletionDate = correctiveCompletionDate;
	}

	/**
	 * @return the correctiveRequest
	 */
	public String getCorrectiveRequest() {
		return correctiveRequest;
	}

	/**
	 * @param correctiveRequest the correctiveRequest to set
	 */
	public void setCorrectiveRequest(String correctiveRequest) {
		this.correctiveRequest = correctiveRequest;
	}

	/**
	 * @return the files
	 */
	public MultipartFile[] getFiles() {
		return files;
	}

	/**
	 * @param files the files to set
	 */
	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	/**
	 * @return the picList
	 */
	public List<String> getPicList() {
		return picList;
	}

	/**
	 * @param picList the picList to set
	 */
	public void setPicList(List<String> picList) {
		this.picList = picList;
	}

	public String getResultsList() {
		return resultsList;
	}

	public void setResultsList(String resultsList) {
		this.resultsList = resultsList;
	}

	public String getItemsId() {
		return itemsId;
	}

	public void setItemsId(String itemsId) {
		this.itemsId = itemsId;
	}
		/*备注*/
	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
	/*抄送 通知(人)*/

	public String getNotifyEntityList() {
		return notifyEntityList;
	}

	public void setNotifyEntityList(String notifyEntityList) {
		this.notifyEntityList = notifyEntityList;
	}

	public String getItemsVOList() {
		return itemsVOList;
	}

	public void setItemsVOList(String itemsVOList) {
		this.itemsVOList = itemsVOList;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getSortNum() {
		return sortNum;
	}

	public void setSortNum(String sortNum) {
		this.sortNum = sortNum;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public Integer getIsPush() {
		return isPush;
	}

	public void setIsPush(Integer isPush) {
		this.isPush = isPush;
	}

	public Integer getOperation() {
		return operation;
	}

	public void setOperation(Integer operation) {
		this.operation = operation;
	}
}
