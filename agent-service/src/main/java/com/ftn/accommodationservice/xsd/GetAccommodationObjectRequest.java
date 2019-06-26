//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.25 at 05:43:39 PM CEST 


package com.ftn.accommodationservice.xsd;

import java.math.BigInteger;
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
 *         &lt;element name="id" type="{http://ftn.com/accommodationservice/xsd}id"/>
 *         &lt;element name="name" type="{http://ftn.com/accommodationservice/xsd}name"/>
 *         &lt;element name="address" type="{http://ftn.com/accommodationservice/xsd}address"/>
 *         &lt;element name="description" type="{http://ftn.com/accommodationservice/xsd}description"/>
 *         &lt;element name="category" type="{http://ftn.com/accommodationservice/xsd}Category"/>
 *         &lt;element name="freeCancelation" type="{http://ftn.com/accommodationservice/xsd}freeCancelation"/>
 *         &lt;element name="daysToCancel" type="{http://ftn.com/accommodationservice/xsd}daysToCancel"/>
 *         &lt;element name="type" type="{http://ftn.com/accommodationservice/xsd}Type"/>
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
    "id",
    "name",
    "address",
    "description",
    "category",
    "freeCancelation",
    "daysToCancel",
    "type"
})
@XmlRootElement(name = "GetAccommodationObjectRequest")
public class GetAccommodationObjectRequest {

    protected long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)
    protected Address address;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected Category category;
    protected boolean freeCancelation;
    @XmlElement(required = true)
    protected BigInteger daysToCancel;
    @XmlElement(required = true)
    protected Type type;

    /**
     * Gets the value of the id property.
     * 
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the value of the id property.
     * 
     */
    public void setId(long value) {
        this.id = value;
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
     * Gets the value of the address property.
     * 
     * @return
     *     possible object is
     *     {@link Address }
     *     
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddress(Address value) {
        this.address = value;
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

    /**
     * Gets the value of the category property.
     * 
     * @return
     *     possible object is
     *     {@link Category }
     *     
     */
    public Category getCategory() {
        return category;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategory(Category value) {
        this.category = value;
    }

    /**
     * Gets the value of the freeCancelation property.
     * 
     */
    public boolean isFreeCancelation() {
        return freeCancelation;
    }

    /**
     * Sets the value of the freeCancelation property.
     * 
     */
    public void setFreeCancelation(boolean value) {
        this.freeCancelation = value;
    }

    /**
     * Gets the value of the daysToCancel property.
     * 
     * @return
     *     possible object is
     *     {@link BigInteger }
     *     
     */
    public BigInteger getDaysToCancel() {
        return daysToCancel;
    }

    /**
     * Sets the value of the daysToCancel property.
     * 
     * @param value
     *     allowed object is
     *     {@link BigInteger }
     *     
     */
    public void setDaysToCancel(BigInteger value) {
        this.daysToCancel = value;
    }

    /**
     * Gets the value of the type property.
     * 
     * @return
     *     possible object is
     *     {@link Type }
     *     
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setType(Type value) {
        this.type = value;
    }

}
