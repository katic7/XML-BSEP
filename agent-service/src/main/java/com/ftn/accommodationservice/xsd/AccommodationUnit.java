//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.06.18 at 10:28:13 PM CEST 
//


package com.ftn.accommodationservice.xsd;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for AccommodationUnit complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="AccommodationUnit">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="id" type="{http://ftn.com/accommodationservice/xsd}id"/>
 *         &lt;element name="numberOfBeds" type="{http://ftn.com/accommodationservice/xsd}numberOfBeds"/>
 *         &lt;element name="balcony" type="{http://ftn.com/accommodationservice/xsd}balcony"/>
 *         &lt;element name="price" type="{http://ftn.com/accommodationservice/xsd}AccUnitPrice"/>
 *         &lt;element name="description" type="{http://ftn.com/accommodationservice/xsd}description"/>
 *         &lt;element name="rating" type="{http://ftn.com/accommodationservice/xsd}rating"/>
 *         &lt;element name="image" type="{http://ftn.com/accommodationservice/xsd}images"/>
 *         &lt;element name="accObjectId" type="{http://ftn.com/accommodationservice/xsd}id"/>
 *         &lt;element name="reservations" type="{http://ftn.com/accommodationservice/xsd}Reservation" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="additionalServices" type="{http://ftn.com/accommodationservice/xsd}AdditionalService" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "AccommodationUnit", propOrder = {
    "id",
    "numberOfBeds",
    "balcony",
    "price",
    "description",
    "rating",
    "image",
    "accObjectId",
    "reservations",
    "additionalServices"
})
public class AccommodationUnit {

    protected long id;
    protected int numberOfBeds;
    protected boolean balcony;
    @XmlElement(required = true)
    protected AccUnitPrice price;
    @XmlElement(required = true)
    protected String description;
    protected double rating;
    @XmlElement(required = true)
    protected byte[] image;
    protected long accObjectId;
    protected List<Reservation> reservations;
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
     * Gets the value of the numberOfBeds property.
     * 
     */
    public int getNumberOfBeds() {
        return numberOfBeds;
    }

    /**
     * Sets the value of the numberOfBeds property.
     * 
     */
    public void setNumberOfBeds(int value) {
        this.numberOfBeds = value;
    }

    /**
     * Gets the value of the balcony property.
     * 
     */
    public boolean isBalcony() {
        return balcony;
    }

    /**
     * Sets the value of the balcony property.
     * 
     */
    public void setBalcony(boolean value) {
        this.balcony = value;
    }

    /**
     * Gets the value of the price property.
     * 
     * @return
     *     possible object is
     *     {@link AccUnitPrice }
     *     
     */
    public AccUnitPrice getPrice() {
        return price;
    }

    /**
     * Sets the value of the price property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccUnitPrice }
     *     
     */
    public void setPrice(AccUnitPrice value) {
        this.price = value;
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
     * Gets the value of the rating property.
     * 
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets the value of the rating property.
     * 
     */
    public void setRating(double value) {
        this.rating = value;
    }

    /**
     * Gets the value of the image property.
     * 
     * @return
     *     possible object is
     *     byte[]
     */
    public byte[] getImage() {
        return image;
    }

    /**
     * Sets the value of the image property.
     * 
     * @param value
     *     allowed object is
     *     byte[]
     */
    public void setImage(byte[] value) {
        this.image = ((byte[]) value);
    }

    /**
     * Gets the value of the accObjectId property.
     * 
     */
    public long getAccObjectId() {
        return accObjectId;
    }

    /**
     * Sets the value of the accObjectId property.
     * 
     */
    public void setAccObjectId(long value) {
        this.accObjectId = value;
    }

    /**
     * Gets the value of the reservations property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the reservations property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getReservations().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Reservation }
     * 
     * 
     */
    public List<Reservation> getReservations() {
        if (reservations == null) {
            reservations = new ArrayList<Reservation>();
        }
        return this.reservations;
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
    public List<AdditionalService> getAdditionalServices() {
        if (additionalServices == null) {
            additionalServices = new ArrayList<AdditionalService>();
        }
        return this.additionalServices;
    }

}
