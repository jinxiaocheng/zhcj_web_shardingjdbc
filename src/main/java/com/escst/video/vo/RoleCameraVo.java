package com.escst.video.vo;

import com.escst.commons.tree.TreeEntity;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 14:21 2018/5/2
 */
public class RoleCameraVo {

    /**
     * @Fileds id 工地Id
     */
    private String id;

    /**
     *@Fileds constructionName 工地名
     */
    private String name;

    /**
     *@Fileds list 通道树形集合
     */
    private List<TreeEntity> children;


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

    public List<TreeEntity> getChildren() {
        return children;
    }

    public void setChildren(List<TreeEntity> children) {
        this.children = children;
    }
}
