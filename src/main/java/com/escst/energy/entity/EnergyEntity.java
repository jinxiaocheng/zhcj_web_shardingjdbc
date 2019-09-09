package com.escst.energy.entity;

/**
 * @author dwj
 * @desc
 * @date 9:55 2017/9/13
 */
public class EnergyEntity {

    private String constructionId;
    //日周月类型
    private int type;
    //水电类型
    private int equipmentType;

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public int getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(int equipmentType) {
        this.equipmentType = equipmentType;
    }
}
