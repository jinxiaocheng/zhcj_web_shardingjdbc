
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
 *         &lt;element name="GetRootProjectResult" type="{http://tempuri.org/}ArrayOfDepart" minOccurs="0"/&gt;
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
    "getRootProjectResult"
})
@XmlRootElement(name = "GetRootProjectResponse")
public class GetRootProjectResponse {

    @XmlElement(name = "GetRootProjectResult")
    protected ArrayOfDepart getRootProjectResult;

    /**
     * ��ȡgetRootProjectResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDepart }
     *     
     */
    public ArrayOfDepart getGetRootProjectResult() {
        return getRootProjectResult;
    }

    /**
     * ����getRootProjectResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDepart }
     *     
     */
    public void setGetRootProjectResult(ArrayOfDepart value) {
        this.getRootProjectResult = value;
    }

}
