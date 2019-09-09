package com.escst.material.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc
 * @author niejing
 * @date 2017年9月26日 下午2:27:15
 */
public class MaterialModelEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = -4439074075143721961L;

	private String id;

	private String materialId;

	// 型号名称
	private String name;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMaterialId() {
		return materialId;
	}

	public void setMaterialId(String materialId) {
		this.materialId = materialId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
