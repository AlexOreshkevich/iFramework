//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.21 at 10:39:14 AM EEST 
//


package by.neosoft.exjaxb.test.adhoc;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for radioButton complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="radioButton">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.prognoz.ru/Adhoc}control">
 *       &lt;sequence>
 *         &lt;element name="RadioButton.EventHandlers">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="EventHandlers">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                           &lt;sequence>
 *                             &lt;element name="RadioButtonClickEventHandler" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded"/>
 *                           &lt;/sequence>
 *                         &lt;/restriction>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="Text" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="IsChecked" use="required" type="{http://www.prognoz.ru/Adhoc}bool" />
 *       &lt;attribute name="GroupName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "radioButton", propOrder = {
    "radioButtonEventHandlers"
})
public class RadioButton
    extends Control
{

    @XmlElement(name = "RadioButton.EventHandlers", required = true)
    protected RadioButton.RadioButtonEventHandlers radioButtonEventHandlers;
    @XmlAttribute(name = "Text")
    protected String text;
    @XmlAttribute(name = "IsChecked", required = true)
    protected String isChecked;
    @XmlAttribute(name = "GroupName")
    protected String groupName;

    /**
     * Gets the value of the radioButtonEventHandlers property.
     * 
     * @return
     *     possible object is
     *     {@link RadioButton.RadioButtonEventHandlers }
     *     
     */
    public RadioButton.RadioButtonEventHandlers getRadioButtonEventHandlers() {
        return radioButtonEventHandlers;
    }

    /**
     * Sets the value of the radioButtonEventHandlers property.
     * 
     * @param value
     *     allowed object is
     *     {@link RadioButton.RadioButtonEventHandlers }
     *     
     */
    public void setRadioButtonEventHandlers(RadioButton.RadioButtonEventHandlers value) {
        this.radioButtonEventHandlers = value;
    }

    /**
     * Gets the value of the text property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getText() {
        return text;
    }

    /**
     * Sets the value of the text property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setText(String value) {
        this.text = value;
    }

    /**
     * Gets the value of the isChecked property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getIsChecked() {
        return isChecked;
    }

    /**
     * Sets the value of the isChecked property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setIsChecked(String value) {
        this.isChecked = value;
    }

    /**
     * Gets the value of the groupName property.
     * 
     * @return
     *     possible object is
     *     {@link String }
     *     
     */
    public String getGroupName() {
        return groupName;
    }

    /**
     * Sets the value of the groupName property.
     * 
     * @param value
     *     allowed object is
     *     {@link String }
     *     
     */
    public void setGroupName(String value) {
        this.groupName = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *       &lt;sequence>
     *         &lt;element name="EventHandlers">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
     *                 &lt;sequence>
     *                   &lt;element name="RadioButtonClickEventHandler" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded"/>
     *                 &lt;/sequence>
     *               &lt;/restriction>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/restriction>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "eventHandlers"
    })
    public static class RadioButtonEventHandlers {

        @XmlElement(name = "EventHandlers", required = true)
        protected RadioButton.RadioButtonEventHandlers.EventHandlers eventHandlers;

        /**
         * Gets the value of the eventHandlers property.
         * 
         * @return
         *     possible object is
         *     {@link RadioButton.RadioButtonEventHandlers.EventHandlers }
         *     
         */
        public RadioButton.RadioButtonEventHandlers.EventHandlers getEventHandlers() {
            return eventHandlers;
        }

        /**
         * Sets the value of the eventHandlers property.
         * 
         * @param value
         *     allowed object is
         *     {@link RadioButton.RadioButtonEventHandlers.EventHandlers }
         *     
         */
        public void setEventHandlers(RadioButton.RadioButtonEventHandlers.EventHandlers value) {
            this.eventHandlers = value;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
         *       &lt;sequence>
         *         &lt;element name="RadioButtonClickEventHandler" type="{http://www.w3.org/2001/XMLSchema}anyType" maxOccurs="unbounded"/>
         *       &lt;/sequence>
         *     &lt;/restriction>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "", propOrder = {
            "radioButtonClickEventHandler"
        })
        public static class EventHandlers {

            @XmlElement(name = "RadioButtonClickEventHandler", required = true)
            protected List<Object> radioButtonClickEventHandler;

            /**
             * Gets the value of the radioButtonClickEventHandler property.
             * 
             * <p>
             * This accessor method returns a reference to the live list,
             * not a snapshot. Therefore any modification you make to the
             * returned list will be present inside the JAXB object.
             * This is why there is not a <CODE>set</CODE> method for the radioButtonClickEventHandler property.
             * 
             * <p>
             * For example, to add a new item, do as follows:
             * <pre>
             *    getRadioButtonClickEventHandler().add(newItem);
             * </pre>
             * 
             * 
             * <p>
             * Objects of the following type(s) are allowed in the list
             * {@link Object }
             * 
             * 
             */
            public List<Object> getRadioButtonClickEventHandler() {
                if (radioButtonClickEventHandler == null) {
                    radioButtonClickEventHandler = new ArrayList<Object>();
                }
                return this.radioButtonClickEventHandler;
            }

        }

    }

}
