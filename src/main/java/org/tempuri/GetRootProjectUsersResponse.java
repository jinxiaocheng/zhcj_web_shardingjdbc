
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
 *         &lt;element name="GetRootProjectUsersResult" type="{http://tempuri.org/}ArrayOfUser" minOccurs="0"/&gt;
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
    "getRootProjectUsersResult"
})
@XmlRootElement(name = "GetRootProjectUsersResponse")
public class GetRootProjectUsersResponse {

    @XmlElement(name = "GetRootProjectUsersResult")
    protected ArrayOfUser getRootProjectUsersResult;

    /**
     * ��ȡgetRootProjectUsersResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfUser }
     *     
     */
    public ArrayOfUser getGetRootProjectUsersResult() {
        return getRootProjectUsersResult;
    }

    /**
     * ����getRootProjectUsersResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfUser }
     *     
     */
    public void setGetRootProjectUsersResult(ArrayOfUser value) {
        this.getRootProjectUsersResult = value;
    }

}
