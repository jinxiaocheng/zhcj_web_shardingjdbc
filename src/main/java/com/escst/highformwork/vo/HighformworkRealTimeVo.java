package com.escst.highformwork.vo;


import com.fasterxml.jackson.databind.annotation.JsonSerialize;

/**
 * @author niejing
 * @desc
 * @date 2018年7月17日 下午4:24:50
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
public class HighformworkRealTimeVo {

    private String constructionId;
    private String constructionName;
    private String flowId;
    private String flowName;
    // 1:正常；2：预警；3：报警；4、控制
    private Integer type;
    private String number;
    private double xAngle;
    private double yAngle;
    private double kpa;
    private double displace;
    private double temperature;
    private double electric;

    private String equipmentId;
    private String equipmentName;

    // 1:正常；2：预警；3：报警；4、控制
    private int xType;
    private int yType;
    private int kpaType;
    private int displaceType;
    private int temperatureType;
    private int electricType;

    // 预警
    private int warning;
    // 报警
    private int alarm;
    // 控制
    private int control;

    private String acquisitionTime;

    /**
     * 页号,默认显示第一页
     **/
    private int pageNum;
    /**
     * 默认每页显示10条数据
     **/
    private int pageSize;

    /**
     * 从第几条开始
     */
    private int offset;

    private int page;

    private int rowNum;

    private String startTime;
    private String endTime;

    private String fileId;

    private String path;

    private String createTime;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public String getFlowId() {
        return flowId;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFlowName() {
        return flowName;
    }

    public Integer getType() {
        return type;
    }

    public String getNumber() {
        return number;
    }

    public double getxAngle() {
        return xAngle;
    }

    public double getyAngle() {
        return yAngle;
    }

    public double getKpa() {
        return kpa;
    }

    public double getDisplace() {
        return displace;
    }

    public double getTemperature() {
        return temperature;
    }

    public double getElectric() {
        return electric;
    }

    public String getEquipmentId() {
        return equipmentId;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public int getxType() {
        return xType;
    }

    public int getyType() {
        return yType;
    }

    public int getKpaType() {
        return kpaType;
    }

    public int getDisplaceType() {
        return displaceType;
    }

    public int getTemperatureType() {
        return temperatureType;
    }

    public int getElectricType() {
        return electricType;
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

    public String getAcquisitionTime() {
        return acquisitionTime;
    }

    public int getPageNum() {
        return pageNum;
    }

    public int getPageSize() {
        return pageSize;
    }

    public int getOffset() {
        return offset;
    }

    public String getStartTime() {
        return startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
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

    public void setType(Integer type) {
        this.type = type;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public void setxAngle(double xAngle) {
        this.xAngle = xAngle;
    }

    public void setyAngle(double yAngle) {
        this.yAngle = yAngle;
    }

    public void setKpa(double kpa) {
        this.kpa = kpa;
    }

    public void setDisplace(double displace) {
        this.displace = displace;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public void setElectric(double electric) {
        this.electric = electric;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public void setxType(int xType) {
        this.xType = xType;
    }

    public void setyType(int yType) {
        this.yType = yType;
    }

    public void setKpaType(int kpaType) {
        this.kpaType = kpaType;
    }

    public void setDisplaceType(int displaceType) {
        this.displaceType = displaceType;
    }

    public void setTemperatureType(int temperatureType) {
        this.temperatureType = temperatureType;
    }

    public void setElectricType(int electricType) {
        this.electricType = electricType;
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

    public void setAcquisitionTime(String acquisitionTime) {
        this.acquisitionTime = acquisitionTime;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public void setOffset(int offset) {
        this.offset = offset;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRowNum() {
        return rowNum;
    }

    public void setRowNum(int rowNum) {
        this.rowNum = rowNum;
    }

    @Override
	public String toString() {
		return "HighformworkRealTimeVo [constructionId=" + constructionId + ", constructionName=" + constructionName + ", flowId=" + flowId + ", flowName="
				+ flowName + ", type=" + type + ", number=" + number + ", xAngle=" + xAngle + ", yAngle=" + yAngle + ", kpa=" + kpa + ", displace=" + displace
				+ ", temperature=" + temperature + ", electric=" + electric + ", equipmentId=" + equipmentId + ", equipmentName=" + equipmentName + ", xType="
				+ xType + ", yType=" + yType + ", kpaType=" + kpaType + ", displaceType=" + displaceType + ", temperatureType=" + temperatureType
				+ ", electricType=" + electricType + ", warning=" + warning + ", alarm=" + alarm + ", control=" + control + ", acquisitionTime="
				+ acquisitionTime + ", pageNum=" + pageNum + ", pageSize=" + pageSize + ", offset=" + offset + ", startTime=" + startTime + ", endTime="
				+ endTime + ", fileId=" + fileId + ", path=" + path + "]";
	}



}
