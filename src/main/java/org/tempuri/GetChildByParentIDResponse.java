
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
 *         &lt;element name="GetChildByParentIDResult" type="{http://tempuri.org/}ArrayOfProjectDepart" minOccurs="0"/&gt;
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
    "getChildByParentIDResult"
})
@XmlRootElement(name = "GetChildByParentIDResponse")
public class GetChildByParentIDResponse {

    @XmlElement(name = "GetChildByParentIDResult")
    protected ArrayOfProjectDepart getChildByParentIDResult;

    /**
     * ��ȡgetChildByParentIDResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfProjectDepart }
     *     
     */
    public ArrayOfProjectDepart getGetChildByParentIDResult() {
        return getChildByParentIDResult;
    }

    /**
     * ����getChildByParentIDResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfProjectDepart }
     *     
     */
    public void setGetChildByParentIDResult(ArrayOfProjectDepart value) {
        this.getChildByParentIDResult = value;
    }

}
