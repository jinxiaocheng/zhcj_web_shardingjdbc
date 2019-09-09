package com.escst.team.bean;

/**
 * @author caozx
 * @desc
 * @date 16:09 2017/10/31
 */
public class TeamWorkTypeVo {

    /**班组名称**/
    private String teamName;

    /**工种**/
    private int positionWorkType;

    public String getTeamName() {
        return teamName;
    }

    public void setTeamName(String teamName) {
        this.teamName = teamName;
    }

    public int getPositionWorkType() {
        return positionWorkType;
    }

    public void setPositionWorkType(int positionWorkType) {
        this.positionWorkType = positionWorkType;
    }
}
