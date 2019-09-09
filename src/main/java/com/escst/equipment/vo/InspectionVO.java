package com.escst.equipment.vo;

import com.escst.commons.vo.BaseVO;
import com.escst.file.vo.FilePathVO;
import com.escst.task.entity.ScheduledPlanEntity;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhouwei
 * @desc 设备巡查
 * @date 2017年8月23日 上午11:03:16
 */
public class InspectionVO extends BaseVO implements Cloneable {

    /**
     * @Fields id 巡检ID
     */
    private String id;

    /**
     * @Fields currentLocation 巡检地址
     */
    private String currentLocation;

    /**
     * @Fields equipmentName 设备名称
     */
    private String equipmentName;

    /**
     * @Fields equipmentNumber 设备编号
     */
    private String equipmentNumber;

    /**
     * @Fields equipmentModel 设备型号
     */
    private String equipmentModel;

    /**
     * @Fields manufacturer 设备生产厂家
     */
    private String manufacturer;

    /**
     * @Fields leasingCompany 设备租赁公司
     */
    private String leasingCompany;

    /**
     * @Fields results 巡检结果.1:合格;2:不合格
     */
    private int results;

    /**
     * @Fields remark 备注
     */
    private String remark;

    /**
     * @Fields filePathList 附件链接地址
     */
    private List<FilePathVO> filePathList;

    /**
     * @Fields constructionName 工地名称
     */
    private String constructionName;

    /**
     * @Fields areaName 工地所属区域
     */
    private String areaName;

    /**
     * @Fields isAttach 是否有附件
     */
    private int isAttach;

    // 检查单id
    private String inspectionId;

    private String iteamId;

    // 整改状态 1:待整改;2:待检查;3:检查不通过;4:已整改
    private int correctiveStatus;

    private String checkName;

    private String dutyName;

    private String date;

    private List<InspectionVO> data;

    private String projectStructureName;

    // 检查项树
    private List<InspectionVO> itemData;

    // 检查部位树
    private List<ScheduledPlanEntity> projectStructureData;

    private String constructionId;

    private String parentId;

    private Integer type;

    private Integer score;

    private String resultsId;

    private String items;

    private Integer isPush;

    private Integer operation;

    private String correctiveCompletionDate;

    private String inspectionDate;

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getEquipmentModel() {
        return equipmentModel;
    }

    public void setEquipmentModel(String equipmentModel) {
        this.equipmentModel = equipmentModel;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getLeasingCompany() {
        return leasingCompany;
    }

    public void setLeasingCompany(String leasingCompany) {
        this.leasingCompany = leasingCompany;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public List<FilePathVO> getFilePathList() {
        return filePathList;
    }

    public void setFilePathList(List<FilePathVO> filePathList) {
        this.filePathList = filePathList;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getAreaName() {
        return areaName;
    }

    public void setAreaName(String areaName) {
        this.areaName = areaName;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public int getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(int isAttach) {
        this.isAttach = isAttach;
    }

    public String getInspectionId() {
        return inspectionId;
    }

    public void setInspectionId(String inspectionId) {
        this.inspectionId = inspectionId;
    }

    public String getIteamId() {
        return iteamId;
    }

    public void setIteamId(String iteamId) {
        this.iteamId = iteamId;
    }

    public List<InspectionVO> getData() {
        return data;
    }

    public void setData(List<InspectionVO> data) {
        this.data = data;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Override
    public String getId() {
        return id;
    }

    @Override
    public void setId(String id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public String getResultsId() {
        return resultsId;
    }

    public void setResultsId(String resultsId) {
        this.resultsId = resultsId;
    }

    public List<InspectionVO> getItemData() {
        return itemData;
    }

    public void setItemData(List<InspectionVO> itemData) {
        this.itemData = itemData;
    }

    public List<ScheduledPlanEntity> getProjectStructureData() {
        return projectStructureData;
    }

    public void setProjectStructureData(List<ScheduledPlanEntity> projectStructureData) {
        this.projectStructureData = projectStructureData;
    }

    public int getCorrectiveStatus() {
        return correctiveStatus;
    }

    public void setCorrectiveStatus(int correctiveStatus) {
        this.correctiveStatus = correctiveStatus;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getDutyName() {
        return dutyName;
    }

    public void setDutyName(String dutyName) {
        this.dutyName = dutyName;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getProjectStructureName() {
        return projectStructureName;
    }

    public void setProjectStructureName(String projectStructureName) {
        this.projectStructureName = projectStructureName;
    }

    public String getItems() {
        return items;
    }

    public void setItems(String items) {
        this.items = items;
    }

    public Integer getIsPush() {
        return isPush;
    }

    public void setIsPush(Integer isPush) {
        this.isPush = isPush;
    }

    public Integer getOperation() {
        return operation;
    }

    public void setOperation(Integer operation) {
        this.operation = operation;
    }

    public String getCorrectiveCompletionDate() {
        return correctiveCompletionDate;
    }

    public void setCorrectiveCompletionDate(String correctiveCompletionDate) {
        this.correctiveCompletionDate = correctiveCompletionDate;
    }

    public String getInspectionDate() {
        return inspectionDate;
    }

    public void setInspectionDate(String inspectionDate) {
        this.inspectionDate = inspectionDate;
    }

    @Override
    public Object clone() throws CloneNotSupportedException {
        InspectionVO obj = (InspectionVO) super.clone();
        List<InspectionVO> structureList = new ArrayList<InspectionVO>();
        List<InspectionVO> list = getData();
        if (list != null) {
            for (InspectionVO bean : list) {
                InspectionVO b = (InspectionVO) bean.clone();
                structureList.add(b);
            }
            obj.setData(structureList);
        }
        return obj;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof InspectionVO) {
            InspectionVO t = (InspectionVO) obj;
            return /*this.name.equals(t.name) &&*/ this.id.equals(t.id);
        }
        return super.equals(obj);
    }


}
