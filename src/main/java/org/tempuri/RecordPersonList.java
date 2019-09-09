
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RecordPersonList complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="RecordPersonList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="List" type="{http://tempuri.org/}ArrayOfRecordPerson" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecordPersonList", propOrder = {
    "total",
    "list"
})
public class RecordPersonList {

    @XmlElement(name = "Total")
    protected int total;
    @XmlElement(name = "List")
    protected ArrayOfRecordPerson list;

    /**
     * ��ȡtotal���Ե�ֵ��
     * 
     */
    public int getTotal() {
        return total;
    }

    /**
     * ����total���Ե�ֵ��
     * 
     */
    public void setTotal(int value) {
        this.total = value;
    }

    /**
     * ��ȡlist���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfRecordPerson }
     *     
     */
    public ArrayOfRecordPerson getList() {
        return list;
    }

    /**
     * ����list���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRecordPerson }
     *     
     */
    public void setList(ArrayOfRecordPerson value) {
        this.list = value;
    }

}
