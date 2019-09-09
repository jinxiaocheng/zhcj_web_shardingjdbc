package com.escst.highformwork.vo;

/**
 * @desc 
 * @author niejing
 * @date 2018年7月18日 下午2:37:50
 */
public class HighformworkFlowVo {
	//工地名称
	private String constructionName;
	private String constructionId;
	private String flowId;
	//标段名称
	private String flowName;
	//预警
	private int warning;
	//报警
	private int alarm;
	//控制
	private int control;

	private String startTime;
	private String endTime;

	private String createTime;
	

	@Override
	public String toString() {
		return "HighformworkFlowVo [constructionName=" + constructionName + ", flowId=" + flowId + ", flowName=" + flowName + ", warning=" + warning
				+ ", alarm=" + alarm + ", control=" + control + ", startTime=" + startTime + ", endTime=" + endTime + "]";
	}

	public String getConstructionName() {
		return constructionName;
	}

	public String getFlowId() {
		return flowId;
	}

	public String getFlowName() {
		return flowName;
	}

	public int getWarning() {
		return warning;
	}

	public int getAlarm() {
		return alarm;
	}

	public int getControl() {
		return control;
	}

	public String getStartTime() {
		return startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public void setFlowId(String flowId) {
		this.flowId = flowId;
	}

	public void setFlowName(String flowName) {
		this.flowName = flowName;
	}

	public void setWarning(int warning) {
		this.warning = warning;
	}

	public void setAlarm(int alarm) {
		this.alarm = alarm;
	}

	public void setControl(int control) {
		this.control = control;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((flowId == null) ? 0 : flowId.hashCode());
		return result;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HighformworkFlowVo other = (HighformworkFlowVo) obj;
		if (flowId == null) {
			if (other.flowId != null)
				return false;
		} else if (!flowId.equals(other.flowId))
			return false;
		return true;
	}
	
	
}
