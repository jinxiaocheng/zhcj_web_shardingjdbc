package com.escst.territory.vo;

import java.util.List;

/**
 * @author caozx
 * @desc
 * @date 2017/8/18 15:34
 */
public class AreaConstructionVO {

    /**市ID**/
    private String cityId;

    /**区ID**/
    private String areaId;

    /**区名称**/
    private String areaName;

    /**区下面的工地数据**/
    private List<ConstructionVO> data;

    public String getCityId() {
        return cityId;
    }

    public void setCityId(String cityId) {
        this.cityId = cityId;
    }

    public String getAreaId() {
        return areaId;
    }

    public void setAreaId(String areaId) {
        this.areaId = areaId;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public List<ConstructionVO> getData() {
        return data;
    }

    public void setData(List<ConstructionVO> data) {
        this.data = data;
    }
}
