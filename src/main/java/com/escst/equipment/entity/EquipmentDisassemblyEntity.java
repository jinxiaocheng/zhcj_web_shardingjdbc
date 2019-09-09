package com.escst.equipment.entity;

import com.escst.commons.entity.BaseEntity;
import com.escst.file.vo.FilePathVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.List;

/**
 * @author dwj
 * @desc
 * @date 14:38 2017/3/18
 */
public class EquipmentDisassemblyEntity  extends BaseEntity{

     /**
	 * @Fields serialVersionUID 
	 */
	private static final long serialVersionUID = -1666716550924997323L;
	private String id;
    private int type;
     private String constructionId;
     private String equipmentId;
     private String disassemblyDate;
     private String remark;
    private String createBy;
    private int isAttach;
    private String name;
    private String constructionName;
    private  String personName;
    private String number;
    /**图片附件路径集合**/
    private List<FilePathVO> picList;
    /**附件*/
    private MultipartFile[] Files;
    private String belongArea;
    private  String userId;

    public String getBelongArea() {
        return belongArea;
    }

    public void setBelongArea(String belongArea) {
        this.belongArea = belongArea;
    }

    public MultipartFile[] getFiles() {
        return Files;
    }

    public void setFiles(MultipartFile[] files) {
        Files = files;
    }

    public int getIsAttach() {
        return isAttach;
    }

    public void setIsAttach(int isAttach) {
        this.isAttach = isAttach;
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

    public String getDisassemblyDate() {
        return disassemblyDate;
    }

    public void setDisassemblyDate(String disassemblyDate) {
        this.disassemblyDate = disassemblyDate;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }




    public String getConstructionName() {
        return constructionName;
    }

    public void setConstructionName(String constructionName) {
        this.constructionName = constructionName;
    }

    public String getPersonName() {
        return personName;
    }

    public void setPersonName(String personName) {
        this.personName = personName;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public List<FilePathVO> getPicList() {
        return picList;
    }

    public void setPicList(List<FilePathVO> picList) {
        this.picList = picList;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    @Override
    public String getUserId() {
        return userId;
    }

    @Override
    public void setUserId(String userId) {
        this.userId = userId;
    }
}
