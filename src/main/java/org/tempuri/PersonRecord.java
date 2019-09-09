
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>PersonRecord complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PersonRecord"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RecordID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrainContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TotalTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DepartCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DemandID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrainDepart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrainName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrainType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="TrainTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="TrainPeriod" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="SGrade" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="ISPass" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="IdentifyID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonRecord", propOrder = {
    "id",
    "recordID",
    "trainContent",
    "totalTime",
    "departCode",
    "demandID",
    "trainDepart",
    "trainName",
    "trainType",
    "startTime",
    "trainTime",
    "trainPeriod",
    "sGrade",
    "isPass",
    "identifyID"
})
public class PersonRecord {

    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "RecordID")
    protected String recordID;
    @XmlElement(name = "TrainContent")
    protected String trainContent;
    @XmlElement(name = "TotalTime")
    protected String totalTime;
    @XmlElement(name = "DepartCode")
    protected String departCode;
    @XmlElement(name = "DemandID")
    protected String demandID;
    @XmlElement(name = "TrainDepart")
    protected String trainDepart;
    @XmlElement(name = "TrainName")
    protected String trainName;
    @XmlElement(name = "TrainType")
    protected String trainType;
    @XmlElement(name = "StartTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;
    @XmlElement(name = "TrainTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar trainTime;
    @XmlElement(name = "TrainPeriod")
    protected double trainPeriod;
    @XmlElement(name = "SGrade")
    protected double sGrade;
    @XmlElement(name = "ISPass")
    protected int isPass;
    @XmlElement(name = "IdentifyID")
    protected String identifyID;

    /**
     * 获取id属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getID() {
        return id;
    }

    /**
     * 设置id属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setID(String value) {
        this.id = value;
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
     * 获取trainContent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainContent() {
        return trainContent;
    }

    /**
     * 设置trainContent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainContent(String value) {
        this.trainContent = value;
    }

    /**
     * 获取totalTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTotalTime() {
        return totalTime;
    }

    /**
     * 设置totalTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTotalTime(String value) {
        this.totalTime = value;
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
     * 获取demandID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDemandID() {
        return demandID;
    }

    /**
     * 设置demandID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDemandID(String value) {
        this.demandID = value;
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
     * 获取trainName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainName() {
        return trainName;
    }

    /**
     * 设置trainName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainName(String value) {
        this.trainName = value;
    }

    /**
     * 获取trainType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainType() {
        return trainType;
    }

    /**
     * 设置trainType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainType(String value) {
        this.trainType = value;
    }

    /**
     * 获取startTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getStartTime() {
        return startTime;
    }

    /**
     * 设置startTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setStartTime(XMLGregorianCalendar value) {
        this.startTime = value;
    }

    /**
     * 获取trainTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getTrainTime() {
        return trainTime;
    }

    /**
     * 设置trainTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setTrainTime(XMLGregorianCalendar value) {
        this.trainTime = value;
    }

    /**
     * 获取trainPeriod属性的值。
     * 
     */
    public double getTrainPeriod() {
        return trainPeriod;
    }

    /**
     * 设置trainPeriod属性的值。
     * 
     */
    public void setTrainPeriod(double value) {
        this.trainPeriod = value;
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

}
