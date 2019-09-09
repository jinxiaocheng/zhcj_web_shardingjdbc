package com.escst.file.entity;

import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

/**
 * @author dwj
 * @desc
 * @date 13:43 2018/3/6
 */
public class FileEntity implements Serializable {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 7770518099064451645L;

	private String id;
	/** 文件名 */
	private String name;
	/** 文件路径 */
	private String path;
	/** 文件类型 */
	private String type;
	/** 文件大小 */
	private String size;
	/** 业务id **/
	private String businessId;
	/** 创建时间 **/
	private String createTime;
	/** 创建人 **/
	private String createBy;
	/** 更新时间 **/
	private String updateTime;
	/** 更新人 **/
	private String updateBy;

	private MultipartFile multipartFile;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public String getSize() {
		return size;
	}

	public void setSize(String size) {
		this.size = size;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getCreateBy() {
		return createBy;
	}

	public void setCreateBy(String createBy) {
		this.createBy = createBy;
	}

	public String getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(String updateTime) {
		this.updateTime = updateTime;
	}

	public String getUpdateBy() {
		return updateBy;
	}

	public void setUpdateBy(String updateBy) {
		this.updateBy = updateBy;
	}

	public MultipartFile getMultipartFile() {
		return multipartFile;
	}

	public void setMultipartFile(MultipartFile multipartFile) {
		this.multipartFile = multipartFile;
	}

	public String getBusinessId() {
		return businessId;
	}

	public void setBusinessId(String businessId) {
		this.businessId = businessId;
	}
}
