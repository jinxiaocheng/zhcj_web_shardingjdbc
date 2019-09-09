package com.escst.person.entity;

import org.springframework.web.multipart.MultipartFile;

import com.escst.commons.entity.BaseEntity;

import java.util.Date;

/**
 * @desc 人员管理实体类
 * @Author wy
 * @createDate 2017/3/9 11:22
 */
/**
 * @desc
 * @author zhouwei
 * @date 2017年10月23日 下午5:06:10
 */
public class PersonEntity extends BaseEntity {
	/**
	 * @Fields serialVersionUID
	 */
	private static final long serialVersionUID = 2337814605713460392L;
	/**
	 * 人员状态有效标识
	 **/
	public static final int STATUS_EFFECT = 1;
	/**
	 * 人员状态无效标识
	 **/
	public static final int STATUS_NO_EFFECT = 0;

	private String id;

	private String personId;

	/**
	 * 人员名称
	 **/
	private String name;
	/**
	 * 姓名拼音
	 **/
	private String code;
	/**
	 * 性别1:男;0:女
	 **/
	private Integer sex;
	/**
	 * 职称1:项目经理;2:施工员;3:预算员;4:材料员;5:质检员;6:安全员;7:办公室人员
	 **/
	private Integer title;
	/**
	 * 工种1:钳工;2:泥工;3:木工;4:水电工;5:钢筋工;6:混凝土工
	 **/
	private Integer workType;
	/**
	 * 工龄
	 **/
	private Integer workAge;
	/**
	 * 头像
	 **/
	private String headPortraitId;

	/**
	 * 身份证号
	 **/
	private String idCard;
	/**
	 * 资格证书照片
	 **/
	private String qualificationCertificateId;
	/**
	 * 身份证正面照片
	 **/
	private String idCardFrontId;
	/**
	 * 身份证反面照片
	 **/
	private String idCardBackId;
	/**
	 * 手机号码
	 **/
	private String mobile;
	/**
	 * 紧急联系人名称
	 **/
	private String iceContactName;
	/**
	 * 紧急联系人电话
	 **/
	private String iceContactMobile;
	/**
	 * 血型
	 **/
	private String bloodType;
	/**
	 * 状态
	 **/
	private Integer status;

	/**
	 * 工地ID
	 **/
	private String constructionId;
	private String constructionName;
	
	private String areaId;
	private String areaName;

	/**
	 * 班组ID
	 **/
	private String teamId;
	private String teamName;

	/**
	 * 头像文件
	 **/
	private MultipartFile headFile;
	
	/**
	 * @Fields headFilePath 头像文件的地址
	 */
	private String headFilePath;
	
	/**
	 * 身份证正面文件
	 **/
	private MultipartFile idCardFrontFile;
	private String idCardFrontFilePath;
	/**
	 * 身份证反面文件
	 **/
	private MultipartFile idCardBackFile;
	private String idCardBackFilePath;
	/**
	 * 资质证书文件
	 **/
	private MultipartFile qualificationCertificateFile;
	private String qualificationCertificateFilePath;

	/** 开始工龄（用于人员列表搜索） */
	private Integer startWorkAge;
	/** 截止工龄（用于人员列表搜索） */
	private Integer endWorkAge;

	// 所属公司ID
	private String projectCompanyId;
	private String projectCompanyName;

	/**
	 * @Fields positionWorkType 岗位/工种
	 */
	private Integer positionWorkType;
	private String positionWorkTypeName;

	/**
	 * @Fields isLeader 是否班组长
	 */
	private Integer isLeader;

	//证书ID
    private String certificateId;
	// 证书编号
	private String certificateNumber;
	// 证书类别
	private Integer certificateType;
	private String certificateTypeName;
	// 签发日期
	private String certificateIssueDate;
	// 有效期
	private String certificateValidDate;
	// 发证机关
	private String certificateIssueOrgans;
	
	/**
	 * @Fields constructionPersonId t_basic_construction_person表的主键
	 */
	private String constructionPersonId;

	private String cardNumber;

	private String positionWorkId;

	private String clientUserId;

	private int state;

	/**
	 * 银行卡号
	 * @return
	 */
	private String bankCard;

	private String jobNumber;

	private Date createTime;

	/**
	 *@Fileds attendanceType 打卡方式
	 **/
	private int attendanceType;
	/**
	 *@Fileds createDate
	 **/
	private String createDate;
	
	
	public String getPositionWorkId() {
		return positionWorkId;
	}

	public void setPositionWorkId(String positionWorkId) {
		this.positionWorkId = positionWorkId;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Integer getSex() {
		return sex;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getTitle() {
		return title;
	}

	public void setTitle(Integer title) {
		this.title = title;
	}

	public Integer getWorkType() {
		return workType;
	}

	public void setWorkType(Integer workType) {
		this.workType = workType;
	}

	public Integer getWorkAge() {
		return workAge;
	}

	public void setWorkAge(Integer workAge) {
		this.workAge = workAge;
	}

	public String getHeadPortraitId() {
		return headPortraitId;
	}

	public void setHeadPortraitId(String headPortraitId) {
		this.headPortraitId = headPortraitId;
	}

	public String getIdCard() {
		return idCard;
	}

	public void setIdCard(String idCard) {
		this.idCard = idCard;
	}

	public String getQualificationCertificateId() {
		return qualificationCertificateId;
	}

	public void setQualificationCertificateId(String qualificationCertificateId) {
		this.qualificationCertificateId = qualificationCertificateId;
	}

	public String getIdCardFrontId() {
		return idCardFrontId;
	}

	public void setIdCardFrontId(String idCardFrontId) {
		this.idCardFrontId = idCardFrontId;
	}

	public String getIdCardBackId() {
		return idCardBackId;
	}

	public void setIdCardBackId(String idCardBackId) {
		this.idCardBackId = idCardBackId;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getIceContactName() {
		return iceContactName;
	}

	public void setIceContactName(String iceContactName) {
		this.iceContactName = iceContactName;
	}

	public String getIceContactMobile() {
		return iceContactMobile;
	}

	public void setIceContactMobile(String iceContactMobile) {
		this.iceContactMobile = iceContactMobile;
	}

	public String getBloodType() {
		return bloodType;
	}

	public void setBloodType(String bloodType) {
		this.bloodType = bloodType;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getConstructionId() {
		return constructionId;
	}

	public void setConstructionId(String constructionId) {
		this.constructionId = constructionId;
	}

	public String getTeamId() {
		return teamId;
	}

	public void setTeamId(String teamId) {
		this.teamId = teamId;
	}

	public MultipartFile getHeadFile() {
		return headFile;
	}

	public void setHeadFile(MultipartFile headFile) {
		this.headFile = headFile;
	}

	public MultipartFile getIdCardFrontFile() {
		return idCardFrontFile;
	}

	public void setIdCardFrontFile(MultipartFile idCardFrontFile) {
		this.idCardFrontFile = idCardFrontFile;
	}

	public MultipartFile getIdCardBackFile() {
		return idCardBackFile;
	}

	public void setIdCardBackFile(MultipartFile idCardBackFile) {
		this.idCardBackFile = idCardBackFile;
	}

	public MultipartFile getQualificationCertificateFile() {
		return qualificationCertificateFile;
	}

	public void setQualificationCertificateFile(MultipartFile qualificationCertificateFile) {
		this.qualificationCertificateFile = qualificationCertificateFile;
	}

	public Integer getStartWorkAge() {
		return startWorkAge;
	}

	public void setStartWorkAge(Integer startWorkAge) {
		this.startWorkAge = startWorkAge;
	}

	public Integer getEndWorkAge() {
		return endWorkAge;
	}

	public void setEndWorkAge(Integer endWorkAge) {
		this.endWorkAge = endWorkAge;
	}

	public String getProjectCompanyId() {
		return projectCompanyId;
	}

	public void setProjectCompanyId(String projectCompanyId) {
		this.projectCompanyId = projectCompanyId;
	}

	public static int getStatusEffect() {
		return STATUS_EFFECT;
	}

	public static int getStatusNoEffect() {
		return STATUS_NO_EFFECT;
	}

	public Integer getPositionWorkType() {
		return positionWorkType;
	}

	public void setPositionWorkType(Integer positionWorkType) {
		this.positionWorkType = positionWorkType;
	}

	public Integer getIsLeader() {
		return isLeader;
	}

	public void setIsLeader(Integer isLeader) {
		this.isLeader = isLeader;
	}

	public String getCertificateNumber() {
		return certificateNumber;
	}

	public void setCertificateNumber(String certificateNumber) {
		this.certificateNumber = certificateNumber;
	}

	public Integer getCertificateType() {
		return certificateType;
	}

	public void setCertificateType(Integer certificateType) {
		this.certificateType = certificateType;
	}

	public String getCertificateIssueDate() {
		return certificateIssueDate;
	}

	public void setCertificateIssueDate(String certificateIssueDate) {
		this.certificateIssueDate = certificateIssueDate;
	}

	public String getCertificateValidDate() {
		return certificateValidDate;
	}

	public void setCertificateValidDate(String certificateValidDate) {
		this.certificateValidDate = certificateValidDate;
	}

	public String getCertificateIssueOrgans() {
		return certificateIssueOrgans;
	}

	public void setCertificateIssueOrgans(String certificateIssueOrgans) {
		this.certificateIssueOrgans = certificateIssueOrgans;
	}

	public String getHeadFilePath() {
		return headFilePath;
	}

	public void setHeadFilePath(String headFilePath) {
		this.headFilePath = headFilePath;
	}

	public String getIdCardFrontFilePath() {
		return idCardFrontFilePath;
	}

	public void setIdCardFrontFilePath(String idCardFrontFilePath) {
		this.idCardFrontFilePath = idCardFrontFilePath;
	}

	public String getIdCardBackFilePath() {
		return idCardBackFilePath;
	}

	public void setIdCardBackFilePath(String idCardBackFilePath) {
		this.idCardBackFilePath = idCardBackFilePath;
	}

	public String getQualificationCertificateFilePath() {
		return qualificationCertificateFilePath;
	}

	public void setQualificationCertificateFilePath(String qualificationCertificateFilePath) {
		this.qualificationCertificateFilePath = qualificationCertificateFilePath;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getProjectCompanyName() {
		return projectCompanyName;
	}

	public void setProjectCompanyName(String projectCompanyName) {
		this.projectCompanyName = projectCompanyName;
	}

	public String getConstructionName() {
		return constructionName;
	}

	public void setConstructionName(String constructionName) {
		this.constructionName = constructionName;
	}

	public String getAreaId() {
		return areaId;
	}

	public void setAreaId(String areaId) {
		this.areaId = areaId;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getPositionWorkTypeName() {
		return positionWorkTypeName;
	}

	public void setPositionWorkTypeName(String positionWorkTypeName) {
		this.positionWorkTypeName = positionWorkTypeName;
	}

	public String getCertificateTypeName() {
		return certificateTypeName;
	}

	public void setCertificateTypeName(String certificateTypeName) {
		this.certificateTypeName = certificateTypeName;
	}

	public String getCertificateId() {
		return certificateId;
	}

	public void setCertificateId(String certificateId) {
		this.certificateId = certificateId;
	}

	public String getConstructionPersonId() {
		return constructionPersonId;
	}

	public void setConstructionPersonId(String constructionPersonId) {
		this.constructionPersonId = constructionPersonId;
	}

	public String getClientUserId() {
		return clientUserId;
	}

	public void setClientUserId(String clientUserId) {
		this.clientUserId = clientUserId;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public String getBankCard() {
		return bankCard;
	}

	public void setBankCard(String bankCard) {
		this.bankCard = bankCard;
	}

	public String getPersonId() {
		return personId;
	}

	public void setPersonId(String personId) {
		this.personId = personId;
	}

	public String getJobNumber() {
		return jobNumber;
	}

	public void setJobNumber(String jobNumber) {
		this.jobNumber = jobNumber;
	}

	@Override
	public Date getCreateTime() {
		return createTime;
	}

	@Override
	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public int getAttendanceType() {
		return attendanceType;
	}

	public void setAttendanceType(int attendanceType) {
		this.attendanceType = attendanceType;
	}

	public String getCreateDate() {
		return createDate;
	}

	public void setCreateDate(String createDate) {
		this.createDate = createDate;
	}
}
