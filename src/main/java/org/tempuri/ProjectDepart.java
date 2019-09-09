
package org.tempuri;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ProjectDepart complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="ProjectDepart"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://tempuri.org/}EntityBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ParentID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DepartCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="ParentCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DepartName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DepartType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DepartShortName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DepartOrder" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="DepartSir" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DepartTel" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IsEpiboly" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Phone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Charge" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Remark" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OwnerDeptID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="BoxID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ProjectDepart", propOrder = {
    "parentID",
    "departCode",
    "parentCode",
    "departName",
    "departType",
    "departShortName",
    "departOrder",
    "state",
    "departSir",
    "departTel",
    "isEpiboly",
    "phone",
    "charge",
    "remark",
    "ownerDeptID",
    "boxID"
})
public class ProjectDepart
    extends EntityBase
{

    @XmlElement(name = "ParentID")
    protected String parentID;
    @XmlElement(name = "DepartCode")
    protected String departCode;
    @XmlElement(name = "ParentCode")
    protected String parentCode;
    @XmlElement(name = "DepartName")
    protected String departName;
    @XmlElement(name = "DepartType")
    protected String departType;
    @XmlElement(name = "DepartShortName")
    protected String departShortName;
    @XmlElement(name = "DepartOrder", required = true)
    protected BigDecimal departOrder;
    @XmlElement(name = "State")
    protected int state;
    @XmlElement(name = "DepartSir")
    protected String departSir;
    @XmlElement(name = "DepartTel")
    protected String departTel;
    @XmlElement(name = "IsEpiboly")
    protected String isEpiboly;
    @XmlElement(name = "Phone")
    protected String phone;
    @XmlElement(name = "Charge")
    protected String charge;
    @XmlElement(name = "Remark")
    protected String remark;
    @XmlElement(name = "OwnerDeptID")
    protected String ownerDeptID;
    @XmlElement(name = "BoxID")
    protected String boxID;

    /**
     * 获取parentID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentID() {
        return parentID;
    }

    /**
     * 设置parentID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentID(String value) {
        this.parentID = value;
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
     * 获取parentCode属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * 设置parentCode属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setParentCode(String value) {
        this.parentCode = value;
    }

    /**
     * 获取departName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartName() {
        return departName;
    }

    /**
     * 设置departName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartName(String value) {
        this.departName = value;
    }

    /**
     * 获取departType属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartType() {
        return departType;
    }

    /**
     * 设置departType属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartType(String value) {
        this.departType = value;
    }

    /**
     * 获取departShortName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartShortName() {
        return departShortName;
    }

    /**
     * 设置departShortName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartShortName(String value) {
        this.departShortName = value;
    }

    /**
     * 获取departOrder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getDepartOrder() {
        return departOrder;
    }

    /**
     * 设置departOrder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setDepartOrder(BigDecimal value) {
        this.departOrder = value;
    }

    /**
     * 获取state属性的值。
     * 
     */
    public int getState() {
        return state;
    }

    /**
     * 设置state属性的值。
     * 
     */
    public void setState(int value) {
        this.state = value;
    }

    /**
     * 获取departSir属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartSir() {
        return departSir;
    }

    /**
     * 设置departSir属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartSir(String value) {
        this.departSir = value;
    }

    /**
     * 获取departTel属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartTel() {
        return departTel;
    }

    /**
     * 设置departTel属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartTel(String value) {
        this.departTel = value;
    }

    /**
     * 获取isEpiboly属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsEpiboly() {
        return isEpiboly;
    }

    /**
     * 设置isEpiboly属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsEpiboly(String value) {
        this.isEpiboly = value;
    }

    /**
     * 获取phone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPhone() {
        return phone;
    }

    /**
     * 设置phone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * 获取charge属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCharge() {
        return charge;
    }

    /**
     * 设置charge属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCharge(String value) {
        this.charge = value;
    }

    /**
     * 获取remark属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRemark() {
        return remark;
    }

    /**
     * 设置remark属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRemark(String value) {
        this.remark = value;
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

}
