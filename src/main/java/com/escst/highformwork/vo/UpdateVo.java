package com.escst.highformwork.vo;


import java.util.Date;
import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 15:44 27/7/2018
 */
public class UpdateVo {

    /**
     *@Fileds status 状态
     **/
    private int status;

    /**
     *@Fileds upateTime 修改时间
     **/
    private Date updateTime;

    /**
     *@Fileds updateBy 修改人
     **/
    private String updateBy;
    /**
     *@Fileds ids 采集器Id
     **/
    private List<String> ids;


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    public String getUpdateBy() {
        return updateBy;
    }

    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }

    public List<String> getIds() {
        return ids;
    }

    public void setIds(List<String> ids) {
        this.ids = ids;
    }
}
