
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
 *         &lt;element name="queryParams" type="{http://tempuri.org/}ArrayOfDictionaryEntry" minOccurs="0"/&gt;
 *         &lt;element name="page" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="resultsPerpage" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
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
    "queryParams",
    "page",
    "resultsPerpage"
})
@XmlRootElement(name = "GetRecordPage")
public class GetRecordPage {

    protected ArrayOfDictionaryEntry queryParams;
    protected int page;
    protected int resultsPerpage;

    /**
     * 获取queryParams属性的值。
     * 
     * @return
     *     possible object is
     *     {@link ArrayOfDictionaryEntry }
     *     
     */
    public ArrayOfDictionaryEntry getQueryParams() {
        return queryParams;
    }

    /**
     * 设置queryParams属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link ArrayOfDictionaryEntry }
     *     
     */
    public void setQueryParams(ArrayOfDictionaryEntry value) {
        this.queryParams = value;
    }

    /**
     * 获取page属性的值。
     * 
     */
    public int getPage() {
        return page;
    }

    /**
     * 设置page属性的值。
     * 
     */
    public void setPage(int value) {
        this.page = value;
    }

    /**
     * 获取resultsPerpage属性的值。
     * 
     */
    public int getResultsPerpage() {
        return resultsPerpage;
    }

    /**
     * 设置resultsPerpage属性的值。
     * 
     */
    public void setResultsPerpage(int value) {
        this.resultsPerpage = value;
    }

}
