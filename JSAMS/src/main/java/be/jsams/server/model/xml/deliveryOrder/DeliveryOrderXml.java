//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2011.12.21 at 05:39:48 PM CET 
//

package be.jsams.server.model.xml.deliveryOrder;

import java.math.BigInteger;
import java.util.Date;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlSchemaType;
import javax.xml.bind.annotation.XmlType;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import org.w3._2001.xmlschema.Adapter1;

import be.jsams.server.model.xml.AddressXml;
import be.jsams.server.model.xml.CustomerXml;
import be.jsams.server.model.xml.SocietyXml;

/**
 * <p>
 * Java class for anonymous complex type.
 * 
 * <p>
 * The following schema fragment specifies the expected content contained within
 * this class.
 * 
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{}society"/>
 *         &lt;element ref="{}customer"/>
 *         &lt;element ref="{}creation_date"/>
 *         &lt;element ref="{}number"/>
 *         &lt;element ref="{}address"/>
 *         &lt;element ref="{}details"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = { "society", "customer", "creationDate", "number", "address", "details" })
@XmlRootElement(name = "delivery_order")
public class DeliveryOrderXml {

    @XmlElement(required = true)
    private SocietyXml society;
    @XmlElement(required = true)
    private CustomerXml customer;
    @XmlElement(name = "creation_date", required = true, type = String.class)
    @XmlJavaTypeAdapter(Adapter1.class)
    @XmlSchemaType(name = "date")
    private Date creationDate;
    @XmlElement(required = true)
    private BigInteger number;
    @XmlElement(required = true)
    private AddressXml address;
    @XmlElement(required = true)
    private DetailsXml details;

    /**
     * Gets the value of the society property.
     * 
     * @return possible object is {@link SocietyXml }
     * 
     */
    public SocietyXml getSociety() {
        return society;
    }

    /**
     * Sets the value of the society property.
     * 
     * @param value allowed object is {@link SocietyXml }
     * 
     */
    public void setSociety(SocietyXml value) {
        this.society = value;
    }

    /**
     * Gets the value of the customer property.
     * 
     * @return possible object is {@link CustomerXml }
     * 
     */
    public CustomerXml getCustomer() {
        return customer;
    }

    /**
     * Sets the value of the customer property.
     * 
     * @param value allowed object is {@link CustomerXml }
     * 
     */
    public void setCustomer(CustomerXml value) {
        this.customer = value;
    }

    /**
     * Gets the value of the creationDate property.
     * 
     * @return possible object is {@link String }
     * 
     */
    public Date getCreationDate() {
        return creationDate;
    }

    /**
     * Sets the value of the creationDate property.
     * 
     * @param value allowed object is {@link String }
     * 
     */
    public void setCreationDate(Date value) {
        this.creationDate = value;
    }

    /**
     * Gets the value of the number property.
     * 
     * @return possible object is {@link BigInteger }
     * 
     */
    public BigInteger getNumber() {
        return number;
    }

    /**
     * Sets the value of the number property.
     * 
     * @param value allowed object is {@link BigInteger }
     * 
     */
    public void setNumber(BigInteger value) {
        this.number = value;
    }

    /**
     * Gets the value of the address property.
     * 
     * @return possible object is {@link AddressXml }
     * 
     */
    public AddressXml getAddress() {
        return address;
    }

    /**
     * Sets the value of the address property.
     * 
     * @param value allowed object is {@link AddressXml }
     * 
     */
    public void setAddress(AddressXml value) {
        this.address = value;
    }

    /**
     * Gets the value of the details property.
     * 
     * @return possible object is {@link DetailsXml }
     * 
     */
    public DetailsXml getDetails() {
        return details;
    }

    /**
     * Sets the value of the details property.
     * 
     * @param value allowed object is {@link DetailsXml }
     * 
     */
    public void setDetails(DetailsXml value) {
        this.details = value;
    }

}