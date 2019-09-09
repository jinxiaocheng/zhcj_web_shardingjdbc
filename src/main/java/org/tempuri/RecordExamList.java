
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>RecordExamList complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType name="RecordExamList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="List" type="{http://tempuri.org/}ArrayOfRecordExam" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "RecordExamList", propOrder = {
    "total",
    "list"
})
public class RecordExamList {

    @XmlElement(name = "Total")
    protected int total;
    @XmlElement(name = "List")
    protected ArrayOfRecordExam list;

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
     *     {@link ArrayOfRecordExam }
     *     
     */
    public ArrayOfRecordExam getList() {
        return list;
    }

    /**
     * ����list���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfRecordExam }
     *     
     */
    public void setList(ArrayOfRecordExam value) {
        this.list = value;
    }

}
