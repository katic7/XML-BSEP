//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.13 at 07:12:27 PM CEST 
//


package com.ftn.accommodationservice.model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
 *         &lt;element name="id" type="{www.ftn.com/accommodationservice/model}id"/>
 *         &lt;element name="numberOfBeds" type="{www.ftn.com/accommodationservice/model}numberOfBeds"/>
 *         &lt;element name="balcony" type="{www.ftn.com/accommodationservice/model}balcony"/>
 *         &lt;element name="price" type="{www.ftn.com/accommodationservice/model}AccUnitPrice"/>
 *         &lt;element name="description" type="{www.ftn.com/accommodationservice/model}description"/>
 *         &lt;element name="rating" type="{www.ftn.com/accommodationservice/model}rating"/>
 *         &lt;element name="image" type="{www.ftn.com/accommodationservice/model}images" maxOccurs="unbounded"/>
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
@XmlType(name = "AccommodationUnit", propOrder = {
    "id",
    "numberOfBeds",
    "balcony",
    "price",
    "description",
    "rating",
    "image",
    "additionalServices",
    "accommodationObject",
    "reservations"
})

@Entity
public class AccommodationUnit {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    protected long id;
    protected int numberOfBeds;
    protected boolean balcony;
    @XmlElement(required = true)
    @OneToOne
    protected AccUnitPrice price;
    @XmlElement(required = true)
    protected String description;
    protected double rating;
    @XmlElement(required = true)
    protected String image;
    @ManyToMany
	@JoinTable(
	        name = "accommodation_unit_additional_services", 
	        joinColumns = { @JoinColumn(name = "accommodation_unit_id") }, 
	        inverseJoinColumns = { @JoinColumn(name = "additional_services_id") }
	    )
    protected List<AdditionalService> additionalServices;
    @ManyToOne
    @JoinColumn(name="accommodation_object")
    private AccommodationObject accommodationObject;
    
    @OneToMany(mappedBy="accommodationUnit")
    private List<Reservation> reservations;

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
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the image property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getImage().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * byte[]
     * 
     */
    

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

	public AccommodationObject getAccommodationObject() {
		return accommodationObject;
	}

	public void setAccommodationObject(AccommodationObject accommodationObject) {
		this.accommodationObject = accommodationObject;
	}

	

	public void setAdditionalServices(List<AdditionalService> additionalServices) {
		this.additionalServices = additionalServices;
	}

	public List<Reservation> getReservations() {
		return reservations;
	}

	public void setReservations(List<Reservation> reservations) {
		this.reservations = reservations;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

}
