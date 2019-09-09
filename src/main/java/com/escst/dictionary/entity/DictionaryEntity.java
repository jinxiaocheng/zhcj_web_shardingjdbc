package com.escst.dictionary.entity;

import java.io.Serializable;

/**
 * @author caozx
 * @desc
 * @date 2017/3/15 13:25
 */
public class DictionaryEntity implements Serializable {

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 1370101838447252226L;
	private String id;
    /**类型**/
    private String type;
    /**名称**/
    private String name;
    /**值**/
    private int value;
    /**描述**/
    private String description;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
