//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, vhudson-jaxb-ri-2.1-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2019.04.13 at 07:15:47 PM CEST 
//


package com.ftn.reservationservice.model;

import java.util.ArrayList;
import java.util.List;
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
 *     &lt;extension base="{www.ftn.com/reservationservice/model}User">
 *       &lt;sequence>
 *         &lt;element name="postedComments" type="{www.ftn.com/reservationservice/model}Comment" maxOccurs="unbounded" minOccurs="0"/>
 *         &lt;element name="accommodations" type="{www.ftn.com/reservationservice/model}AccommodationObject"/>
 *         &lt;element name="endUsers" type="{www.ftn.com/reservationservice/model}User" maxOccurs="unbounded" minOccurs="0" form="qualified"/>
 *         &lt;element name="agentUsers" type="{www.ftn.com/reservationservice/model}User" maxOccurs="unbounded" minOccurs="0"/>
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
    "postedComments",
    "accommodations",
    "endUsers",
    "agentUsers"
})
@XmlRootElement(name = "Administrator")
public class Administrator
    extends User
{

    protected List<Comment> postedComments;
    @XmlElement(required = true)
    protected AccommodationObject accommodations;
    protected List<User> endUsers;
    protected List<User> agentUsers;

    /**
     * Gets the value of the postedComments property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the postedComments property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getPostedComments().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Comment }
     * 
     * 
     */
    public List<Comment> getPostedComments() {
        if (postedComments == null) {
            postedComments = new ArrayList<Comment>();
        }
        return this.postedComments;
    }

    /**
     * Gets the value of the accommodations property.
     * 
     * @return
     *     possible object is
     *     {@link AccommodationObject }
     *     
     */
    public AccommodationObject getAccommodations() {
        return accommodations;
    }

    /**
     * Sets the value of the accommodations property.
     * 
     * @param value
     *     allowed object is
     *     {@link AccommodationObject }
     *     
     */
    public void setAccommodations(AccommodationObject value) {
        this.accommodations = value;
    }

    /**
     * Gets the value of the endUsers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the endUsers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getEndUsers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     * 
     * 
     */
    public List<User> getEndUsers() {
        if (endUsers == null) {
            endUsers = new ArrayList<User>();
        }
        return this.endUsers;
    }

    /**
     * Gets the value of the agentUsers property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the agentUsers property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAgentUsers().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link User }
     * 
     * 
     */
    public List<User> getAgentUsers() {
        if (agentUsers == null) {
            agentUsers = new ArrayList<User>();
        }
        return this.agentUsers;
    }

}
