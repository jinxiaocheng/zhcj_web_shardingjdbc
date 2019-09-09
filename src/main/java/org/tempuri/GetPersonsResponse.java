
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
     * 获取getPersonsResult属性的值。
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
     * 设置getPersonsResult属性的值。
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
