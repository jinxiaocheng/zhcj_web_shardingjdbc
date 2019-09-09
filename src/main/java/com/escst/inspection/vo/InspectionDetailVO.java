package com.escst.inspection.vo;

import com.escst.file.vo.FilePathVO;
import com.escst.inspection.entity.InspectionResultsEntity;
import com.escst.inspection.entity.NotifyEntity;

import java.util.List;
import java.util.Map;

/**
 * @author caozx
 * @desc  质量检查安全检查展示对象
 * @date 2017/08/10
 */
public class InspectionDetailVO {

    /**检查日期**/
    private String businessDate;

    /**检查项目**/
    private String items;

    /**检查结果**/
    private String results;

    /**检查部位**/
    private String projectStructureName;

    /**分包公司**/
    private String projectCompanyName;

    /**班组**/
    private String teamName;
    
    /**责任人ID**/
    private String contactId;

    /**联系人**/
    private String contactName;

    /**联系人电话**/
    private String contactMobile;

    /**处理意见 1:通过;2:警告;3:整改**/
    private Integer status;

    /**整改状态**/
    private Integer correctiveStatus;

    /**整改日期**/
    private String correctiveCompletionDate;

    /**整改要求**/
    private String correctiveRequest;

    /**是否有附件**/
    private Integer isAttach;
    
    /**检查人ID**/
    private String createBy;

    /**图片附件路径集合**/
    private List<FilePathVO> picList;
    
    /**备注**/
    private String remark;

    /**处理意见.1:通过;2:警告;3:整改**/
    private Integer processingOption;
    
    /**通知**/
    private List<NotifyEntity> notifyEntityList;
    
    /**检查的结果树 **/
    private List<?> treeVOList;
    
    
    /**检查结果集合**/
    private List<InspectionResultsEntity> checkResultList;

    private Map<String,List<InspectionResultsEntity>> checkResultMap;
    
    private String orderNo;

    private String otherItems;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public String getBusinessDate() {
        return businessDate;
    }

    public void setBusinessDate(String businessDate) {
        this.businessDate = businessDate;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public String getResults() {
        return results;
    }

    public void setResults(String results) {
        this.results = results;
    }

    public String getProjectStructureName() {
        return projectStructureName;
    }

    public void setProjectStructureName(String projectStructureName) {
        this.projectStructureName = projectStructureName;
    }

    public String getProjectCompanyName() {
        return projectCompanyName;
    }

    public void setProjectCompanyName(String projectCompanyName) {
        this.projectCompanyName = projectCompanyName;
    }

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public String getContactName() {
        return contactName;
    }

    public void setContactName(String contactName) {
        this.contactName = contactName;
    }

    public String getContactMobile() {
        return contactMobile;
    }

    public void setContactMobile(String contactMobile) {
        this.contactMobile = contactMobile;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Integer getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(Integer isAttach) {
        this.isAttach = isAttach;
    }

    public List<FilePathVO> getPicList() {
        return picList;
    }

    public void setPicList(List<FilePathVO> picList) {
        this.picList = picList;
    }

    public List<InspectionResultsEntity> getCheckResultList() {
        return checkResultList;
    }

    public void setCheckResultList(List<InspectionResultsEntity> checkResultList) {
        this.checkResultList = checkResultList;
    }

    public Map<String, List<InspectionResultsEntity>> getCheckResultMap() {
        return checkResultMap;
    }

    public void setCheckResultMap(Map<String, List<InspectionResultsEntity>> checkResultMap) {
        this.checkResultMap = checkResultMap;
    }

    public Integer getCorrectiveStatus() {
        return correctiveStatus;
    }

    public void setCorrectiveStatus(Integer correctiveStatus) {
        this.correctiveStatus = correctiveStatus;
    }

    public String getCorrectiveCompletionDate() {
        return correctiveCompletionDate;
    }

    public void setCorrectiveCompletionDate(String correctiveCompletionDate) {
        this.correctiveCompletionDate = correctiveCompletionDate;
    }

    public String getCorrectiveRequest() {
        return correctiveRequest;
    }

    public void setCorrectiveRequest(String correctiveRequest) {
        this.correctiveRequest = correctiveRequest;
    }

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public List<NotifyEntity> getNotifyEntityList() {
		return notifyEntityList;
	}

	public void setNotifyEntityList(List<NotifyEntity> notifyEntityList) {
		this.notifyEntityList = notifyEntityList;
	}

    public List<?> getTreeVOList() {
        return treeVOList;
    }

    public void setTreeVOList(List<?> treeVOList) {
        this.treeVOList = treeVOList;
    }

    public Integer getProcessingOption() {
        return processingOption;
    }

    public void setProcessingOption(Integer processingOption) {
        this.processingOption = processingOption;
    }

	public String getContactId() {
		return contactId;
	}

	public void setContactId(String contactId) {
		this.contactId = contactId;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

    public String getOtherItems() {
        return otherItems;
    }

    public void setOtherItems(String otherItems) {
        this.otherItems = otherItems;
    }
}
