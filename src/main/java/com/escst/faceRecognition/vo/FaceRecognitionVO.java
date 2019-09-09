package com.escst.faceRecognition.vo;

import com.escst.commons.entity.BaseEntity;
import com.escst.commons.vo.PageVo;
import com.escst.person.entity.PersonEntity;

import java.util.Date;
import java.util.List;

/**
 * @author jincheng
 * @desc 人脸识别相关
 * @date 2018/3/5 15:28
 */
public class FaceRecognitionVO extends BaseEntity {


    /**
     * 工地id
     */
    private String constructionId;

    /**
     * 设备id
     */
    private String equipmentId;


    /**
     * 图片的base64字符串
     */
    private String base64img;

    /**
     * 姓名（摄像头名称）
     */
    private String Name;

    /**
     * 性别
     */
    private String Sex;

    /**
     * 身份证号码
     */
    private String Card;

    /**
     * 手机号码
     */
    private String Phone;

    /**
     * 身份证号
     **/
    private String idCard;

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getSex() {
        return Sex;
    }

    public void setSex(String sex) {
        Sex = sex;
    }

    public String getIdCard() {
        return idCard;
    }

    public void setIdCard(String idCard) {
        this.idCard = idCard;
    }

    /**
     * 最低分值
     */
    private String score;

    /**
     * 返回的数量
     */
    private String count;


    /**
     * 人像模板编号
     */
    private String Id;

    /**
     * 库类别编号
     */
    private String ClassId;


    /**
     * 图片的URL
     */
    private String ImageUrl;

    /**
     * 摄像头ID(采集枪ID)
     */
    private String CameId;

    /**
     * 时间
     */
    private Date CreateTime;

    /**
     * 分组编号
     */
    private String UserGroupId;

    /**
     * 采集时间Date
     */
    private Date CaijiTime;

    /**
     * 采集时间
     */
    private String TimeStr;

    /**
     * 采集地点
     */
    private String CameName;

    /**
     * 主键编号
     */
    private String MGuid;

    /**
     * 图片的URL地址
     */
    private String TImage;

    /**
     * 页码
     */
    private String Start;

    /**
     * 每页数量
     */
    private String PageSize;

    /**
     * 开始时间
     */
    private String dtStart;

    /**
     * 结束时间
     */
    private String dtEnd;

    /**
     * 摄像头编号
     */
    private String CamID;

    /**
     * 总体数
     */
    private String RecordCount;

    /**
     * 图片地址
     */
    private String Url;

    /**
     * 摄像头名称
     */
    private String CamName;

    /**
     * 人员id
     * @return
     */
    private String guid;

    /**
     * 头像ID
     */
    private String headPortraitId;

    /**
     * 头像文件路径
     */
    private String headFilePath;

    /**
     * 开始日期
     */
    private String correctStartDate;

    /**
     * 结束日期
     */
    private String correctEndDate;

    /**
     * 摄像头x坐标
     * @return
     */
    private String x;

    /**
     * 摄像头y坐标
     * @return
     */
    private String y;

    /**
     * 身份证图片地址
     */
    private String cardImagePath;

    private List<FaceRecognitionVO> data;

    public List<FaceRecognitionVO> getData() {
        return data;
    }

    public void setData(List<FaceRecognitionVO> data) {
        this.data = data;
    }

    public String getGuid() {
        return guid;
    }

    public void setGuid(String guid) {
        this.guid = guid;
    }

    public String getBase64img() {
        return base64img;
    }

    public void setBase64img(String base64img) {
        this.base64img = base64img;
    }

    public String getCard() {
        return Card;
    }

    public void setCard(String card) {
        Card = card;
    }

    public String getPhone() {
        return Phone;
    }

    public void setPhone(String phone) {
        Phone = phone;
    }

    public String getScore() {
        return score;
    }

    public void setScore(String score) {
        this.score = score;
    }

    public String getImageUrl() {
        return ImageUrl;
    }

    public void setImageUrl(String imageUrl) {
        ImageUrl = imageUrl;
    }

    public String getCameId() {
        return CameId;
    }

    public void setCameId(String cameId) {
        CameId = cameId;
    }

    public void setCreateTime(Date createTime) {
        CreateTime = createTime;
    }

    public String getUserGroupId() {
        return UserGroupId;
    }

    public void setUserGroupId(String userGroupId) {
        UserGroupId = userGroupId;
    }

    public Date getCaijiTime() {
        return CaijiTime;
    }

    public void setCaijiTime(Date caijiTime) {
        CaijiTime = caijiTime;
    }

    public String getTimeStr() {
        return TimeStr;
    }

    public void setTimeStr(String timeStr) {
        TimeStr = timeStr;
    }

    public String getCameName() {
        return CameName;
    }

    public void setCameName(String cameName) {
        CameName = cameName;
    }

    public String getMGuid() {
        return MGuid;
    }

    public void setMGuid(String MGuid) {
        this.MGuid = MGuid;
    }

    public String getTImage() {
        return TImage;
    }

    public void setTImage(String TImage) {
        this.TImage = TImage;
    }

    public String getStart() {
        return Start;
    }

    public void setStart(String start) {
        Start = start;
    }

    public String getPageSize() {
        return PageSize;
    }

    public void setPageSize(String pageSize) {
        PageSize = pageSize;
    }

    public String getDtStart() {
        return dtStart;
    }

    public void setDtStart(String dtStart) {
        this.dtStart = dtStart;
    }

    public String getDtEnd() {
        return dtEnd;
    }

    public void setDtEnd(String dtEnd) {
        this.dtEnd = dtEnd;
    }

    public String getCamID() {
        return CamID;
    }

    public void setCamID(String camID) {
        CamID = camID;
    }

    public String getRecordCount() {
        return RecordCount;
    }

    public void setRecordCount(String recordCount) {
        RecordCount = recordCount;
    }

    public String getUrl() {
        return Url;
    }

    public void setUrl(String url) {
        Url = url;
    }

    public String getCamName() {
        return CamName;
    }

    public void setCamName(String camName) {
        CamName = camName;
    }

    public String getCount() {
        return count;
    }

    public void setCount(String count) {
        this.count = count;
    }

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public String getClassId() {
        return ClassId;
    }

    public void setClassId(String classId) {
        ClassId = classId;
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

    public Date getCreateTime() {
        return CreateTime;
    }

    public String getCorrectStartDate() {
        return correctStartDate;
    }

    public void setCorrectStartDate(String correctStartDate) {
        this.correctStartDate = correctStartDate;
    }

    public String getCorrectEndDate() {
        return correctEndDate;
    }

    public void setCorrectEndDate(String correctEndDate) {
        this.correctEndDate = correctEndDate;
    }

    public String getX() {
        return x;
    }

    public void setX(String x) {
        this.x = x;
    }

    public String getY() {
        return y;
    }

    public void setY(String y) {
        this.y = y;
    }

    public String getHeadPortraitId() {
        return headPortraitId;
    }

    public void setHeadPortraitId(String headPortraitId) {
        this.headPortraitId = headPortraitId;
    }

    public String getHeadFilePath() {
        return headFilePath;
    }

    public void setHeadFilePath(String headFilePath) {
        this.headFilePath = headFilePath;
    }

    public String getCardImagePath() {
        return cardImagePath;
    }

    public void setCardImagePath(String cardImagePath) {
        this.cardImagePath = cardImagePath;
    }
}
