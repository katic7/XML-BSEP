//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.25 at 05:39:56 PM CEST 
//


package com.ftn.accommodationservice.xsd;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


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
 *         &lt;element name="AccUnitPrice" type="{http://ftn.com/accommodationservice/xsd}AccUnitPrice"/>
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
    "accUnitPrice"
})
@XmlRootElement(name = "PostAccUnitPriceResponse")
public class PostAccUnitPriceResponse {

    @XmlElement(name = "AccUnitPrice", required = true)
    protected AccUnitPrice accUnitPrice;

    /**
     * Gets the value of the accUnitPrice property.
     * 
     * @return
     *     possible object is
     *     {@link AccUnitPrice }
     *     
     */
    public AccUnitPrice getAccUnitPrice() {
        return accUnitPrice;
    }

    /**
     * Sets the value of the accUnitPrice property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccUnitPrice }
     *     
     */
    public void setAccUnitPrice(AccUnitPrice value) {
        this.accUnitPrice = value;
    }

}