//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.21 at 10:39:14 AM EEST 
//


package by.prognoz.exjaxb.test.adhoc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for obstyle complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="obstyle">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.prognoz.ru/Adhoc}obj">
 *       &lt;attribute name="AppliedTo" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BackgroundColor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="CaptionBackgroundColor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="BodyBackgroundColor" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Underline" type="{http://www.prognoz.ru/Adhoc}bool" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "obstyle")
public class Obstyle
    extends Obj
{

    @XmlAttribute(name = "AppliedTo", required = true)
    protected String appliedTo;
    @XmlAttribute(name = "BackgroundColor")
    protected String backgroundColor;
    @XmlAttribute(name = "CaptionBackgroundColor")
    protected String captionBackgroundColor;
    @XmlAttribute(name = "BodyBackgroundColor")
    protected String bodyBackgroundColor;
    @XmlAttribute(name = "Underline")
    protected String underline;

    /**
     * Gets the value of the appliedTo property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getAppliedTo() {
        return appliedTo;
    }

    /**
     * Sets the value of the appliedTo property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setAppliedTo(String value) {
        this.appliedTo = value;
    }

    /**
     * Gets the value of the backgroundColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBackgroundColor() {
        return backgroundColor;
    }

    /**
     * Sets the value of the backgroundColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBackgroundColor(String value) {
        this.backgroundColor = value;
    }

    /**
     * Gets the value of the captionBackgroundColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getCaptionBackgroundColor() {
        return captionBackgroundColor;
    }

    /**
     * Sets the value of the captionBackgroundColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setCaptionBackgroundColor(String value) {
        this.captionBackgroundColor = value;
    }

    /**
     * Gets the value of the bodyBackgroundColor property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getBodyBackgroundColor() {
        return bodyBackgroundColor;
    }

    /**
     * Sets the value of the bodyBackgroundColor property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setBodyBackgroundColor(String value) {
        this.bodyBackgroundColor = value;
    }

    /**
     * Gets the value of the underline property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getUnderline() {
        return underline;
    }

    /**
     * Sets the value of the underline property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setUnderline(String value) {
        this.underline = value;
    }

}
