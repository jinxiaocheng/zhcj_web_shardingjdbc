
package org.tempuri;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>Record complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="Record"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://tempuri.org/}EntityBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="TrainType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TeachType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrainName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrainPlace" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="StartTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="TrainTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="TrainContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PrecisName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrainDepart" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrainPartType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TrainPeriod" type="{http://www.w3.org/2001/XMLSchema}double"/&gt;
 *         &lt;element name="TotalTime" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DepartCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ISFINISH" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="VideoID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DemandID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BoxNo" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="standby1" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="standby2" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="standby3" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="standby4" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="standby5" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UploadTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="OwnerDeptName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Record", propOrder = {
    "trainType",
    "teachType",
    "trainName",
    "trainPlace",
    "startTime",
    "trainTime",
    "trainContent",
    "precisName",
    "trainDepart",
    "trainPartType",
    "trainPeriod",
    "totalTime",
    "departCode",
    "isfinish",
    "videoID",
    "demandID",
    "boxNo",
    "standby1",
    "standby2",
    "standby3",
    "standby4",
    "standby5",
    "uploadTime",
    "ownerDeptName"
})
public class Record
    extends EntityBase
{

    @XmlElement(name = "TrainType")
    protected String trainType;
    @XmlElement(name = "TeachType")
    protected String teachType;
    @XmlElement(name = "TrainName")
    protected String trainName;
    @XmlElement(name = "TrainPlace")
    protected String trainPlace;
    @XmlElement(name = "StartTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar startTime;
    @XmlElement(name = "TrainTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar trainTime;
    @XmlElement(name = "TrainContent")
    protected String trainContent;
    @XmlElement(name = "PrecisName")
    protected String precisName;
    @XmlElement(name = "TrainDepart")
    protected String trainDepart;
    @XmlElement(name = "TrainPartType")
    protected String trainPartType;
    @XmlElement(name = "TrainPeriod")
    protected double trainPeriod;
    @XmlElement(name = "TotalTime")
    protected String totalTime;
    @XmlElement(name = "DepartCode")
    protected String departCode;
    @XmlElement(name = "ISFINISH")
    protected String isfinish;
    @XmlElement(name = "VideoID")
    protected String videoID;
    @XmlElement(name = "DemandID")
    protected String demandID;
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
    @XmlElement(name = "OwnerDeptName")
    protected String ownerDeptName;

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
     * ��ȡteachType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTeachType() {
        return teachType;
    }

    /**
     * ����teachType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTeachType(String value) {
        this.teachType = value;
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
     * ��ȡtrainPlace���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainPlace() {
        return trainPlace;
    }

    /**
     * ����trainPlace���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainPlace(String value) {
        this.trainPlace = value;
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
     * ��ȡprecisName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPrecisName() {
        return precisName;
    }

    /**
     * ����precisName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPrecisName(String value) {
        this.precisName = value;
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
     * ��ȡtrainPartType���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTrainPartType() {
        return trainPartType;
    }

    /**
     * ����trainPartType���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTrainPartType(String value) {
        this.trainPartType = value;
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
     * ��ȡisfinish���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getISFINISH() {
        return isfinish;
    }

    /**
     * ����isfinish���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setISFINISH(String value) {
        this.isfinish = value;
    }

    /**
     * ��ȡvideoID���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getVideoID() {
        return videoID;
    }

    /**
     * ����videoID���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setVideoID(String value) {
        this.videoID = value;
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

    /**
     * ��ȡownerDeptName���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOwnerDeptName() {
        return ownerDeptName;
    }

    /**
     * ����ownerDeptName���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOwnerDeptName(String value) {
        this.ownerDeptName = value;
    }

}
