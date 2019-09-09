
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>anonymous complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="GetPersonPictureResult" type="{http://www.w3.org/2001/XMLSchema}base64Binary" minOccurs="0"/&gt;
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
    "getPersonPictureResult"
})
@XmlRootElement(name = "GetPersonPictureResponse")
public class GetPersonPictureResponse {

    @XmlElement(name = "GetPersonPictureResult")
    protected byte[] getPersonPictureResult;

    /**
     * 获取getPersonPictureResult属性的值。
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getGetPersonPictureResult() {
        return getPersonPictureResult;
    }

    /**
     * 设置getPersonPictureResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setGetPersonPictureResult(byte[] value) {
        this.getPersonPictureResult = value;
    }

}
