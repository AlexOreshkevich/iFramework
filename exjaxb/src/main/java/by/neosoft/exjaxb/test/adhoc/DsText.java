//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.21 at 10:39:14 AM EEST 
//


package by.neosoft.exjaxb.test.adhoc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ds.text complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ds.text">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.prognoz.ru/Adhoc}ds.generic">
 *       &lt;sequence>
 *         &lt;element name="TextDataSource.Text" type="{http://www.w3.org/2001/XMLSchema}string"/>
 *       &lt;/sequence>
 *       &lt;attribute name="Name" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="Description" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ds.text", propOrder = {
    "textDataSourceText"
})
public class DsText
    extends DsGeneric
{

    @XmlElement(name = "TextDataSource.Text", required = true)
    protected String textDataSourceText;
    @XmlAttribute(name = "Name")
    protected String name;
    @XmlAttribute(name = "Description")
    protected String description;

    /**
     * Gets the value of the textDataSourceText property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getTextDataSourceText() {
        return textDataSourceText;
    }

    /**
     * Sets the value of the textDataSourceText property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setTextDataSourceText(String value) {
        this.textDataSourceText = value;
    }

    /**
     * Gets the value of the name property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the value of the name property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * Gets the value of the description property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the value of the description property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setDescription(String value) {
        this.description = value;
    }

}
