package com.escst.material.entity;

import org.springframework.web.multipart.MultipartFile;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc 材料领用实体类
 * @Author wy
 * @createDate 2017/3/22 13:27
 */
public class MaterialAcquisitionEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -2057985851235028053L;
	private String id;
	/** 工地ID */
	private String constructionId;

	/** 材料名称 */
	private String name;

	/** 材料进场ID */
	private String approachId;
	/** 领用日期 */
	private String recipientsDate;
	/** 领用数量 */
	private Float quantity;
	/** 人员ID */
	private String personId;
	/** 备注 */
	private String remark;
	/** 是否有附件：1-有附件，0-没附件 */
	private Integer isAttach;
	/** 附件 */
	private MultipartFile[] files;
	/** 状态（1：待确认；2：驳回确认；3：已取消；4：已确认） */
	private Integer status;
	// 材料ID
	private String materialId;

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getApproachId() {
		return approachId;
	}

	public void setApproachId(String approachId) {
		this.approachId = approachId;
	}

	public String getRecipientsDate() {
		return recipientsDate;
	}

	public void setRecipientsDate(String recipientsDate) {
		this.recipientsDate = recipientsDate;
	}

	public Float getQuantity() {
		return quantity;
	}

	public void setQuantity(Float quantity) {
		this.quantity = quantity;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Integer getIsAttach() {
		return isAttach;
	}

	public void setIsAttach(Integer isAttach) {
		this.isAttach = isAttach;
	}

	public MultipartFile[] getFiles() {
		return files;
	}

	public void setFiles(MultipartFile[] files) {
		this.files = files;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}
}
