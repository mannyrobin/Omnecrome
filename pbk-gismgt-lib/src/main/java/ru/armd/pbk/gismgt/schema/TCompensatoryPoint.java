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


/**
 * Точки компенсационных маршуртов (остановки, посты ДПС, МЧС)
 * <p>
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;tags xmlns="http://mappl.ru/2015/XMLSchema/GISMGT" xmlns:gismgt="http://mappl.ru/2015/XMLSchema/GISMGT" xmlns:p578540_="http://java.sun.com/xml/ns/jaxb/xjc" xmlns:p729618_="http://java.sun.com/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;tag&gt;mtrans&lt;/tag&gt;&lt;/tags&gt;
 * </pre>
 * <p>
 * <p>
 * <p>Java class for TCompensatoryPoint complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="TCompensatoryPoint">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://mappl.ru/2015/XMLSchema/GISMGT}TGISDictionaryObject"/>
 *         &lt;element name="name" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TName" minOccurs="0"/>
 *         &lt;element name="type" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RCompensatoryPointType" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TCompensatoryPoint", propOrder = {
	"muid",
	"version",
	"signDeleted",
	"wktGeom",
	"name",
	"type"
})
public class TCompensatoryPoint {

   @XmlElement(required = true)
   protected String muid;
   @XmlElement(defaultValue = "1")
   protected BigInteger version;
   @XmlElementRef(name = "signDeleted", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<BigInteger> signDeleted;
   @XmlElementRef(name = "wkt_geom", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<String> wktGeom;
   @XmlElementRef(name = "name", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<String> name;
   @XmlElementRef(name = "type", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<RCompensatoryPointType> type;

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
	* Gets the value of the wktGeom property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link String }{@code >}
	*/
   public JAXBElement<String> getWktGeom() {
	  return wktGeom;
   }

   /**
	* Sets the value of the wktGeom property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link String }{@code >}
	*/
   public void setWktGeom(JAXBElement<String> value) {
	  this.wktGeom = value;
   }

   /**
	* Gets the value of the name property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link String }{@code >}
	*/
   public JAXBElement<String> getName() {
	  return name;
   }

   /**
	* Sets the value of the name property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link String }{@code >}
	*/
   public void setName(JAXBElement<String> value) {
	  this.name = value;
   }

   /**
	* Gets the value of the type property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link RCompensatoryPointType }{@code >}
	*/
   public JAXBElement<RCompensatoryPointType> getType() {
	  return type;
   }

   /**
	* Sets the value of the type property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link RCompensatoryPointType }{@code >}
	*/
   public void setType(JAXBElement<RCompensatoryPointType> value) {
	  this.type = value;
   }

}
