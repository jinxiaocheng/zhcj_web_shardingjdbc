
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
 *         &lt;element name="GetCodeContentByKindNameResult" type="{http://tempuri.org/}ArrayOfCodeContent" minOccurs="0"/&gt;
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
    "getCodeContentByKindNameResult"
})
@XmlRootElement(name = "GetCodeContentByKindNameResponse")
public class GetCodeContentByKindNameResponse {

    @XmlElement(name = "GetCodeContentByKindNameResult")
    protected ArrayOfCodeContent getCodeContentByKindNameResult;

    /**
     * ��ȡgetCodeContentByKindNameResult���Ե�ֵ��
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfCodeContent }
     *     
     */
    public ArrayOfCodeContent getGetCodeContentByKindNameResult() {
        return getCodeContentByKindNameResult;
    }

    /**
     * ����getCodeContentByKindNameResult���Ե�ֵ��
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfCodeContent }
     *     
     */
    public void setGetCodeContentByKindNameResult(ArrayOfCodeContent value) {
        this.getCodeContentByKindNameResult = value;
    }

}
