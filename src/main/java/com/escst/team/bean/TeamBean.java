package com.escst.team.bean;

/**
 * @author caozx
 * @desc
 * @date 14:43 2017/10/31
 */
public class TeamBean {

    /**工地ID**/
    private String constructionId;

    /**分包公司ID**/
    private String projectCompanyId;

    /**添加的班组信息**/
//    private List<TeamWorkTypeVo> teamList;
    private String[] teamNames;
    
    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getProjectCompanyId() {
        return projectCompanyId;
    }

    public void setProjectCompanyId(String projectCompanyId) {
        this.projectCompanyId = projectCompanyId;
    }

	public String[] getTeamNames() {
		return teamNames;
	}

	public void setTeamNames(String[] teamNames) {
		this.teamNames = teamNames;
	}

}
