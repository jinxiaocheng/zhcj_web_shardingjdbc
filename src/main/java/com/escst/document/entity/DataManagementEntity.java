package com.escst.document.entity;

import com.escst.commons.entity.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @author caozx
 * @desc  资料信息
 * @date 2017/3/9 11:15
 */
public class DataManagementEntity extends BaseEntity {
    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 3787202371869785288L;

	private String id;

    /**工地ID**/
    private String constructionId;

    /**资料名称**/
    private String name;

    /**资料类型（1:合同;2:安全文明;3:工程监理;4:工作总结;5:质量）**/
    private int type;

    private String typeId;

    /**状态1:有效;0:无效**/
    private int status;

    /**附件,多个附件用数组做参数**/
    private MultipartFile[] files;

    /**文件路径**/
    private List<String> picList;

    private String remark;

    private String belongArea;

    private String createBy;

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

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

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }

    public List<String> getPicList() {
        return picList;
    }

    public void setPicList(List<String> picList) {
        this.picList = picList;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
