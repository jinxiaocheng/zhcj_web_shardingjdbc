
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RecordList complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="RecordList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="List" type="{http://tempuri.org/}ArrayOfRecord" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecordList", propOrder = {
    "total",
    "list"
})
public class RecordList {

    @XmlElement(name = "Total")
    protected int total;
    @XmlElement(name = "List")
    protected ArrayOfRecord list;

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
     *     {@link ArrayOfRecord }
     *     
     */
    public ArrayOfRecord getList() {
        return list;
    }

    /**
     * ����list���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRecord }
     *     
     */
    public void setList(ArrayOfRecord value) {
        this.list = value;
    }

}
