package com.escst.inspection.vo;

import java.io.Serializable;

import com.escst.commons.vo.BaseVO;


/**
 * @author jincheng
 * @desc 检查部位（工程结构）vo
 * @date 2018/1/19 9:47
 */
public class InspectionProjectStructureVO extends BaseVO implements Serializable {

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 2491436492438916238L;

	// 检查单id
    private String inspectionId;

    // 检查部位id
    private String projectStructureId;

    // 检查部位名称
    private String projectStructureName;

    // 工地id
    private String constructionId;

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getProjectStructureId() {
        return projectStructureId;
    }

    public void setProjectStructureId(String projectStructureId) {
        this.projectStructureId = projectStructureId;
    }

    public String getProjectStructureName() {
        return projectStructureName;
    }

    public void setProjectStructureName(String projectStructureName) {
        this.projectStructureName = projectStructureName;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }
}
