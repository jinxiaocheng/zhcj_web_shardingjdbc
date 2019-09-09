
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>PersonRecord complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡid���Ե�ֵ��
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
     * ����id���Ե�ֵ��
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
     * ��ȡtrainContent���Ե�ֵ��
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
     * ����trainContent���Ե�ֵ��
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
     * ��ȡtotalTime���Ե�ֵ��
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
     * ����totalTime���Ե�ֵ��
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
     * ��ȡdemandID���Ե�ֵ��
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
     * ����demandID���Ե�ֵ��
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
     * ��ȡtrainName���Ե�ֵ��
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
     * ����trainName���Ե�ֵ��
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
     * ��ȡtrainType���Ե�ֵ��
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
     * ����trainType���Ե�ֵ��
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
     * ��ȡstartTime���Ե�ֵ��
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
     * ����startTime���Ե�ֵ��
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
     * ��ȡtrainTime���Ե�ֵ��
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
     * ����trainTime���Ե�ֵ��
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
     * ��ȡtrainPeriod���Ե�ֵ��
     * 
     */
    public double getTrainPeriod() {
        return trainPeriod;
    }

    /**
     * ����trainPeriod���Ե�ֵ��
     * 
     */
    public void setTrainPeriod(double value) {
        this.trainPeriod = value;
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

}
