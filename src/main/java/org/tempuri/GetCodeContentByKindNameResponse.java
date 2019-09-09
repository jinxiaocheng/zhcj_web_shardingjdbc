
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
     * 获取getCodeContentByKindNameResult属性的值。
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
     * 设置getCodeContentByKindNameResult属性的值。
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
