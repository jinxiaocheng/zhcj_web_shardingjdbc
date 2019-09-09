package com.escst.hook.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @author dwj
 * @desc
 * @date 9:18 19/10/2018
 */
public class HookEquipmentEntity extends BaseEntity {

    /**
     *@Fileds id
     **/
    private String id;
    /**
     *@Fileds name 设备名
     **/
    private String name;
    /**
     *@Fileds constructionId 工地Id
     **/
    private String constructionId;
    /**
     *@Fileds constructionName 工地名
     **/
    private String constructionName;
    /**
     *@Fileds number 编号
     **/
    private String number;
    /**
     *@Fileds model 型号
     **/
    private String model;
    /**
     *@Fileds manufacturer 生产厂家
     **/
    private String manufacturer;
    /**
     *@Fileds remark 备注
     **/
    private String remark;
    /**
     *@Fileds status 是否删除 1否2是
     **/
    private int status;

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

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
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

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
