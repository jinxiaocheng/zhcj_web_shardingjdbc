package com.escst.territory.entity;

import java.io.Serializable;

/**
 * @author caozx
 * @desc
 * @date 2017/2/16 17:45
 */
public class TerritoryEntity implements Serializable {
    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -6661774152027859286L;
	private String id;
    /**代码**/
    private String code;
    /**级别**/
    private int level;
    /**名称**/
    private String name;
    /**拼音**/
    private String pinyin;
    /**x坐标**/
    private float xWgs84;
    /**y坐标**/
    private float yWgs84;
    /**父节点ID**/
    private String parentId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPinyin() {
        return pinyin;
    }

    public void setPinyin(String pinyin) {
        this.pinyin = pinyin;
    }

    public float getxWgs84() {
        return xWgs84;
    }

    public void setxWgs84(float xWgs84) {
        this.xWgs84 = xWgs84;
    }

    public float getyWgs84() {
        return yWgs84;
    }

    public void setyWgs84(float yWgs84) {
        this.yWgs84 = yWgs84;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }
}
