//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vJAXB 2.1.10 in JDK 6 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.06.21 at 10:39:14 AM EEST 
//


package by.prognoz.exjaxb.test.adhoc;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ds.dsparams complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="ds.dsparams">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="DataSourceParams" type="{http://www.prognoz.ru/Adhoc}ds.p5expressreport.paramlist" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ds.dsparams", propOrder = {
    "dataSourceParams"
})
public class DsDsparams {

    @XmlElement(name = "DataSourceParams")
    protected DsP5ExpressreportParamlist dataSourceParams;

    /**
     * Gets the value of the dataSourceParams property.
     * 
     * @return
     *     possible object is
     *     {@link DsP5ExpressreportParamlist }
     *     
     */
    public DsP5ExpressreportParamlist getDataSourceParams() {
        return dataSourceParams;
    }

    /**
     * Sets the value of the dataSourceParams property.
     * 
     * @param value
     *     allowed object is
     *     {@link DsP5ExpressreportParamlist }
     *     
     */
    public void setDataSourceParams(DsP5ExpressreportParamlist value) {
        this.dataSourceParams = value;
    }

}
