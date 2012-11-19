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
 * <p>Java class for containerBase.propertiesBinding complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="containerBase.propertiesBinding">
 *   &lt;complexContent>
 *     &lt;extension base="{http://www.prognoz.ru/Adhoc}obj">
 *       &lt;sequence>
 *         &lt;element name="PropertiesBinding">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;extension base="{http://www.prognoz.ru/Adhoc}obj">
 *                 &lt;sequence>
 *                   &lt;element name="Binding" maxOccurs="unbounded">
 *                     &lt;complexType>
 *                       &lt;complexContent>
 *                         &lt;extension base="{http://www.prognoz.ru/Adhoc}obj">
 *                           &lt;sequence>
 *                           &lt;/sequence>
 *                           &lt;attribute name="PropertyName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="SelectedIndex" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="SourceBlockId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                           &lt;attribute name="SourceProperty" type="{http://www.w3.org/2001/XMLSchema}string" />
 *                         &lt;/extension>
 *                       &lt;/complexContent>
 *                     &lt;/complexType>
 *                   &lt;/element>
 *                 &lt;/sequence>
 *               &lt;/extension>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/extension>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "containerBase.propertiesBinding", propOrder = {
    "propertiesBinding"
})
public class ContainerBasePropertiesBinding
    extends Obj
{

    @XmlElement(name = "PropertiesBinding", required = true)
    protected ContainerBasePropertiesBinding.PropertiesBinding propertiesBinding;

    /**
     * Gets the value of the propertiesBinding property.
     * 
     * @return
     *     possible object is
     *     {@link ContainerBasePropertiesBinding.PropertiesBinding }
     *     
     */
    public ContainerBasePropertiesBinding.PropertiesBinding getPropertiesBinding() {
        return propertiesBinding;
    }

    /**
     * Sets the value of the propertiesBinding property.
     * 
     * @param value
     *     allowed object is
     *     {@link ContainerBasePropertiesBinding.PropertiesBinding }
     *     
     */
    public void setPropertiesBinding(ContainerBasePropertiesBinding.PropertiesBinding value) {
        this.propertiesBinding = value;
    }


    /**
     * <p>Java class for anonymous complex type.
     * 
     * <p>The following schema fragment specifies the expected content contained within this class.
     * 
     * <pre>
     * &lt;complexType>
     *   &lt;complexContent>
     *     &lt;extension base="{http://www.prognoz.ru/Adhoc}obj">
     *       &lt;sequence>
     *         &lt;element name="Binding" maxOccurs="unbounded">
     *           &lt;complexType>
     *             &lt;complexContent>
     *               &lt;extension base="{http://www.prognoz.ru/Adhoc}obj">
     *                 &lt;sequence>
     *                 &lt;/sequence>
     *                 &lt;attribute name="PropertyName" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="SelectedIndex" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="SourceBlockId" type="{http://www.w3.org/2001/XMLSchema}string" />
     *                 &lt;attribute name="SourceProperty" type="{http://www.w3.org/2001/XMLSchema}string" />
     *               &lt;/extension>
     *             &lt;/complexContent>
     *           &lt;/complexType>
     *         &lt;/element>
     *       &lt;/sequence>
     *     &lt;/extension>
     *   &lt;/complexContent>
     * &lt;/complexType>
     * </pre>
     * 
     * 
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
        "binding"
    })
    public static class PropertiesBinding
        extends Obj
    {

        @XmlElement(name = "Binding", required = true)
        protected List<ContainerBasePropertiesBinding.PropertiesBinding.Binding> binding;

        /**
         * Gets the value of the binding property.
         * 
         * <p>
         * This accessor method returns a reference to the live list,
         * not a snapshot. Therefore any modification you make to the
         * returned list will be present inside the JAXB object.
         * This is why there is not a <CODE>set</CODE> method for the binding property.
         * 
         * <p>
         * For example, to add a new item, do as follows:
         * <pre>
         *    getBinding().add(newItem);
         * </pre>
         * 
         * 
         * <p>
         * Objects of the following type(s) are allowed in the list
         * {@link ContainerBasePropertiesBinding.PropertiesBinding.Binding }
         * 
         * 
         */
        public List<ContainerBasePropertiesBinding.PropertiesBinding.Binding> getBinding() {
            if (binding == null) {
                binding = new ArrayList<ContainerBasePropertiesBinding.PropertiesBinding.Binding>();
            }
            return this.binding;
        }


        /**
         * <p>Java class for anonymous complex type.
         * 
         * <p>The following schema fragment specifies the expected content contained within this class.
         * 
         * <pre>
         * &lt;complexType>
         *   &lt;complexContent>
         *     &lt;extension base="{http://www.prognoz.ru/Adhoc}obj">
         *       &lt;sequence>
         *       &lt;/sequence>
         *       &lt;attribute name="PropertyName" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="SelectedIndex" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="SourceBlockId" type="{http://www.w3.org/2001/XMLSchema}string" />
         *       &lt;attribute name="SourceProperty" type="{http://www.w3.org/2001/XMLSchema}string" />
         *     &lt;/extension>
         *   &lt;/complexContent>
         * &lt;/complexType>
         * </pre>
         * 
         * 
         */
        @XmlAccessorType(XmlAccessType.FIELD)
        @XmlType(name = "")
        public static class Binding
            extends Obj
        {

            @XmlAttribute(name = "PropertyName")
            protected String propertyName;
            @XmlAttribute(name = "SelectedIndex")
            protected String selectedIndex;
            @XmlAttribute(name = "SourceBlockId")
            protected String sourceBlockId;
            @XmlAttribute(name = "SourceProperty")
            protected String sourceProperty;

            /**
             * Gets the value of the propertyName property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getPropertyName() {
                return propertyName;
            }

            /**
             * Sets the value of the propertyName property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setPropertyName(String value) {
                this.propertyName = value;
            }

            /**
             * Gets the value of the selectedIndex property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSelectedIndex() {
                return selectedIndex;
            }

            /**
             * Sets the value of the selectedIndex property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSelectedIndex(String value) {
                this.selectedIndex = value;
            }

            /**
             * Gets the value of the sourceBlockId property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSourceBlockId() {
                return sourceBlockId;
            }

            /**
             * Sets the value of the sourceBlockId property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSourceBlockId(String value) {
                this.sourceBlockId = value;
            }

            /**
             * Gets the value of the sourceProperty property.
             * 
             * @return
             *     possible object is
             *     {@link String }
             *     
             */
            public String getSourceProperty() {
                return sourceProperty;
            }

            /**
             * Sets the value of the sourceProperty property.
             * 
             * @param value
             *     allowed object is
             *     {@link String }
             *     
             */
            public void setSourceProperty(String value) {
                this.sourceProperty = value;
            }

        }

    }

}
