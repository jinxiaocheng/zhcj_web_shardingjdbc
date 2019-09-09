package com.escst.equipment.entity;

import com.escst.commons.entity.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author dwj
 * @desc
 * @date 16:13 2017/3/22
 */
public class EquipmentInspectionEntity extends BaseEntity{

    /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = 5155362091583580787L;

	private String id;

    private String constructionId;

    private String equipmentId;

    private String currentLocation;

    private String longitude;

    private String latitude;

    //巡检结果 1:合格;2:不合格
    private int results;

    private String remark;

    private Date createTime;

    private String createBy;

    private Date updateTime;

    private String updateBy;
    /**
     * 附件照片,有附件的时候才有
     **/
    private MultipartFile[] files;

    private int isAttach;

    public int getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(int isAttach) {
        this.isAttach = isAttach;
    }

    public MultipartFile[] getFiles() {
        return files;
    }

    public void setFiles(MultipartFile[] files) {
        this.files = files;
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

    public String getEquipmentId() {
        return equipmentId;
    }

    public void setEquipmentId(String equipmentId) {
        this.equipmentId = equipmentId;
    }

    public String getCurrentLocation() {
        return currentLocation;
    }

    public void setCurrentLocation(String currentLocation) {
        this.currentLocation = currentLocation;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public int getResults() {
        return results;
    }

    public void setResults(int results) {
        this.results = results;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    @Override
    public Date getCreateTime() {
        return createTime;
    }

    @Override
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Override
    public String getCreateBy() {
        return createBy;
    }

    @Override
    public void setCreateBy(String createBy) {
        this.createBy = createBy;
    }

    @Override
    public Date getUpdateTime() {
        return updateTime;
    }

    @Override
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String getUpdateBy() {
        return updateBy;
    }

    @Override
    public void setUpdateBy(String updateBy) {
        this.updateBy = updateBy;
    }
}
