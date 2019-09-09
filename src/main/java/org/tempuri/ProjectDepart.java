
package org.tempuri;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>ProjectDepart complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
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
     * ��ȡparentID���Ե�ֵ��
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
     * ����parentID���Ե�ֵ��
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
     * ��ȡparentCode���Ե�ֵ��
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
     * ����parentCode���Ե�ֵ��
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
     * ��ȡdepartName���Ե�ֵ��
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
     * ����departName���Ե�ֵ��
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
     * ��ȡdepartType���Ե�ֵ��
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
     * ����departType���Ե�ֵ��
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
     * ��ȡdepartShortName���Ե�ֵ��
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
     * ����departShortName���Ե�ֵ��
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
     * ��ȡdepartOrder���Ե�ֵ��
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
     * ����departOrder���Ե�ֵ��
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
     * ��ȡstate���Ե�ֵ��
     * 
     */
    public int getState() {
        return state;
    }

    /**
     * ����state���Ե�ֵ��
     * 
     */
    public void setState(int value) {
        this.state = value;
    }

    /**
     * ��ȡdepartSir���Ե�ֵ��
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
     * ����departSir���Ե�ֵ��
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
     * ��ȡdepartTel���Ե�ֵ��
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
     * ����departTel���Ե�ֵ��
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
     * ��ȡisEpiboly���Ե�ֵ��
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
     * ����isEpiboly���Ե�ֵ��
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
     * ��ȡphone���Ե�ֵ��
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
     * ����phone���Ե�ֵ��
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
     * ��ȡcharge���Ե�ֵ��
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
     * ����charge���Ե�ֵ��
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
     * ��ȡremark���Ե�ֵ��
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
     * ����remark���Ե�ֵ��
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

}
