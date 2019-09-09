package com.escst.inspection.vo;

import com.escst.inspection.bean.ItemsBean;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 14:36 2018/5/16
 */
public class ItemsCountVo {

    private String companyName;

    private String companyId;

    private List<ItemsBean> data;


    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public List<ItemsBean> getData() {
        return data;
    }

    public void setData(List<ItemsBean> data) {
        this.data = data;
    }
}
