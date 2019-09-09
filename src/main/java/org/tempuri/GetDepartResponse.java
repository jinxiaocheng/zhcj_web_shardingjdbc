
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
 *         &lt;element name="GetDepartResult" type="{http://tempuri.org/}Depart" minOccurs="0"/&gt;
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
    "getDepartResult"
})
@XmlRootElement(name = "GetDepartResponse")
public class GetDepartResponse {

    @XmlElement(name = "GetDepartResult")
    protected Depart getDepartResult;

    /**
     * 获取getDepartResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link Depart }
     *     
     */
    public Depart getGetDepartResult() {
        return getDepartResult;
    }

    /**
     * 设置getDepartResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link Depart }
     *     
     */
    public void setGetDepartResult(Depart value) {
        this.getDepartResult = value;
    }

}
