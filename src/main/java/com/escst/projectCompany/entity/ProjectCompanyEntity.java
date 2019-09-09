package com.escst.projectCompany.entity;

import com.escst.commons.entity.BaseEntity;

/**
 * @author dwj
 * @desc
 * @date 9:42 2017/4/11
 */
public class ProjectCompanyEntity extends BaseEntity{

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 1746133556453850879L;
	private String id;
    /**工地ID**/
    private String constructionId;
    /**分包公司名称**/
    private String name;
    /**公司类型 1:专业工程分包公司;2:劳务分包公司;3:租赁公司;4:建设单位;5:施工单位;6:监理单位;**/
    private Integer type;

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


    public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

}
