
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;
import java.math.BigDecimal;


/**
 * <p>Depart complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="Depart"&gt;
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
 *         &lt;element name="ItemType" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AreaCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AreaID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Finished" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="ItemTypeList" type="{http://tempuri.org/}ArrayOfCodeContent" minOccurs="0"/&gt;
 *         &lt;element name="PageResId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="CreditCode" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "Depart", propOrder = {
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
        "itemType",
        "areaCode",
        "areaID",
        "finished",
        "itemTypeList",
        "pageResId",
        "creditCode",
        "pId",
        "name"
})
public class Depart
        extends EntityBase {


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
    @XmlElement(name = "ItemType")
    protected String itemType;
    @XmlElement(name = "AreaCode")
    protected String areaCode;
    @XmlElement(name = "AreaID")
    protected String areaID;
    @XmlElement(name = "Finished")
    protected int finished;
    @XmlElement(name = "ItemTypeList")
    protected ArrayOfCodeContent itemTypeList;
    @XmlElement(name = "PageResId")
    protected String pageResId;
    @XmlElement(name = "CreditCode")
    protected String creditCode;
    @XmlElement(name = "Pid")
    protected String pId;
    @XmlElement(name = "name")
    protected String name;

    /**
     * ��ȡparentID���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getParentID() {
        return parentID;
    }

    /**
     * ����parentID���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setParentID(String value) {
        this.parentID = value;
    }

    /**
     * ��ȡdepartCode���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getDepartCode() {
        return departCode;
    }

    /**
     * ����departCode���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDepartCode(String value) {
        this.departCode = value;
    }

    /**
     * ��ȡparentCode���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getParentCode() {
        return parentCode;
    }

    /**
     * ����parentCode���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setParentCode(String value) {
        this.parentCode = value;
    }

    /**
     * ��ȡdepartName���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getDepartName() {
        return departName;
    }

    /**
     * ����departName���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDepartName(String value) {
        this.departName = value;
    }

    /**
     * ��ȡdepartType���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getDepartType() {
        return departType;
    }

    /**
     * ����departType���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDepartType(String value) {
        this.departType = value;
    }

    /**
     * ��ȡdepartShortName���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getDepartShortName() {
        return departShortName;
    }

    /**
     * ����departShortName���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDepartShortName(String value) {
        this.departShortName = value;
    }

    /**
     * ��ȡdepartOrder���Ե�ֵ��
     *
     * @return possible object is
     * {@link BigDecimal }
     */
    public BigDecimal getDepartOrder() {
        return departOrder;
    }

    /**
     * ����departOrder���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link BigDecimal }
     */
    public void setDepartOrder(BigDecimal value) {
        this.departOrder = value;
    }

    /**
     * ��ȡstate���Ե�ֵ��
     */
    public int getState() {
        return state;
    }

    /**
     * ����state���Ե�ֵ��
     */
    public void setState(int value) {
        this.state = value;
    }

    /**
     * ��ȡdepartSir���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getDepartSir() {
        return departSir;
    }

    /**
     * ����departSir���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDepartSir(String value) {
        this.departSir = value;
    }

    /**
     * ��ȡdepartTel���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getDepartTel() {
        return departTel;
    }

    /**
     * ����departTel���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDepartTel(String value) {
        this.departTel = value;
    }

    /**
     * ��ȡisEpiboly���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getIsEpiboly() {
        return isEpiboly;
    }

    /**
     * ����isEpiboly���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setIsEpiboly(String value) {
        this.isEpiboly = value;
    }

    /**
     * ��ȡphone���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getPhone() {
        return phone;
    }

    /**
     * ����phone���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPhone(String value) {
        this.phone = value;
    }

    /**
     * ��ȡcharge���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getCharge() {
        return charge;
    }

    /**
     * ����charge���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCharge(String value) {
        this.charge = value;
    }

    /**
     * ��ȡremark���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getRemark() {
        return remark;
    }

    /**
     * ����remark���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRemark(String value) {
        this.remark = value;
    }

    /**
     * ��ȡitemType���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getItemType() {
        return itemType;
    }

    /**
     * ����itemType���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setItemType(String value) {
        this.itemType = value;
    }

    /**
     * ��ȡareaCode���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getAreaCode() {
        return areaCode;
    }

    /**
     * ����areaCode���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAreaCode(String value) {
        this.areaCode = value;
    }

    /**
     * ��ȡareaID���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getAreaID() {
        return areaID;
    }

    /**
     * ����areaID���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAreaID(String value) {
        this.areaID = value;
    }

    /**
     * ��ȡfinished���Ե�ֵ��
     */
    public int getFinished() {
        return finished;
    }

    /**
     * ����finished���Ե�ֵ��
     */
    public void setFinished(int value) {
        this.finished = value;
    }

    /**
     * ��ȡitemTypeList���Ե�ֵ��
     *
     * @return possible object is
     * {@link ArrayOfCodeContent }
     */
    public ArrayOfCodeContent getItemTypeList() {
        return itemTypeList;
    }

    /**
     * ����itemTypeList���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link ArrayOfCodeContent }
     */
    public void setItemTypeList(ArrayOfCodeContent value) {
        this.itemTypeList = value;
    }

    /**
     * ��ȡpageResId���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getPageResId() {
        return pageResId;
    }

    /**
     * ����pageResId���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPageResId(String value) {
        this.pageResId = value;
    }

    /**
     * ��ȡcreditCode���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getCreditCode() {
        return creditCode;
    }

    /**
     * ����creditCode���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCreditCode(String value) {
        this.creditCode = value;
    }

    public String getpId() {
        return pId;
    }

    public void setpId(String pId) {
        this.pId = pId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
