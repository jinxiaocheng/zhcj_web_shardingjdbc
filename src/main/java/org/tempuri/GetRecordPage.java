
package org.tempuri;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
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
     * ��ȡqueryParams���Ե�ֵ��
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
     * ����queryParams���Ե�ֵ��
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
     * ��ȡpage���Ե�ֵ��
     * 
     */
    public int getPage() {
        return page;
    }

    /**
     * ����page���Ե�ֵ��
     * 
     */
    public void setPage(int value) {
        this.page = value;
    }

    /**
     * ��ȡresultsPerpage���Ե�ֵ��
     * 
     */
    public int getResultsPerpage() {
        return resultsPerpage;
    }

    /**
     * ����resultsPerpage���Ե�ֵ��
     * 
     */
    public void setResultsPerpage(int value) {
        this.resultsPerpage = value;
    }

}
