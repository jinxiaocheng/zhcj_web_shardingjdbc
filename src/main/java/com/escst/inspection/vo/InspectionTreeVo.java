package com.escst.inspection.vo;

import java.util.List;

/**
 * @author jincheng
 * @desc 检查单相关
 * @date 2018-8-17 14:49
 */
public class InspectionTreeVo {

    private String id;

    private String name;

    private List<InspectionTreeVo> data;


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

    public List<InspectionTreeVo> getData() {
        return data;
    }

    public void setData(List<InspectionTreeVo> data) {
        this.data = data;
    }
}
