
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
 *         &lt;element name="GetPersonRecordByIdentiyIdResult" type="{http://tempuri.org/}ArrayOfPersonRecord" minOccurs="0"/&gt;
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
    "getPersonRecordByIdentiyIdResult"
})
@XmlRootElement(name = "GetPersonRecordByIdentiyIdResponse")
public class GetPersonRecordByIdentiyIdResponse {

    @XmlElement(name = "GetPersonRecordByIdentiyIdResult")
    protected ArrayOfPersonRecord getPersonRecordByIdentiyIdResult;

    /**
     * ��ȡgetPersonRecordByIdentiyIdResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPersonRecord }
     *     
     */
    public ArrayOfPersonRecord getGetPersonRecordByIdentiyIdResult() {
        return getPersonRecordByIdentiyIdResult;
    }

    /**
     * ����getPersonRecordByIdentiyIdResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPersonRecord }
     *     
     */
    public void setGetPersonRecordByIdentiyIdResult(ArrayOfPersonRecord value) {
        this.getPersonRecordByIdentiyIdResult = value;
    }

}
