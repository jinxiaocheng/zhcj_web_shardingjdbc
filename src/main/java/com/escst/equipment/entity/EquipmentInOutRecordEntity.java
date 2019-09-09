package com.escst.equipment.entity;

import com.escst.commons.entity.BaseEntity;
import com.escst.file.vo.FilePathVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 14:28 2017/3/18
 */
public class EquipmentInOutRecordEntity extends BaseEntity {

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -5323412923532511417L;

	private  String id;

    private String constructionId;

    private String equipmentId;

    private String constructionName;
    private  String personName;
    private String number;

    private String areaId;
    private String model;
    private String manufacturer;
    private String leasingCompany;

    private  String userId;
    private int type;

    private String personId;

    private String remark;

    private Date  createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
    private int isAttach;
    private  String name;
    /**附件*/
    private MultipartFile[] File;

    /**图片附件路径集合**/
    private List<FilePathVO> picList;

    private String belongArea;

    private int status;


    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
    }

    public MultipartFile[] getFile() {
        return File;
    }

    public void setFile(MultipartFile[] file) {
        File = file;
    }

    public int getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(int isAttach) {
        this.isAttach = isAttach;
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

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
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

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FilePathVO> getPicList() {
        return picList;
    }

    public void setPicList(List<FilePathVO> picList) {
        this.picList = picList;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

}
