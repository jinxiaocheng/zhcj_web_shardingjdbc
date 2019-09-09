package com.escst.highformwork.bean;

import com.escst.highformwork.vo.CollectorVo;

import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 17:37 16/7/2018
 */
public class CollectorBean {

    /**
     *@Fileds constructionId 工地Id
     **/
    private String constructionId;

    /**
     *@Fileds constructionName 工地名
     **/
    private String constructionName;


    /**
     * @Fileds createBy 操作人
     * */
    private String createBy;

    /**
     *@Fileds  entityList 采集器集合
     **/
    private List<CollectorVo> vos;

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

    public List<CollectorVo> getVos() {
        return vos;
    }

    public void setVos(List<CollectorVo> vos) {
        this.vos = vos;
    }

    public String getCreateBy() {
        return createBy;
    }

    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }
}
