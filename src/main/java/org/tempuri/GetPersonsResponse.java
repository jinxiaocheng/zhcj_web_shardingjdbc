
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
 *         &lt;element name="GetPersonsResult" type="{http://tempuri.org/}PersonList" minOccurs="0"/&gt;
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
    "getPersonsResult"
})
@XmlRootElement(name = "GetPersonsResponse")
public class GetPersonsResponse {

    @XmlElement(name = "GetPersonsResult")
    protected PersonList getPersonsResult;

    /**
     * ��ȡgetPersonsResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link PersonList }
     *     
     */
    public PersonList getGetPersonsResult() {
        return getPersonsResult;
    }

    /**
     * ����getPersonsResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link PersonList }
     *     
     */
    public void setGetPersonsResult(PersonList value) {
        this.getPersonsResult = value;
    }

}
