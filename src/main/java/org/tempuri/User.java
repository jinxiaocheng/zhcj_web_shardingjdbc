
package org.tempuri;

import java.math.BigDecimal;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.datatype.XMLGregorianCalendar;


/**
 * <p>User complex type的 Java 类。
 * 
 * <p>以下模式片段指定包含在此类中的预期内容。
 * 
 * <pre>
 * &lt;complexType name="User"&gt;
 *   &lt;complexContent&gt;
 *     &lt;extension base="{http://tempuri.org/}EntityBase"&gt;
 *       &lt;sequence&gt;
 *         &lt;element name="UserName" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UserAccount" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="WorkNum" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PassWord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UserKind" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="UserOrder" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="StopUse" type="{http://www.w3.org/2001/XMLSchema}decimal"/&gt;
 *         &lt;element name="ComputerLimit" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="TelePhone" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="Email" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="DepartID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PicTure" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="PwdLastUpdateTime" type="{http://www.w3.org/2001/XMLSchema}dateTime"/&gt;
 *         &lt;element name="MDPassWord" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="IdentifyId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="State" type="{http://www.w3.org/2001/XMLSchema}int"/&gt;
 *         &lt;element name="TokenID" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="OpenId" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *         &lt;element name="RoleNames" type="{http://www.w3.org/2001/XMLSchema}string" minOccurs="0"/&gt;
 *       &lt;/sequence&gt;
 *     &lt;/extension&gt;
 *   &lt;/complexContent&gt;
 * &lt;/complexType&gt;
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "User", propOrder = {
    "userName",
    "userAccount",
    "workNum",
    "passWord",
    "userKind",
    "userOrder",
    "stopUse",
    "computerLimit",
    "telePhone",
    "email",
    "departID",
    "picTure",
    "pwdLastUpdateTime",
    "mdPassWord",
    "identifyId",
    "state",
    "tokenID",
    "openId",
    "roleNames"
})
public class User
    extends EntityBase
{

    @XmlElement(name = "UserName")
    protected String userName;
    @XmlElement(name = "UserAccount")
    protected String userAccount;
    @XmlElement(name = "WorkNum")
    protected String workNum;
    @XmlElement(name = "PassWord")
    protected String passWord;
    @XmlElement(name = "UserKind")
    protected String userKind;
    @XmlElement(name = "UserOrder", required = true)
    protected BigDecimal userOrder;
    @XmlElement(name = "StopUse", required = true)
    protected BigDecimal stopUse;
    @XmlElement(name = "ComputerLimit")
    protected String computerLimit;
    @XmlElement(name = "TelePhone")
    protected String telePhone;
    @XmlElement(name = "Email")
    protected String email;
    @XmlElement(name = "DepartID")
    protected String departID;
    @XmlElement(name = "PicTure")
    protected String picTure;
    @XmlElement(name = "PwdLastUpdateTime", required = true)
    @XmlSchemaType(name = "dateTime")
    protected XMLGregorianCalendar pwdLastUpdateTime;
    @XmlElement(name = "MDPassWord")
    protected String mdPassWord;
    @XmlElement(name = "IdentifyId")
    protected String identifyId;
    @XmlElement(name = "State")
    protected int state;
    @XmlElement(name = "TokenID")
    protected String tokenID;
    @XmlElement(name = "OpenId")
    protected String openId;
    @XmlElement(name = "RoleNames")
    protected String roleNames;

    /**
     * 获取userName属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserName() {
        return userName;
    }

    /**
     * 设置userName属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserName(String value) {
        this.userName = value;
    }

    /**
     * 获取userAccount属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserAccount() {
        return userAccount;
    }

    /**
     * 设置userAccount属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserAccount(String value) {
        this.userAccount = value;
    }

    /**
     * 获取workNum属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getWorkNum() {
        return workNum;
    }

    /**
     * 设置workNum属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setWorkNum(String value) {
        this.workNum = value;
    }

    /**
     * 获取passWord属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPassWord() {
        return passWord;
    }

    /**
     * 设置passWord属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPassWord(String value) {
        this.passWord = value;
    }

    /**
     * 获取userKind属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUserKind() {
        return userKind;
    }

    /**
     * 设置userKind属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUserKind(String value) {
        this.userKind = value;
    }

    /**
     * 获取userOrder属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getUserOrder() {
        return userOrder;
    }

    /**
     * 设置userOrder属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setUserOrder(BigDecimal value) {
        this.userOrder = value;
    }

    /**
     * 获取stopUse属性的值。
     * 
     * @return
     *     possible object is
     *     {@link BigDecimal }
     *     
     */
    public BigDecimal getStopUse() {
        return stopUse;
    }

    /**
     * 设置stopUse属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link BigDecimal }
     *     
     */
    public void setStopUse(BigDecimal value) {
        this.stopUse = value;
    }

    /**
     * 获取computerLimit属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getComputerLimit() {
        return computerLimit;
    }

    /**
     * 设置computerLimit属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setComputerLimit(String value) {
        this.computerLimit = value;
    }

    /**
     * 获取telePhone属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTelePhone() {
        return telePhone;
    }

    /**
     * 设置telePhone属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTelePhone(String value) {
        this.telePhone = value;
    }

    /**
     * 获取email属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getEmail() {
        return email;
    }

    /**
     * 设置email属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setEmail(String value) {
        this.email = value;
    }

    /**
     * 获取departID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDepartID() {
        return departID;
    }

    /**
     * 设置departID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDepartID(String value) {
        this.departID = value;
    }

    /**
     * 获取picTure属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getPicTure() {
        return picTure;
    }

    /**
     * 设置picTure属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setPicTure(String value) {
        this.picTure = value;
    }

    /**
     * 获取pwdLastUpdateTime属性的值。
     * 
     * @return
     *     possible object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public XMLGregorianCalendar getPwdLastUpdateTime() {
        return pwdLastUpdateTime;
    }

    /**
     * 设置pwdLastUpdateTime属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link XMLGregorianCalendar }
     *     
     */
    public void setPwdLastUpdateTime(XMLGregorianCalendar value) {
        this.pwdLastUpdateTime = value;
    }

    /**
     * 获取mdPassWord属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getMDPassWord() {
        return mdPassWord;
    }

    /**
     * 设置mdPassWord属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setMDPassWord(String value) {
        this.mdPassWord = value;
    }

    /**
     * 获取identifyId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIdentifyId() {
        return identifyId;
    }

    /**
     * 设置identifyId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIdentifyId(String value) {
        this.identifyId = value;
    }

    /**
     * 获取state属性的值。
     * 
     */
    public int getState() {
        return state;
    }

    /**
     * 设置state属性的值。
     * 
     */
    public void setState(int value) {
        this.state = value;
    }

    /**
     * 获取tokenID属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTokenID() {
        return tokenID;
    }

    /**
     * 设置tokenID属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTokenID(String value) {
        this.tokenID = value;
    }

    /**
     * 获取openId属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getOpenId() {
        return openId;
    }

    /**
     * 设置openId属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setOpenId(String value) {
        this.openId = value;
    }

    /**
     * 获取roleNames属性的值。
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getRoleNames() {
        return roleNames;
    }

    /**
     * 设置roleNames属性的值。
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setRoleNames(String value) {
        this.roleNames = value;
    }

}
