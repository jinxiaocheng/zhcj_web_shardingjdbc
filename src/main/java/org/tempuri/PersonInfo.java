
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>PersonInfo complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="PersonInfo"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://tempuri.org/}EntityBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PersonName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdentifyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Address" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Integral" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="Station" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TraID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PersonOrder" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="UnitName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UnitCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UnitID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TraPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IsOut" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BlackList" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FingerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ReadCardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TelPhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BoxID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OwnerDeptID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RegisterDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="AvailabeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="EntranceDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="LeaveDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="TrainCount" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="TrainHour" type="{http://www.w3.org/2001/XMLSchema}float"/&gt;
 *         &lt;element name="IsCard" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IsPhoto" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Native" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Age" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ISPass" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="LastExamTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="ProjectName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProjectNamePart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProjectID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ProjectPartID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PersonPhotoPath" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EmergencyContacts" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="EmergencyContactsTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="MaritalStatus" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="RegisteredType" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="HealthReport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SafeDuty" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ContractOfLabour" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BirthDay" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="Degrees" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ContractNumber" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BloodType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BuildArea" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SufferDeptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Passport" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CategoryLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PersonScan" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Standby7" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="UploadTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonInfo", propOrder = {
    "personName",
    "sex",
    "identifyID",
    "fileName",
    "address",
    "nation",
    "integral",
    "station",
    "category",
    "traID",
    "personOrder",
    "unitName",
    "unitCode",
    "unitID",
    "traPrincipal",
    "isOut",
    "blackList",
    "state",
    "fingerId",
    "readCardId",
    "email",
    "telPhone",
    "boxID",
    "ownerDeptID",
    "registerDate",
    "availabeDate",
    "entranceDate",
    "leaveDate",
    "trainCount",
    "trainHour",
    "isCard",
    "isPhoto",
    "_native",
    "age",
    "isPass",
    "lastExamTime",
    "projectName",
    "projectNamePart",
    "projectID",
    "projectPartID",
    "personPhotoPath",
    "emergencyContacts",
    "emergencyContactsTel",
    "maritalStatus",
    "registeredType",
    "healthReport",
    "safeDuty",
    "contractOfLabour",
    "birthDay",
    "degrees",
    "contractNumber",
    "bloodType",
    "buildArea",
    "sufferDeptName",
    "passport",
    "categoryLevel",
    "personScan",
    "standby7",
    "uploadTime"
})
public class PersonInfo
    extends EntityBase
{

    @XmlElement(name = "PersonName")
    protected String personName;
    @XmlElement(name = "Sex")
    protected String sex;
    @XmlElement(name = "IdentifyID")
    protected String identifyID;
    @XmlElement(name = "FileName")
    protected String fileName;
    @XmlElement(name = "Address")
    protected String address;
    @XmlElement(name = "Nation")
    protected String nation;
    @XmlElement(name = "Integral")
    protected double integral;
    @XmlElement(name = "Station")
    protected String station;
    @XmlElement(name = "Category")
    protected String category;
    @XmlElement(name = "TraID")
    protected String traID;
    @XmlElement(name = "PersonOrder")
    protected int personOrder;
    @XmlElement(name = "UnitName")
    protected String unitName;
    @XmlElement(name = "UnitCode")
    protected String unitCode;
    @XmlElement(name = "UnitID")
    protected String unitID;
    @XmlElement(name = "TraPrincipal")
    protected String traPrincipal;
    @XmlElement(name = "IsOut")
    protected String isOut;
    @XmlElement(name = "BlackList")
    protected String blackList;
    @XmlElement(name = "State")
    protected String state;
    @XmlElement(name = "FingerId")
    protected String fingerId;
    @XmlElement(name = "ReadCardId")
    protected String readCardId;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "TelPhone")
    protected String telPhone;
    @XmlElement(name = "BoxID")
    protected String boxID;
    @XmlElement(name = "OwnerDeptID")
    protected String ownerDeptID;
    @XmlElement(name = "RegisterDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar registerDate;
    @XmlElement(name = "AvailabeDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar availabeDate;
    @XmlElement(name = "EntranceDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar entranceDate;
    @XmlElement(name = "LeaveDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar leaveDate;
    @XmlElement(name = "TrainCount")
    protected int trainCount;
    @XmlElement(name = "TrainHour")
    protected float trainHour;
    @XmlElement(name = "IsCard")
    protected String isCard;
    @XmlElement(name = "IsPhoto")
    protected String isPhoto;
    @XmlElement(name = "Native")
    protected String _native;
    @XmlElement(name = "Age")
    protected int age;
    @XmlElement(name = "ISPass")
    protected int isPass;
    @XmlElement(name = "LastExamTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar lastExamTime;
    @XmlElement(name = "ProjectName")
    protected String projectName;
    @XmlElement(name = "ProjectNamePart")
    protected String projectNamePart;
    @XmlElement(name = "ProjectID")
    protected String projectID;
    @XmlElement(name = "ProjectPartID")
    protected String projectPartID;
    @XmlElement(name = "PersonPhotoPath")
    protected String personPhotoPath;
    @XmlElement(name = "EmergencyContacts")
    protected String emergencyContacts;
    @XmlElement(name = "EmergencyContactsTel")
    protected String emergencyContactsTel;
    @XmlElement(name = "MaritalStatus")
    protected int maritalStatus;
    @XmlElement(name = "RegisteredType")
    protected int registeredType;
    @XmlElement(name = "HealthReport")
    protected String healthReport;
    @XmlElement(name = "SafeDuty")
    protected String safeDuty;
    @XmlElement(name = "ContractOfLabour")
    protected String contractOfLabour;
    @XmlElement(name = "BirthDay", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar birthDay;
    @XmlElement(name = "Degrees")
    protected String degrees;
    @XmlElement(name = "ContractNumber")
    protected String contractNumber;
    @XmlElement(name = "BloodType")
    protected String bloodType;
    @XmlElement(name = "BuildArea")
    protected String buildArea;
    @XmlElement(name = "SufferDeptName")
    protected String sufferDeptName;
    @XmlElement(name = "Passport")
    protected String passport;
    @XmlElement(name = "CategoryLevel")
    protected String categoryLevel;
    @XmlElement(name = "PersonScan")
    protected String personScan;
    @XmlElement(name = "Standby7")
    protected int standby7;
    @XmlElement(name = "UploadTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar uploadTime;

    /**
     * ��ȡpersonName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * ����personName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonName(String value) {
        this.personName = value;
    }

    /**
     * ��ȡsex���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSex() {
        return sex;
    }

    /**
     * ����sex���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSex(String value) {
        this.sex = value;
    }

    /**
     * ��ȡidentifyID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifyID() {
        return identifyID;
    }

    /**
     * ����identifyID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifyID(String value) {
        this.identifyID = value;
    }

    /**
     * ��ȡfileName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * ����fileName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFileName(String value) {
        this.fileName = value;
    }

    /**
     * ��ȡaddress���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAddress() {
        return address;
    }

    /**
     * ����address���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAddress(String value) {
        this.address = value;
    }

    /**
     * ��ȡnation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNation() {
        return nation;
    }

    /**
     * ����nation���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNation(String value) {
        this.nation = value;
    }

    /**
     * ��ȡintegral���Ե�ֵ��
     * 
     */
    public double getIntegral() {
        return integral;
    }

    /**
     * ����integral���Ե�ֵ��
     * 
     */
    public void setIntegral(double value) {
        this.integral = value;
    }

    /**
     * ��ȡstation���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStation() {
        return station;
    }

    /**
     * ����station���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStation(String value) {
        this.station = value;
    }

    /**
     * ��ȡcategory���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategory() {
        return category;
    }

    /**
     * ����category���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategory(String value) {
        this.category = value;
    }

    /**
     * ��ȡtraID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTraID() {
        return traID;
    }

    /**
     * ����traID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTraID(String value) {
        this.traID = value;
    }

    /**
     * ��ȡpersonOrder���Ե�ֵ��
     * 
     */
    public int getPersonOrder() {
        return personOrder;
    }

    /**
     * ����personOrder���Ե�ֵ��
     * 
     */
    public void setPersonOrder(int value) {
        this.personOrder = value;
    }

    /**
     * ��ȡunitName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitName() {
        return unitName;
    }

    /**
     * ����unitName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitName(String value) {
        this.unitName = value;
    }

    /**
     * ��ȡunitCode���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitCode() {
        return unitCode;
    }

    /**
     * ����unitCode���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitCode(String value) {
        this.unitCode = value;
    }

    /**
     * ��ȡunitID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnitID() {
        return unitID;
    }

    /**
     * ����unitID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnitID(String value) {
        this.unitID = value;
    }

    /**
     * ��ȡtraPrincipal���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTraPrincipal() {
        return traPrincipal;
    }

    /**
     * ����traPrincipal���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTraPrincipal(String value) {
        this.traPrincipal = value;
    }

    /**
     * ��ȡisOut���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsOut() {
        return isOut;
    }

    /**
     * ����isOut���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsOut(String value) {
        this.isOut = value;
    }

    /**
     * ��ȡblackList���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBlackList() {
        return blackList;
    }

    /**
     * ����blackList���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBlackList(String value) {
        this.blackList = value;
    }

    /**
     * ��ȡstate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getState() {
        return state;
    }

    /**
     * ����state���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setState(String value) {
        this.state = value;
    }

    /**
     * ��ȡfingerId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getFingerId() {
        return fingerId;
    }

    /**
     * ����fingerId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setFingerId(String value) {
        this.fingerId = value;
    }

    /**
     * ��ȡreadCardId���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getReadCardId() {
        return readCardId;
    }

    /**
     * ����readCardId���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setReadCardId(String value) {
        this.readCardId = value;
    }

    /**
     * ��ȡemail���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * ����email���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * ��ȡtelPhone���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelPhone() {
        return telPhone;
    }

    /**
     * ����telPhone���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelPhone(String value) {
        this.telPhone = value;
    }

    /**
     * ��ȡboxID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoxID() {
        return boxID;
    }

    /**
     * ����boxID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoxID(String value) {
        this.boxID = value;
    }

    /**
     * ��ȡownerDeptID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerDeptID() {
        return ownerDeptID;
    }

    /**
     * ����ownerDeptID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerDeptID(String value) {
        this.ownerDeptID = value;
    }

    /**
     * ��ȡregisterDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegisterDate() {
        return registerDate;
    }

    /**
     * ����registerDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegisterDate(XMLGregorianCalendar value) {
        this.registerDate = value;
    }

    /**
     * ��ȡavailabeDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getAvailabeDate() {
        return availabeDate;
    }

    /**
     * ����availabeDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setAvailabeDate(XMLGregorianCalendar value) {
        this.availabeDate = value;
    }

    /**
     * ��ȡentranceDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getEntranceDate() {
        return entranceDate;
    }

    /**
     * ����entranceDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setEntranceDate(XMLGregorianCalendar value) {
        this.entranceDate = value;
    }

    /**
     * ��ȡleaveDate���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLeaveDate() {
        return leaveDate;
    }

    /**
     * ����leaveDate���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLeaveDate(XMLGregorianCalendar value) {
        this.leaveDate = value;
    }

    /**
     * ��ȡtrainCount���Ե�ֵ��
     * 
     */
    public int getTrainCount() {
        return trainCount;
    }

    /**
     * ����trainCount���Ե�ֵ��
     * 
     */
    public void setTrainCount(int value) {
        this.trainCount = value;
    }

    /**
     * ��ȡtrainHour���Ե�ֵ��
     * 
     */
    public float getTrainHour() {
        return trainHour;
    }

    /**
     * ����trainHour���Ե�ֵ��
     * 
     */
    public void setTrainHour(float value) {
        this.trainHour = value;
    }

    /**
     * ��ȡisCard���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsCard() {
        return isCard;
    }

    /**
     * ����isCard���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsCard(String value) {
        this.isCard = value;
    }

    /**
     * ��ȡisPhoto���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsPhoto() {
        return isPhoto;
    }

    /**
     * ����isPhoto���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsPhoto(String value) {
        this.isPhoto = value;
    }

    /**
     * ��ȡnative���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getNative() {
        return _native;
    }

    /**
     * ����native���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setNative(String value) {
        this._native = value;
    }

    /**
     * ��ȡage���Ե�ֵ��
     * 
     */
    public int getAge() {
        return age;
    }

    /**
     * ����age���Ե�ֵ��
     * 
     */
    public void setAge(int value) {
        this.age = value;
    }

    /**
     * ��ȡisPass���Ե�ֵ��
     * 
     */
    public int getISPass() {
        return isPass;
    }

    /**
     * ����isPass���Ե�ֵ��
     * 
     */
    public void setISPass(int value) {
        this.isPass = value;
    }

    /**
     * ��ȡlastExamTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getLastExamTime() {
        return lastExamTime;
    }

    /**
     * ����lastExamTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setLastExamTime(XMLGregorianCalendar value) {
        this.lastExamTime = value;
    }

    /**
     * ��ȡprojectName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectName() {
        return projectName;
    }

    /**
     * ����projectName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectName(String value) {
        this.projectName = value;
    }

    /**
     * ��ȡprojectNamePart���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectNamePart() {
        return projectNamePart;
    }

    /**
     * ����projectNamePart���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectNamePart(String value) {
        this.projectNamePart = value;
    }

    /**
     * ��ȡprojectID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectID() {
        return projectID;
    }

    /**
     * ����projectID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectID(String value) {
        this.projectID = value;
    }

    /**
     * ��ȡprojectPartID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getProjectPartID() {
        return projectPartID;
    }

    /**
     * ����projectPartID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setProjectPartID(String value) {
        this.projectPartID = value;
    }

    /**
     * ��ȡpersonPhotoPath���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonPhotoPath() {
        return personPhotoPath;
    }

    /**
     * ����personPhotoPath���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonPhotoPath(String value) {
        this.personPhotoPath = value;
    }

    /**
     * ��ȡemergencyContacts���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmergencyContacts() {
        return emergencyContacts;
    }

    /**
     * ����emergencyContacts���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmergencyContacts(String value) {
        this.emergencyContacts = value;
    }

    /**
     * ��ȡemergencyContactsTel���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmergencyContactsTel() {
        return emergencyContactsTel;
    }

    /**
     * ����emergencyContactsTel���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmergencyContactsTel(String value) {
        this.emergencyContactsTel = value;
    }

    /**
     * ��ȡmaritalStatus���Ե�ֵ��
     * 
     */
    public int getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * ����maritalStatus���Ե�ֵ��
     * 
     */
    public void setMaritalStatus(int value) {
        this.maritalStatus = value;
    }

    /**
     * ��ȡregisteredType���Ե�ֵ��
     * 
     */
    public int getRegisteredType() {
        return registeredType;
    }

    /**
     * ����registeredType���Ե�ֵ��
     * 
     */
    public void setRegisteredType(int value) {
        this.registeredType = value;
    }

    /**
     * ��ȡhealthReport���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getHealthReport() {
        return healthReport;
    }

    /**
     * ����healthReport���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setHealthReport(String value) {
        this.healthReport = value;
    }

    /**
     * ��ȡsafeDuty���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSafeDuty() {
        return safeDuty;
    }

    /**
     * ����safeDuty���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSafeDuty(String value) {
        this.safeDuty = value;
    }

    /**
     * ��ȡcontractOfLabour���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractOfLabour() {
        return contractOfLabour;
    }

    /**
     * ����contractOfLabour���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractOfLabour(String value) {
        this.contractOfLabour = value;
    }

    /**
     * ��ȡbirthDay���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getBirthDay() {
        return birthDay;
    }

    /**
     * ����birthDay���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setBirthDay(XMLGregorianCalendar value) {
        this.birthDay = value;
    }

    /**
     * ��ȡdegrees���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDegrees() {
        return degrees;
    }

    /**
     * ����degrees���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDegrees(String value) {
        this.degrees = value;
    }

    /**
     * ��ȡcontractNumber���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getContractNumber() {
        return contractNumber;
    }

    /**
     * ����contractNumber���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setContractNumber(String value) {
        this.contractNumber = value;
    }

    /**
     * ��ȡbloodType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBloodType() {
        return bloodType;
    }

    /**
     * ����bloodType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBloodType(String value) {
        this.bloodType = value;
    }

    /**
     * ��ȡbuildArea���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBuildArea() {
        return buildArea;
    }

    /**
     * ����buildArea���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBuildArea(String value) {
        this.buildArea = value;
    }

    /**
     * ��ȡsufferDeptName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSufferDeptName() {
        return sufferDeptName;
    }

    /**
     * ����sufferDeptName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSufferDeptName(String value) {
        this.sufferDeptName = value;
    }

    /**
     * ��ȡpassport���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassport() {
        return passport;
    }

    /**
     * ����passport���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassport(String value) {
        this.passport = value;
    }

    /**
     * ��ȡcategoryLevel���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCategoryLevel() {
        return categoryLevel;
    }

    /**
     * ����categoryLevel���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCategoryLevel(String value) {
        this.categoryLevel = value;
    }

    /**
     * ��ȡpersonScan���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPersonScan() {
        return personScan;
    }

    /**
     * ����personScan���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPersonScan(String value) {
        this.personScan = value;
    }

    /**
     * ��ȡstandby7���Ե�ֵ��
     * 
     */
    public int getStandby7() {
        return standby7;
    }

    /**
     * ����standby7���Ե�ֵ��
     * 
     */
    public void setStandby7(int value) {
        this.standby7 = value;
    }

    /**
     * ��ȡuploadTime���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getUploadTime() {
        return uploadTime;
    }

    /**
     * ����uploadTime���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setUploadTime(XMLGregorianCalendar value) {
        this.uploadTime = value;
    }

}
