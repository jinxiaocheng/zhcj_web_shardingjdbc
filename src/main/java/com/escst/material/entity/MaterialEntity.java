package com.escst.material.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc 材料类
 * @author niejing
 * @date 2017年8月22日 上午9:33:26
 */
public class MaterialEntity extends BaseEntity{

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 3340278750627688232L;
	//材料型号id
	private String id;
	//材料名
	private String name;
	
	//材料名拼音
	private String code;
	
	//规格型号
	private String model;
	
	//计量单位
	private String unit;

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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getUnit() {
		return unit;
	}

	public void setUnit(String unit) {
		this.unit = unit;
	}
	
}
