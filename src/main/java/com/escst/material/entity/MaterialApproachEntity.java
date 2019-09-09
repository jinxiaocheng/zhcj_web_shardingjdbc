package com.escst.material.entity;

import com.escst.commons.entity.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @desc 材料进场实体类
 * @Author wy
 * @createDate 2017/3/22 13:25
 */
public class MaterialApproachEntity extends BaseEntity {

    /**
     * @Fields serialVersionUID
     */
    private static final long serialVersionUID = 5979674869767967592L;
    private String id;
    private String constructionId;
    /**
     * 进场时间
     */
    private String approachDate;
    /**
     * 材料名称
     */
    private String name;
    /**
     * 进场数量
     */
    private Float quantity;
    /**
     * 规格型号
     */
    private String model;
    /**
     * @Fields modelId 规格型号ID
     */
    private String modelId;
    /**
     * 计量单位
     */
    private String measurementUnit;
    /**
     * 生产厂家
     */
    private String manufacturer;
    /**
     * 使用部位
     */
    private String usePosition;
    /**
     * 可用数量
     */
    private Float availableQuantity;
    /**
     * 现场验证结果
     */
    private String verificationResults;
    /**
     * 备注
     */
    private String remark;
    /**
     * 是否有附件：1-有附件，0-没附件
     */
    private int isAttach;
    /**
     * 材料名称拼音首字母
     */
    private String code;
    /**
     * 附件
     */
    private MultipartFile[] files;
    // 材料ID
    private String materialId;
    // 材料预算Id
    private String materialBudgetId;
    // 报告编号
    private String reportNum;
    //材料品种
    private String breed;

    /**
     * 车牌号
     */
    private String carNumber;

    private String constructionName;

    private String territoryName;

    private List<String> base64ImageList;

    private String filePathList;

    private String[] pathArray;


    /**
     * 地磅数据Id
     */
    private String weighbridgeId;

    /**
     * 地磅前后照片地址
     *
     * @return
     */
    private String frontImagePath;
    private String laterImagePath;


    private Float amount;
    private Float totalAmount;
    private Integer siteResult;
    private String samplingDate;
    private Integer experimentResult;

    private String orderNo;

    public Float getAmount() {
        return amount;
    }

    public void setAmount(Float amount) {
        this.amount = amount;
    }

    public Float getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(Float totalAmount) {
        this.totalAmount = totalAmount;
    }

    public Integer getSiteResult() {
        return siteResult;
    }

    public void setSiteResult(Integer siteResult) {
        this.siteResult = siteResult;
    }

    public String getSamplingDate() {
        return samplingDate;
    }

    public void setSamplingDate(String samplingDate) {
        this.samplingDate = samplingDate;
    }

    public Integer getExperimentResult() {
        return experimentResult;
    }

    public void setExperimentResult(Integer experimentResult) {
        this.experimentResult = experimentResult;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getConstructionId() {
        return constructionId;
    }

    public void setConstructionId(String constructionId) {
        this.constructionId = constructionId;
    }

    public String getApproachDate() {
        return approachDate;
    }

    public void setApproachDate(String approachDate) {
        this.approachDate = approachDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Float getQuantity() {
        return quantity;
    }

    public void setQuantity(Float quantity) {
        this.quantity = quantity;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getMeasurementUnit() {
        return measurementUnit;
    }

    public void setMeasurementUnit(String measurementUnit) {
        this.measurementUnit = measurementUnit;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public String getUsePosition() {
        return usePosition;
    }

    public void setUsePosition(String usePosition) {
        this.usePosition = usePosition;
    }

    public Float getAvailableQuantity() {
        return availableQuantity;
    }

    public void setAvailableQuantity(Float availableQuantity) {
        this.availableQuantity = availableQuantity;
    }

    public String getVerificationResults() {
        return verificationResults;
    }

    public void setVerificationResults(String verificationResults) {
        this.verificationResults = verificationResults;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public int getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(int isAttach) {
        this.isAttach = isAttach;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
    }


    public String getMaterialId() {
        return materialId;
    }

    public void setMaterialId(String materialId) {
        this.materialId = materialId;
    }

    public String getMaterialBudgetId() {
        return materialBudgetId;
    }

    public void setMaterialBudgetId(String materialBudgetId) {
        this.materialBudgetId = materialBudgetId;
    }

    public String getReportNum() {
        return reportNum;
    }

    public void setReportNum(String reportNum) {
        this.reportNum = reportNum;
    }

    public String getModelId() {
        return modelId;
    }

    public void setModelId(String modelId) {
        this.modelId = modelId;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }

    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getTerritoryName() {
        return territoryName;
    }

    public void setTerritoryName(String territoryName) {
        this.territoryName = territoryName;
    }

    public List<String> getBase64ImageList() {
        return base64ImageList;
    }

    public void setBase64ImageList(List<String> base64ImageList) {
        this.base64ImageList = base64ImageList;
    }

    public String[] getPathArray() {
        return pathArray;
    }

    public void setPathArray(String[] pathArray) {
        this.pathArray = pathArray;
    }

    public String getWeighbridgeId() {
        return weighbridgeId;
    }

    public void setWeighbridgeId(String weighbridgeId) {
        this.weighbridgeId = weighbridgeId;
    }

    public String getFrontImagePath() {
        return frontImagePath;
    }

    public void setFrontImagePath(String frontImagePath) {
        this.frontImagePath = frontImagePath;
    }

    public String getLaterImagePath() {
        return laterImagePath;
    }

    public void setLaterImagePath(String laterImagePath) {
        this.laterImagePath = laterImagePath;
    }

    public String getFilePathList() {
        return filePathList;
    }

    public void setFilePathList(String filePathList) {
        this.filePathList = filePathList;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }
}
