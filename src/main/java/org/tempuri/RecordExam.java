
package org.tempuri;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>RecordExam complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="RecordExam"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://tempuri.org/}EntityBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ExamContent" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Answer" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ExamType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ExamImportant" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="heft" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ExamID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ExamName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ExamOrder" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
@XmlType(name = "RecordExam", propOrder = {
    "examContent",
    "answer",
    "examType",
    "examImportant",
    "heft",
    "examID",
    "examName",
    "examOrder",
    "standby1",
    "standby2",
    "standby3",
    "standby4",
    "standby5",
    "uploadTime"
})
public class RecordExam
    extends EntityBase
{

    @XmlElement(name = "ExamContent")
    protected String examContent;
    @XmlElement(name = "Answer")
    protected String answer;
    @XmlElement(name = "ExamType")
    protected String examType;
    @XmlElement(name = "ExamImportant")
    protected String examImportant;
    protected int heft;
    @XmlElement(name = "ExamID")
    protected String examID;
    @XmlElement(name = "ExamName")
    protected String examName;
    @XmlElement(name = "ExamOrder")
    protected int examOrder;
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
     * 获取examContent属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExamContent() {
        return examContent;
    }

    /**
     * 设置examContent属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExamContent(String value) {
        this.examContent = value;
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
     * 获取examType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExamType() {
        return examType;
    }

    /**
     * 设置examType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExamType(String value) {
        this.examType = value;
    }

    /**
     * 获取examImportant属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExamImportant() {
        return examImportant;
    }

    /**
     * 设置examImportant属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExamImportant(String value) {
        this.examImportant = value;
    }

    /**
     * 获取heft属性的值。
     * 
     */
    public int getHeft() {
        return heft;
    }

    /**
     * 设置heft属性的值。
     * 
     */
    public void setHeft(int value) {
        this.heft = value;
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
     * 获取examName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getExamName() {
        return examName;
    }

    /**
     * 设置examName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setExamName(String value) {
        this.examName = value;
    }

    /**
     * 获取examOrder属性的值。
     * 
     */
    public int getExamOrder() {
        return examOrder;
    }

    /**
     * 设置examOrder属性的值。
     * 
     */
    public void setExamOrder(int value) {
        this.examOrder = value;
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
