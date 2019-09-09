
package org.tempuri;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>RecordPerson complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡschloolLevel���Ե�ֵ��
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
     * ����schloolLevel���Ե�ֵ��
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
     * ��ȡtrainDepart���Ե�ֵ��
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
     * ����trainDepart���Ե�ֵ��
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
     * ��ȡregisteDate���Ե�ֵ��
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
     * ����registeDate���Ե�ֵ��
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
     * ��ȡcommendPerson���Ե�ֵ��
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
     * ����commendPerson���Ե�ֵ��
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
     * ��ȡleaveDate���Ե�ֵ��
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
     * ����leaveDate���Ե�ֵ��
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
     * ��ȡdepartCode���Ե�ֵ��
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
     * ����departCode���Ե�ֵ��
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
     * ��ȡanswer���Ե�ֵ��
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
     * ����answer���Ե�ֵ��
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
     * ��ȡsGrade���Ե�ֵ��
     * 
     */
    public double getSGrade() {
        return sGrade;
    }

    /**
     * ����sGrade���Ե�ֵ��
     * 
     */
    public void setSGrade(double value) {
        this.sGrade = value;
    }

    /**
     * ��ȡallNum���Ե�ֵ��
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
     * ����allNum���Ե�ֵ��
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
     * ��ȡpassNum���Ե�ֵ��
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
     * ����passNum���Ե�ֵ��
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
     * ��ȡrecordID���Ե�ֵ��
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
     * ����recordID���Ե�ֵ��
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
     * ��ȡexamID���Ե�ֵ��
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
     * ����examID���Ե�ֵ��
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
     * ��ȡboxNo���Ե�ֵ��
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
     * ����boxNo���Ե�ֵ��
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
     * ��ȡstandby1���Ե�ֵ��
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
     * ����standby1���Ե�ֵ��
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
     * ��ȡstandby2���Ե�ֵ��
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
     * ����standby2���Ե�ֵ��
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
     * ��ȡstandby3���Ե�ֵ��
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
     * ����standby3���Ե�ֵ��
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
     * ��ȡstandby4���Ե�ֵ��
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
     * ����standby4���Ե�ֵ��
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
     * ��ȡstandby5���Ե�ֵ��
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
     * ����standby5���Ե�ֵ��
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
