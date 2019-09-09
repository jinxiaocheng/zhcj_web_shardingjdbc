
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>PersonInfo complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
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
     * 获取personName属性的值。
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
     * 设置personName属性的值。
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
     * 获取sex属性的值。
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
     * 设置sex属性的值。
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
     * 获取identifyID属性的值。
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
     * 设置identifyID属性的值。
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
     * 获取fileName属性的值。
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
     * 设置fileName属性的值。
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
     * 获取address属性的值。
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
     * 设置address属性的值。
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
     * 获取nation属性的值。
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
     * 设置nation属性的值。
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
     * 获取integral属性的值。
     * 
     */
    public double getIntegral() {
        return integral;
    }

    /**
     * 设置integral属性的值。
     * 
     */
    public void setIntegral(double value) {
        this.integral = value;
    }

    /**
     * 获取station属性的值。
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
     * 设置station属性的值。
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
     * 获取category属性的值。
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
     * 设置category属性的值。
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
     * 获取traID属性的值。
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
     * 设置traID属性的值。
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
     * 获取personOrder属性的值。
     * 
     */
    public int getPersonOrder() {
        return personOrder;
    }

    /**
     * 设置personOrder属性的值。
     * 
     */
    public void setPersonOrder(int value) {
        this.personOrder = value;
    }

    /**
     * 获取unitName属性的值。
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
     * 设置unitName属性的值。
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
     * 获取unitCode属性的值。
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
     * 设置unitCode属性的值。
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
     * 获取unitID属性的值。
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
     * 设置unitID属性的值。
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
     * 获取traPrincipal属性的值。
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
     * 设置traPrincipal属性的值。
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
     * 获取isOut属性的值。
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
     * 设置isOut属性的值。
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
     * 获取blackList属性的值。
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
     * 设置blackList属性的值。
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
     * 获取state属性的值。
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
     * 设置state属性的值。
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
     * 获取fingerId属性的值。
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
     * 设置fingerId属性的值。
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
     * 获取readCardId属性的值。
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
     * 设置readCardId属性的值。
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
     * 获取email属性的值。
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
     * 设置email属性的值。
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
     * 获取telPhone属性的值。
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
     * 设置telPhone属性的值。
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
     * 获取boxID属性的值。
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
     * 设置boxID属性的值。
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
     * 获取ownerDeptID属性的值。
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
     * 设置ownerDeptID属性的值。
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
     * 获取registerDate属性的值。
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
     * 设置registerDate属性的值。
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
     * 获取availabeDate属性的值。
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
     * 设置availabeDate属性的值。
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
     * 获取entranceDate属性的值。
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
     * 设置entranceDate属性的值。
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
     * 获取leaveDate属性的值。
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
     * 设置leaveDate属性的值。
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
     * 获取trainCount属性的值。
     * 
     */
    public int getTrainCount() {
        return trainCount;
    }

    /**
     * 设置trainCount属性的值。
     * 
     */
    public void setTrainCount(int value) {
        this.trainCount = value;
    }

    /**
     * 获取trainHour属性的值。
     * 
     */
    public float getTrainHour() {
        return trainHour;
    }

    /**
     * 设置trainHour属性的值。
     * 
     */
    public void setTrainHour(float value) {
        this.trainHour = value;
    }

    /**
     * 获取isCard属性的值。
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
     * 设置isCard属性的值。
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
     * 获取isPhoto属性的值。
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
     * 设置isPhoto属性的值。
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
     * 获取native属性的值。
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
     * 设置native属性的值。
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
     * 获取age属性的值。
     * 
     */
    public int getAge() {
        return age;
    }

    /**
     * 设置age属性的值。
     * 
     */
    public void setAge(int value) {
        this.age = value;
    }

    /**
     * 获取isPass属性的值。
     * 
     */
    public int getISPass() {
        return isPass;
    }

    /**
     * 设置isPass属性的值。
     * 
     */
    public void setISPass(int value) {
        this.isPass = value;
    }

    /**
     * 获取lastExamTime属性的值。
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
     * 设置lastExamTime属性的值。
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
     * 获取projectName属性的值。
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
     * 设置projectName属性的值。
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
     * 获取projectNamePart属性的值。
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
     * 设置projectNamePart属性的值。
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
     * 获取projectID属性的值。
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
     * 设置projectID属性的值。
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
     * 获取projectPartID属性的值。
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
     * 设置projectPartID属性的值。
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
     * 获取personPhotoPath属性的值。
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
     * 设置personPhotoPath属性的值。
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
     * 获取emergencyContacts属性的值。
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
     * 设置emergencyContacts属性的值。
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
     * 获取emergencyContactsTel属性的值。
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
     * 设置emergencyContactsTel属性的值。
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
     * 获取maritalStatus属性的值。
     * 
     */
    public int getMaritalStatus() {
        return maritalStatus;
    }

    /**
     * 设置maritalStatus属性的值。
     * 
     */
    public void setMaritalStatus(int value) {
        this.maritalStatus = value;
    }

    /**
     * 获取registeredType属性的值。
     * 
     */
    public int getRegisteredType() {
        return registeredType;
    }

    /**
     * 设置registeredType属性的值。
     * 
     */
    public void setRegisteredType(int value) {
        this.registeredType = value;
    }

    /**
     * 获取healthReport属性的值。
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
     * 设置healthReport属性的值。
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
     * 获取safeDuty属性的值。
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
     * 设置safeDuty属性的值。
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
     * 获取contractOfLabour属性的值。
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
     * 设置contractOfLabour属性的值。
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
     * 获取birthDay属性的值。
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
     * 设置birthDay属性的值。
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
     * 获取degrees属性的值。
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
     * 设置degrees属性的值。
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
     * 获取contractNumber属性的值。
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
     * 设置contractNumber属性的值。
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
     * 获取bloodType属性的值。
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
     * 设置bloodType属性的值。
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
     * 获取buildArea属性的值。
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
     * 设置buildArea属性的值。
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
     * 获取sufferDeptName属性的值。
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
     * 设置sufferDeptName属性的值。
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
     * 获取passport属性的值。
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
     * 设置passport属性的值。
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
     * 获取categoryLevel属性的值。
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
     * 设置categoryLevel属性的值。
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
     * 获取personScan属性的值。
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
     * 设置personScan属性的值。
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
     * 获取standby7属性的值。
     * 
     */
    public int getStandby7() {
        return standby7;
    }

    /**
     * 设置standby7属性的值。
     * 
     */
    public void setStandby7(int value) {
        this.standby7 = value;
    }

    /**
     * 获取uploadTime属性的值。
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
     * 设置uploadTime属性的值。
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
