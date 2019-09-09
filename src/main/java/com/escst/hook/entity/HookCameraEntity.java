package com.escst.hook.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @author dwj
 * @desc
 * @date 9:41 22/10/2018
 */
public class HookCameraEntity extends BaseEntity {

    /**
     *@Fileds id
     **/
    private String id;
    /**
     *@Fileds name 摄像头名称
     **/
    private String name;
    /**
     *@Fileds constructionId 工地Id
     **/
    private String constructionId;
    /**
     *@Fileds constructionName 工地名称
     **/
    private String constructionName;
    /**
     *@Fileds equipmentId 设备Id
     **/
    private String equipmentId;
    private String equipmentName;
    /**
     *@Fileds supplier 品牌（1:海康; 2:宇视）
     **/
    private int supplier;
    /**
     *@Fileds flag 标识.1:可云台控制;0:不能云台控制
     **/
    private int flag;
    /**
     *@Fileds channelNo 通道号
     **/
    private int channelNo;
    /**
     *@Fileds status 状态.1:在线;0:离线
     **/
    private int status;

    private String hikXml;
    
    private String hikSysCode;
    
    
    public String getEquipmentName() {
		return equipmentName;
	}

	public void setEquipmentName(String equipmentName) {
		this.equipmentName = equipmentName;
	}

	public String getHikXml() {
		return hikXml;
	}

	public String getHikSysCode() {
		return hikSysCode;
	}

	public void setHikXml(String hikXml) {
		this.hikXml = hikXml;
	}

	public void setHikSysCode(String hikSysCode) {
		this.hikSysCode = hikSysCode;
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

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public int getSupplier() {
        return supplier;
    }

    public void setSupplier(int supplier) {
        this.supplier = supplier;
    }

    public int getFlag() {
        return flag;
    }

    public void setFlag(int flag) {
        this.flag = flag;
    }

    public int getChannelNo() {
        return channelNo;
    }

    public void setChannelNo(int channelNo) {
        this.channelNo = channelNo;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
