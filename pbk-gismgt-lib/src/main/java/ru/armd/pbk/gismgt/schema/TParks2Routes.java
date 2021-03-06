//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.24 at 01:07:10 PM MSK 
//


package ru.armd.pbk.gismgt.schema;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementRef;
import javax.xml.bind.annotation.XmlType;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * Таблица связей "Парки - Маршруты"
 * <p>
 * <p>
 * <p>Java class for TParks2Routes complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="TParks2Routes">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://mappl.ru/2015/XMLSchema/GISMGT}TDictionaryObject"/>
 *         &lt;element name="park" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RPark" maxOccurs="unbounded"/>
 *         &lt;element name="route" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RRoute" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TParks2Routes", propOrder = {
	"muid",
	"version",
	"signDeleted",
	"parks",
	"routes"
})
public class TParks2Routes {

   @XmlElement(required = true)
   protected String muid;
   @XmlElement(defaultValue = "1")
   protected BigInteger version;
   @XmlElementRef(name = "signDeleted", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<BigInteger> signDeleted;
   @XmlElement(name = "park", required = true, nillable = true)
   protected List<RPark> parks;
   @XmlElement(name = "route", required = true, nillable = true)
   protected List<RRoute> routes;

   /**
	* Gets the value of the muid property.
	*
	* @return possible object is
	* {@link String }
	*/
   public String getMuid() {
	  return muid;
   }

   /**
	* Sets the value of the muid property.
	*
	* @param value allowed object is
	*              {@link String }
	*/
   public void setMuid(String value) {
	  this.muid = value;
   }

   /**
	* Gets the value of the version property.
	*
	* @return possible object is
	* {@link BigInteger }
	*/
   public BigInteger getVersion() {
	  return version;
   }

   /**
	* Sets the value of the version property.
	*
	* @param value allowed object is
	*              {@link BigInteger }
	*/
   public void setVersion(BigInteger value) {
	  this.version = value;
   }

   /**
	* Gets the value of the signDeleted property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
	*/
   public JAXBElement<BigInteger> getSignDeleted() {
	  return signDeleted;
   }

   /**
	* Sets the value of the signDeleted property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link BigInteger }{@code >}
	*/
   public void setSignDeleted(JAXBElement<BigInteger> value) {
	  this.signDeleted = value;
   }

   /**
	* Gets the value of the parks property.
	* <p>
	* <p>
	* This accessor method returns a reference to the live list,
	* not a snapshot. Therefore any modification you make to the
	* returned list will be present inside the JAXB object.
	* This is why there is not a <CODE>set</CODE> method for the parks property.
	* <p>
	* <p>
	* For example, to add a new item, do as follows:
	* <pre>
	*    getParks().add(newItem);
	* </pre>
	* <p>
	* <p>
	* <p>
	* Objects of the following type(s) are allowed in the list
	* {@link RPark }
	*/
   public List<RPark> getParks() {
	  if (parks == null) {
		 parks = new ArrayList<RPark>();
	  }
	  return this.parks;
   }

   /**
	* Gets the value of the routes property.
	* <p>
	* <p>
	* This accessor method returns a reference to the live list,
	* not a snapshot. Therefore any modification you make to the
	* returned list will be present inside the JAXB object.
	* This is why there is not a <CODE>set</CODE> method for the routes property.
	* <p>
	* <p>
	* For example, to add a new item, do as follows:
	* <pre>
	*    getRoutes().add(newItem);
	* </pre>
	* <p>
	* <p>
	* <p>
	* Objects of the following type(s) are allowed in the list
	* {@link RRoute }
	*/
   public List<RRoute> getRoutes() {
	  if (routes == null) {
		 routes = new ArrayList<RRoute>();
	  }
	  return this.routes;
   }

   public void setParks(List<RPark> parks) {
	  this.parks = parks;
   }

   public void setRoutes(List<RRoute> routes) {
	  this.routes = routes;
   }

}
