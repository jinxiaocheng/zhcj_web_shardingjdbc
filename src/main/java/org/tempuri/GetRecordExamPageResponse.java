
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
 *         &lt;element name="GetRecordExamPageResult" type="{http://tempuri.org/}RecordExamList" minOccurs="0"/&gt;
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
    "getRecordExamPageResult"
})
@XmlRootElement(name = "GetRecordExamPageResponse")
public class GetRecordExamPageResponse {

    @XmlElement(name = "GetRecordExamPageResult")
    protected RecordExamList getRecordExamPageResult;

    /**
     * ��ȡgetRecordExamPageResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link RecordExamList }
     *     
     */
    public RecordExamList getGetRecordExamPageResult() {
        return getRecordExamPageResult;
    }

    /**
     * ����getRecordExamPageResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link RecordExamList }
     *     
     */
    public void setGetRecordExamPageResult(RecordExamList value) {
        this.getRecordExamPageResult = value;
    }

}
