
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>PersonList complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="PersonList"&gt;
 *   &lt;complexContent&gt;
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="Total" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="List" type="{http://tempuri.org/}ArrayOfPersonInfo" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/restriction&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "PersonList", propOrder = {
    "total",
    "list"
})
public class PersonList {

    @XmlElement(name = "Total")
    protected int total;
    @XmlElement(name = "List")
    protected ArrayOfPersonInfo list;

    /**
     * 获取total属性的值。
     * 
     */
    public int getTotal() {
        return total;
    }

    /**
     * 设置total属性的值。
     * 
     */
    public void setTotal(int value) {
        this.total = value;
    }

    /**
     * 获取list属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfPersonInfo }
     *     
     */
    public ArrayOfPersonInfo getList() {
        return list;
    }

    /**
     * 设置list属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfPersonInfo }
     *     
     */
    public void setList(ArrayOfPersonInfo value) {
        this.list = value;
    }

}
