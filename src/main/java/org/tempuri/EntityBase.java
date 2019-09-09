
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>EntityBase complex type�� Java �ࡣ
 *
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 *
 * <pre>
 * &lt;complexType name="EntityBase"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="ID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="AutoID" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="CreateDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="CreateUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OperDate" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="OperUser" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "EntityBase", propOrder = {
        "id",
        "autoID",
        "createDate",
        "createUser",
        "operDate",
        "operUser",
        "rowNum",
        "page"
})
@XmlSeeAlso({
        Depart.class,
        PersonInfo.class,
        Record.class,
        RecordPerson.class,
        RecordExam.class,
        CodeContent.class,
        User.class,
        ProjectDepart.class
})
public abstract class EntityBase {

    @XmlElement(name = "ID")
    protected String id;
    @XmlElement(name = "AutoID")
    protected int autoID;
    @XmlElement(name = "CreateDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar createDate;
    @XmlElement(name = "CreateUser")
    protected String createUser;
    @XmlElement(name = "OperDate", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar operDate;
    @XmlElement(name = "OperUser")
    protected String operUser;
    @XmlElement(name = "RowNum")
    private Integer rowNum;
    @XmlElement(name = "PAGE")
    private Integer page;


    /**
     * ��ȡid���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getID() {
        return id;
    }

    /**
     * ����id���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setID(String value) {
        this.id = value;
    }

    /**
     * ��ȡautoID���Ե�ֵ��
     */
    public int getAutoID() {
        return autoID;
    }

    /**
     * ����autoID���Ե�ֵ��
     */
    public void setAutoID(int value) {
        this.autoID = value;
    }

    /**
     * ��ȡcreateDate���Ե�ֵ��
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getCreateDate() {
        return createDate;
    }

    /**
     * ����createDate���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setCreateDate(XMLGregorianCalendar value) {
        this.createDate = value;
    }

    /**
     * ��ȡcreateUser���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getCreateUser() {
        return createUser;
    }

    /**
     * ����createUser���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCreateUser(String value) {
        this.createUser = value;
    }

    /**
     * ��ȡoperDate���Ե�ֵ��
     *
     * @return possible object is
     * {@link XMLGregorianCalendar }
     */
    public XMLGregorianCalendar getOperDate() {
        return operDate;
    }

    /**
     * ����operDate���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link XMLGregorianCalendar }
     */
    public void setOperDate(XMLGregorianCalendar value) {
        this.operDate = value;
    }

    /**
     * ��ȡoperUser���Ե�ֵ��
     *
     * @return possible object is
     * {@link String }
     */
    public String getOperUser() {
        return operUser;
    }

    /**
     * ����operUser���Ե�ֵ��
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setOperUser(String value) {
        this.operUser = value;
    }

    public Integer getRowNum() {
        return rowNum;
    }

    public void setRowNum(Integer rowNum) {
        this.rowNum = rowNum;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }
}
