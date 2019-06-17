//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.13 at 07:12:27 PM CEST 
//


package com.ftn.agentservice.model;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;

import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * <p>Java class for AccommodationObject complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccommodationObject">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{www.ftn.com/accommodationservice/model}id"/>
 *         &lt;element name="name" type="{www.ftn.com/accommodationservice/model}name"/>
 *         &lt;element name="address" type="{www.ftn.com/accommodationservice/model}address"/>
 *         &lt;element name="description" type="{www.ftn.com/accommodationservice/model}description"/>
 *         &lt;element name="category" type="{www.ftn.com/accommodationservice/model}Category"/>
 *         &lt;element name="freeCancelation" type="{www.ftn.com/accommodationservice/model}freeCancelation"/>
 *         &lt;element name="daysToCancel" type="{www.ftn.com/accommodationservice/model}daysToCancel"/>
 *         &lt;element name="type" type="{www.ftn.com/accommodationservice/model}Type"/>
 *         &lt;element name="accommodationsUnitList" type="{www.ftn.com/accommodationservice/model}AccommodationUnit" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="additionalServices" type="{www.ftn.com/accommodationservice/model}AdditionalService" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccommodationObject", propOrder = {
    "id",
    "name",
    "addressId",
    "description",
    "categoryId",
    "freeCancelation",
    "daysToCancel",
    "typeId",
    "accommodationsUnitList",
    "additionalServices"
})

@Entity
public class AccommodationObject {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    @XmlElement(required = true)
    protected String name;
    @XmlElement(required = true)    
    protected Long addressId;
    @XmlElement(required = true)
    protected String description;
    @XmlElement(required = true)
    protected Long categoryId;
    protected boolean freeCancelation;
    @XmlElement(required = true)
    protected BigInteger daysToCancel;
    @XmlElement(required = true)    
    protected Long typeId;
    @OneToMany(mappedBy = "accommodationObject")
    protected List<AccommodationUnit> accommodationsUnitList;
    @OneToMany(mappedBy = "accommodationObject")
    protected List<AdditionalService> additionalServices;

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
    public Long getAddressId() {
        return addressId;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value
     *     allowed object is
     *     {@link Address }
     *     
     */
    public void setAddressId(Long value) {
        this.addressId = value;
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
    public Long getCategoryId() {
        return categoryId;
    }

    /**
     * Sets the value of the category property.
     * 
     * @param value
     *     allowed object is
     *     {@link Category }
     *     
     */
    public void setCategoryId(Long value) {
        this.categoryId = value;
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
    public Long getTypeId() {
        return typeId;
    }

    /**
     * Sets the value of the type property.
     * 
     * @param value
     *     allowed object is
     *     {@link Type }
     *     
     */
    public void setTypeId(Long value) {
        this.typeId = value;
    }

    /**
     * Gets the value of the accommodationsUnitList property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the accommodationsUnitList property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAccommodationsUnitList().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AccommodationUnit }
     * 
     * 
     */
    @JsonIgnore
    public List<AccommodationUnit> getAccommodationsUnitList() {
        if (accommodationsUnitList == null) {
            accommodationsUnitList = new ArrayList<AccommodationUnit>();
        }
        return this.accommodationsUnitList;
    }

    /**
     * Gets the value of the additionalServices property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the additionalServices property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAdditionalServices().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link AdditionalService }
     * 
     * 
     */
    @JsonIgnore
    public List<AdditionalService> getAdditionalServices() {
        if (additionalServices == null) {
            additionalServices = new ArrayList<AdditionalService>();
        }
        return this.additionalServices;
    }

	public void setAccommodationsUnitList(List<AccommodationUnit> accommodationsUnitList) {
		this.accommodationsUnitList = accommodationsUnitList;
	}

	public void setAdditionalServices(List<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

}
