
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type�� Java �ࡣ
 * 
 * <p>����ģʽƬ��ָ�������ڴ����е�Ԥ�����ݡ�
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GetRecordPageResult" type="{http://tempuri.org/}RecordList" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
    "getRecordPageResult"
})
@XmlRootElement(name = "GetRecordPageResponse")
public class GetRecordPageResponse {

    @XmlElement(name = "GetRecordPageResult")
    protected RecordList getRecordPageResult;

    /**
     * ��ȡgetRecordPageResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link RecordList }
     *     
     */
    public RecordList getGetRecordPageResult() {
        return getRecordPageResult;
    }

    /**
     * ����getRecordPageResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link RecordList }
     *     
     */
    public void setGetRecordPageResult(RecordList value) {
        this.getRecordPageResult = value;
    }

}
