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
import javax.xml.datatype.XMLGregorianCalendar;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;


/**
 * Маршруты
 * <p>
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;tags xmlns="http://mappl.ru/2015/XMLSchema/GISMGT" xmlns:gismgt="http://mappl.ru/2015/XMLSchema/GISMGT" xmlns:p578540_="http://java.sun.com/xml/ns/jaxb/xjc" xmlns:p729618_="http://java.sun.com/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;tag&gt;mtrans&lt;/tag&gt;&lt;/tags&gt;
 * </pre>
 * <p>
 * <p>
 * <p>Java class for TRoute complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="TRoute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://mappl.ru/2015/XMLSchema/GISMGT}TDictionaryObject"/>
 *         &lt;element name="number" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TRouteNumber" minOccurs="0"/>
 *         &lt;element name="kind" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RRouteKind" minOccurs="0"/>
 *         &lt;element name="transportKind" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RRouteTransportKind" minOccurs="0"/>
 *         &lt;element name="transportationKind" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RRouteTransportationKind" minOccurs="0"/>
 *         &lt;element name="state" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RRouteState" minOccurs="0"/>
 *         &lt;element name="state2" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RRouteState2" minOccurs="0"/>
 *         &lt;element name="currentRouteVariant" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RRouteVariant" minOccurs="0"/>
 *         &lt;element name="agency" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RAgency" minOccurs="0"/>
 *         &lt;element name="openDate" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TDate" minOccurs="0"/>
 *         &lt;element name="openOrder" type="{http://mappl.ru/2015/XMLSchema/GISMGT}ROrder" minOccurs="0"/>
 *         &lt;element name="closeDate" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TDate" minOccurs="0"/>
 *         &lt;element name="closeOrder" type="{http://mappl.ru/2015/XMLSchema/GISMGT}ROrder" minOccurs="0"/>
 *         &lt;element name="comment" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TComment" minOccurs="0"/>
 *         &lt;element name="parks" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="park" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RPark" maxOccurs="unbounded"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TRoute", propOrder = {
	"muid",
	"version",
	"signDeleted",
	"number",
	"kind",
	"transportKind",
	"transportationKind",
	"state",
	"state2",
	"currentRouteVariant",
	"agency",
	"openDate",
	"openOrder",
	"closeDate",
	"closeOrder",
	"comment",
	"parks"
})
public class TRoute {

   @XmlElement(required = true)
   protected String muid;
   @XmlElement(defaultValue = "1")
   protected BigInteger version;
   @XmlElementRef(name = "signDeleted", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<BigInteger> signDeleted;
   @XmlElementRef(name = "number", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<String> number;
   @XmlElementRef(name = "kind", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<RRouteKind> kind;
   @XmlElementRef(name = "transportKind", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<RRouteTransportKind> transportKind;
   @XmlElementRef(name = "transportationKind", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<RRouteTransportationKind> transportationKind;
   @XmlElementRef(name = "state", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<RRouteState> state;
   @XmlElementRef(name = "state2", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<RRouteState2> state2;
   @XmlElementRef(name = "currentRouteVariant", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<RRouteVariant> currentRouteVariant;
   @XmlElementRef(name = "agency", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<RAgency> agency;
   @XmlElementRef(name = "openDate", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<XMLGregorianCalendar> openDate;
   @XmlElementRef(name = "openOrder", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<ROrder> openOrder;
   @XmlElementRef(name = "closeDate", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<XMLGregorianCalendar> closeDate;
   @XmlElementRef(name = "closeOrder", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<ROrder> closeOrder;
   @XmlElementRef(name = "comment", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<String> comment;
   protected TRoute.Parks parks;

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
	* Gets the value of the number property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link String }{@code >}
	*/
   public JAXBElement<String> getNumber() {
	  return number;
   }

   /**
	* Sets the value of the number property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link String }{@code >}
	*/
   public void setNumber(JAXBElement<String> value) {
	  this.number = value;
   }

   /**
	* Gets the value of the kind property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link RRouteKind }{@code >}
	*/
   public JAXBElement<RRouteKind> getKind() {
	  return kind;
   }

   /**
	* Sets the value of the kind property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link RRouteKind }{@code >}
	*/
   public void setKind(JAXBElement<RRouteKind> value) {
	  this.kind = value;
   }

   /**
	* Gets the value of the transportKind property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link RRouteTransportKind }{@code >}
	*/
   public JAXBElement<RRouteTransportKind> getTransportKind() {
	  return transportKind;
   }

   /**
	* Sets the value of the transportKind property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link RRouteTransportKind }{@code >}
	*/
   public void setTransportKind(JAXBElement<RRouteTransportKind> value) {
	  this.transportKind = value;
   }

   /**
	* Gets the value of the transportationKind property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link RRouteTransportationKind }{@code >}
	*/
   public JAXBElement<RRouteTransportationKind> getTransportationKind() {
	  return transportationKind;
   }

   /**
	* Sets the value of the transportationKind property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link RRouteTransportationKind }{@code >}
	*/
   public void setTransportationKind(JAXBElement<RRouteTransportationKind> value) {
	  this.transportationKind = value;
   }

   /**
	* Gets the value of the state property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link RRouteState }{@code >}
	*/
   public JAXBElement<RRouteState> getState() {
	  return state;
   }

   /**
	* Sets the value of the state property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link RRouteState }{@code >}
	*/
   public void setState(JAXBElement<RRouteState> value) {
	  this.state = value;
   }

   /**
	* Gets the value of the state2 property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link RRouteState2 }{@code >}
	*/
   public JAXBElement<RRouteState2> getState2() {
	  return state2;
   }

   /**
	* Sets the value of the state2 property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link RRouteState2 }{@code >}
	*/
   public void setState2(JAXBElement<RRouteState2> value) {
	  this.state2 = value;
   }

   /**
	* Gets the value of the currentRouteVariant property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link RRouteVariant }{@code >}
	*/
   public JAXBElement<RRouteVariant> getCurrentRouteVariant() {
	  return currentRouteVariant;
   }

   /**
	* Sets the value of the currentRouteVariant property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link RRouteVariant }{@code >}
	*/
   public void setCurrentRouteVariant(JAXBElement<RRouteVariant> value) {
	  this.currentRouteVariant = value;
   }

   /**
	* Gets the value of the agency property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link RAgency }{@code >}
	*/
   public JAXBElement<RAgency> getAgency() {
	  return agency;
   }

   /**
	* Sets the value of the agency property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link RAgency }{@code >}
	*/
   public void setAgency(JAXBElement<RAgency> value) {
	  this.agency = value;
   }

   /**
	* Gets the value of the openDate property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public JAXBElement<XMLGregorianCalendar> getOpenDate() {
	  return openDate;
   }

   /**
	* Sets the value of the openDate property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public void setOpenDate(JAXBElement<XMLGregorianCalendar> value) {
	  this.openDate = value;
   }

   /**
	* Gets the value of the openOrder property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link ROrder }{@code >}
	*/
   public JAXBElement<ROrder> getOpenOrder() {
	  return openOrder;
   }

   /**
	* Sets the value of the openOrder property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link ROrder }{@code >}
	*/
   public void setOpenOrder(JAXBElement<ROrder> value) {
	  this.openOrder = value;
   }

   /**
	* Gets the value of the closeDate property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public JAXBElement<XMLGregorianCalendar> getCloseDate() {
	  return closeDate;
   }

   /**
	* Sets the value of the closeDate property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public void setCloseDate(JAXBElement<XMLGregorianCalendar> value) {
	  this.closeDate = value;
   }

   /**
	* Gets the value of the closeOrder property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link ROrder }{@code >}
	*/
   public JAXBElement<ROrder> getCloseOrder() {
	  return closeOrder;
   }

   /**
	* Sets the value of the closeOrder property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link ROrder }{@code >}
	*/
   public void setCloseOrder(JAXBElement<ROrder> value) {
	  this.closeOrder = value;
   }

   /**
	* Gets the value of the comment property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link String }{@code >}
	*/
   public JAXBElement<String> getComment() {
	  return comment;
   }

   /**
	* Sets the value of the comment property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link String }{@code >}
	*/
   public void setComment(JAXBElement<String> value) {
	  this.comment = value;
   }

   /**
	* Gets the value of the parks property.
	*
	* @return possible object is
	* {@link TRoute.Parks }
	*/
   public TRoute.Parks getParks() {
	  return parks;
   }

   /**
	* Sets the value of the parks property.
	*
	* @param value allowed object is
	*              {@link TRoute.Parks }
	*/
   public void setParks(TRoute.Parks value) {
	  this.parks = value;
   }


   /**
	* список парков
	* <p>
	* <p>
	* <p>Java class for anonymous complex type.
	* <p>
	* <p>The following schema fragment specifies the expected content contained within this class.
	* <p>
	* <pre>
	* &lt;complexType>
	*   &lt;complexContent>
	*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	*       &lt;sequence>
	*         &lt;element name="park" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RPark" maxOccurs="unbounded"/>
	*       &lt;/sequence>
	*     &lt;/restriction>
	*   &lt;/complexContent>
	* &lt;/complexType>
	* </pre>
	*/
   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(name = "", propOrder = {
	   "parks"
   })
   public static class Parks {

	  @XmlElement(name = "park", required = true, nillable = true)
	  protected List<RPark> parks;

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

   }

}