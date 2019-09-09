
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
 *         &lt;element name="GetRecordPersonPageResult" type="{http://tempuri.org/}RecordPersonList" minOccurs="0"/&gt;
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
    "getRecordPersonPageResult"
})
@XmlRootElement(name = "GetRecordPersonPageResponse")
public class GetRecordPersonPageResponse {

    @XmlElement(name = "GetRecordPersonPageResult")
    protected RecordPersonList getRecordPersonPageResult;

    /**
     * 获取getRecordPersonPageResult属性的值。
     * 
     * @return
     *     possible object is
     *     {@link RecordPersonList }
     *     
     */
    public RecordPersonList getGetRecordPersonPageResult() {
        return getRecordPersonPageResult;
    }

    /**
     * 设置getRecordPersonPageResult属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link RecordPersonList }
     *     
     */
    public void setGetRecordPersonPageResult(RecordPersonList value) {
        this.getRecordPersonPageResult = value;
    }

}
