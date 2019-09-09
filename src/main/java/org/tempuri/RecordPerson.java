
package org.tempuri;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>RecordPerson complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RecordPerson"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://tempuri.org/}EntityBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="PersonName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Sex" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdentifyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Nation" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SchloolLevel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrainDepart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Station" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Category" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TraPrincipal" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RegisteDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="FileName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CommendPerson" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="FingerId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ReadCardId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AvailabeDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="LeaveDate" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DepartCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Answer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="SGrade" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="AllNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PassNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ISPass" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="RecordID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ExamID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BoxNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="standby1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="standby2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="standby3" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="standby4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="standby5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
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
@XmlType(name = "RecordPerson", propOrder = {
    "personName",
    "sex",
    "identifyID",
    "nation",
    "schloolLevel",
    "trainDepart",
    "station",
    "category",
    "traPrincipal",
    "registeDate",
    "fileName",
    "commendPerson",
    "fingerId",
    "readCardId",
    "availabeDate",
    "leaveDate",
    "departCode",
    "answer",
    "sGrade",
    "allNum",
    "passNum",
    "isPass",
    "recordID",
    "examID",
    "boxNo",
    "standby1",
    "standby2",
    "standby3",
    "standby4",
    "standby5",
    "uploadTime"
})
public class RecordPerson
    extends EntityBase
{

    @XmlElement(name = "PersonName")
    protected String personName;
    @XmlElement(name = "Sex")
    protected String sex;
    @XmlElement(name = "IdentifyID")
    protected String identifyID;
    @XmlElement(name = "Nation")
    protected String nation;
    @XmlElement(name = "SchloolLevel")
    protected String schloolLevel;
    @XmlElement(name = "TrainDepart")
    protected String trainDepart;
    @XmlElement(name = "Station")
    protected String station;
    @XmlElement(name = "Category")
    protected String category;
    @XmlElement(name = "TraPrincipal")
    protected String traPrincipal;
    @XmlElement(name = "RegisteDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar registeDate;
    @XmlElement(name = "FileName")
    protected String fileName;
    @XmlElement(name = "CommendPerson")
    protected String commendPerson;
    @XmlElement(name = "FingerId")
    protected String fingerId;
    @XmlElement(name = "ReadCardId")
    protected String readCardId;
    @XmlElement(name = "AvailabeDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar availabeDate;
    @XmlElement(name = "LeaveDate")
    protected String leaveDate;
    @XmlElement(name = "DepartCode")
    protected String departCode;
    @XmlElement(name = "Answer")
    protected String answer;
    @XmlElement(name = "SGrade")
    protected double sGrade;
    @XmlElement(name = "AllNum")
    protected String allNum;
    @XmlElement(name = "PassNum")
    protected String passNum;
    @XmlElement(name = "ISPass")
    protected int isPass;
    @XmlElement(name = "RecordID")
    protected String recordID;
    @XmlElement(name = "ExamID")
    protected String examID;
    @XmlElement(name = "BoxNo")
    protected String boxNo;
    protected String standby1;
    protected String standby2;
    @XmlElement(required = true)
    protected BigDecimal standby3;
    protected String standby4;
    protected String standby5;
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
     * 获取schloolLevel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getSchloolLevel() {
        return schloolLevel;
    }

    /**
     * 设置schloolLevel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setSchloolLevel(String value) {
        this.schloolLevel = value;
    }

    /**
     * 获取trainDepart属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainDepart() {
        return trainDepart;
    }

    /**
     * 设置trainDepart属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainDepart(String value) {
        this.trainDepart = value;
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
     * 获取registeDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getRegisteDate() {
        return registeDate;
    }

    /**
     * 设置registeDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setRegisteDate(XMLGregorianCalendar value) {
        this.registeDate = value;
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
     * 获取commendPerson属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCommendPerson() {
        return commendPerson;
    }

    /**
     * 设置commendPerson属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCommendPerson(String value) {
        this.commendPerson = value;
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
     * 获取leaveDate属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getLeaveDate() {
        return leaveDate;
    }

    /**
     * 设置leaveDate属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setLeaveDate(String value) {
        this.leaveDate = value;
    }

    /**
     * 获取departCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartCode() {
        return departCode;
    }

    /**
     * 设置departCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartCode(String value) {
        this.departCode = value;
    }

    /**
     * 获取answer属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * 设置answer属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAnswer(String value) {
        this.answer = value;
    }

    /**
     * 获取sGrade属性的值。
     * 
     */
    public double getSGrade() {
        return sGrade;
    }

    /**
     * 设置sGrade属性的值。
     * 
     */
    public void setSGrade(double value) {
        this.sGrade = value;
    }

    /**
     * 获取allNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAllNum() {
        return allNum;
    }

    /**
     * 设置allNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAllNum(String value) {
        this.allNum = value;
    }

    /**
     * 获取passNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassNum() {
        return passNum;
    }

    /**
     * 设置passNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassNum(String value) {
        this.passNum = value;
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
     * 获取recordID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRecordID() {
        return recordID;
    }

    /**
     * 设置recordID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRecordID(String value) {
        this.recordID = value;
    }

    /**
     * 获取examID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExamID() {
        return examID;
    }

    /**
     * 设置examID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExamID(String value) {
        this.examID = value;
    }

    /**
     * 获取boxNo属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBoxNo() {
        return boxNo;
    }

    /**
     * 设置boxNo属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBoxNo(String value) {
        this.boxNo = value;
    }

    /**
     * 获取standby1属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandby1() {
        return standby1;
    }

    /**
     * 设置standby1属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandby1(String value) {
        this.standby1 = value;
    }

    /**
     * 获取standby2属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandby2() {
        return standby2;
    }

    /**
     * 设置standby2属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandby2(String value) {
        this.standby2 = value;
    }

    /**
     * 获取standby3属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStandby3() {
        return standby3;
    }

    /**
     * 设置standby3属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStandby3(BigDecimal value) {
        this.standby3 = value;
    }

    /**
     * 获取standby4属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandby4() {
        return standby4;
    }

    /**
     * 设置standby4属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandby4(String value) {
        this.standby4 = value;
    }

    /**
     * 获取standby5属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getStandby5() {
        return standby5;
    }

    /**
     * 设置standby5属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setStandby5(String value) {
        this.standby5 = value;
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
