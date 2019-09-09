package com.escst.equipment.entity;

import com.escst.commons.entity.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author dwj
 * @desc
 * @date 10:45 2017/3/13
 */
public class EquipmentManagerEntity extends BaseEntity {

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -7179918342065492943L;

    private String id;
	private String equipmentId;
    /**工地ID*/
    private String constructionId;
    /**设备名称*/
    private String name;

    private String belongArea;
    /**
     * 设备名称拼音
     **/
    private String code;
    /**设备编号*/
    private String number;
    /**型号*/
    private String model;
    /**生产厂家*/
    private String  manufacturer;

    private String leasingCompany;
    /**功能说明*/
    private String description;
    /**价格*/
    private String price;
    /**是否在工地.1:在工地;0:不在工地*/
    private int isOnConstruction;
    /**备注*/
    private String remark;
    /**创建时间*/
    private Date createTime;
    /**创建人*/
    private String createBy;
    /**更新时间*/
    private Date updateTime;
    /**更新人*/
    private String updateBy;
    /**附件*/
    private MultipartFile[] Files;

    private int status;

    /**
     * @Fields type 设备类型.来源EquipmentTypeEnum
     */
    private int type;
    private String personId;

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

    public MultipartFile[] getFiles() {
        return Files;
    }

    public void setFiles(MultipartFile[] files) {
        Files = files;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
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

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public int getIsOnConstruction() {
        return isOnConstruction;
    }

    public void setIsOnConstruction(int isOnConstruction) {
        this.isOnConstruction = isOnConstruction;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getLeasingCompany() {
        return leasingCompany;
    }

    public void setLeasingCompany(String leasingCompany) {
        this.leasingCompany = leasingCompany;
    }

    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}

