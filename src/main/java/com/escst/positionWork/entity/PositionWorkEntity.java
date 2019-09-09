package com.escst.positionWork.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @desc 
 * @author niejing
 * @date 2018年4月25日 下午4:03:27
 */
public class PositionWorkEntity extends BaseEntity {

	/**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 4188767695359199399L;
	
	private String id;
	private String constructionId;
	private String constructionName;
	private String name;
	private Integer type;
	
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
	public String getConstructionName() {
		return constructionName;
	}
	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getType() {
		return type;
	}
	public void setType(Integer type) {
		this.type = type;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((constructionId == null) ? 0 : constructionId.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		PositionWorkEntity other = (PositionWorkEntity) obj;
		if (constructionId == null) {
			if (other.constructionId != null)
				return false;
		} else if (!constructionId.equals(other.constructionId))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}
	
	
}
