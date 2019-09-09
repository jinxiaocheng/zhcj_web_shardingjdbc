package com.escst.hook.bean;

import com.escst.hook.entity.HookCameraEntity;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 9:39 22/10/2018
 */
public class HookCameraBean {

    /**
     *@Fileds equipmentId 设备Id
     **/
    private String equipmentId;
    /**
     *@Fileds equipmentName 设备名
     **/
    private String equipmentName;
    /**
     *@Fileds list 通道列表集合
     **/
    private List<HookCameraEntity> list;

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public List<HookCameraEntity> getList() {
        return list;
    }

    public void setList(List<HookCameraEntity> list) {
        this.list = list;
    }
}
