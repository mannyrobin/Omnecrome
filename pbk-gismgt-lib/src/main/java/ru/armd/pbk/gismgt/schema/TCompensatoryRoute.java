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
 * Компенсационные маршруты
 * <p>
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;tags xmlns="http://mappl.ru/2015/XMLSchema/GISMGT" xmlns:gismgt="http://mappl.ru/2015/XMLSchema/GISMGT" xmlns:p578540_="http://java.sun.com/xml/ns/jaxb/xjc" xmlns:p729618_="http://java.sun.com/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;tag&gt;mtrans&lt;/tag&gt;&lt;/tags&gt;
 * </pre>
 * <p>
 * <p>
 * <p>Java class for TCompensatoryRoute complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="TCompensatoryRoute">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://mappl.ru/2015/XMLSchema/GISMGT}TGISDictionaryObject"/>
 *         &lt;element name="number" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TRouteNumber" minOccurs="0"/>
 *         &lt;element name="points" minOccurs="0">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="point" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RCompensatoryPoint" maxOccurs="unbounded"/>
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
@XmlType(name = "TCompensatoryRoute", propOrder = {
	"muid",
	"version",
	"signDeleted",
	"wktGeom",
	"number",
	"points"
})
public class TCompensatoryRoute {

   @XmlElement(required = true)
   protected String muid;
   @XmlElement(defaultValue = "1")
   protected BigInteger version;
   @XmlElementRef(name = "signDeleted", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<BigInteger> signDeleted;
   @XmlElementRef(name = "wkt_geom", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<String> wktGeom;
   @XmlElementRef(name = "number", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<String> number;
   protected TCompensatoryRoute.Points points;

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
	* Gets the value of the points property.
	*
	* @return possible object is
	* {@link TCompensatoryRoute.Points }
	*/
   public TCompensatoryRoute.Points getPoints() {
	  return points;
   }

   /**
	* Sets the value of the points property.
	*
	* @param value allowed object is
	*              {@link TCompensatoryRoute.Points }
	*/
   public void setPoints(TCompensatoryRoute.Points value) {
	  this.points = value;
   }


   /**
	* список точек
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
	*         &lt;element name="point" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RCompensatoryPoint" maxOccurs="unbounded"/>
	*       &lt;/sequence>
	*     &lt;/restriction>
	*   &lt;/complexContent>
	* &lt;/complexType>
	* </pre>
	*/
   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(name = "", propOrder = {
	   "points"
   })
   public static class Points {

	  @XmlElement(name = "point", required = true, nillable = true)
	  protected List<RCompensatoryPoint> points;

	  /**
	   * Gets the value of the points property.
	   * <p>
	   * <p>
	   * This accessor method returns a reference to the live list,
	   * not a snapshot. Therefore any modification you make to the
	   * returned list will be present inside the JAXB object.
	   * This is why there is not a <CODE>set</CODE> method for the points property.
	   * <p>
	   * <p>
	   * For example, to add a new item, do as follows:
	   * <pre>
	   *    getPoints().add(newItem);
	   * </pre>
	   * <p>
	   * <p>
	   * <p>
	   * Objects of the following type(s) are allowed in the list
	   * {@link RCompensatoryPoint }
	   */
	  public List<RCompensatoryPoint> getPoints() {
		 if (points == null) {
			points = new ArrayList<RCompensatoryPoint>();
		 }
		 return this.points;
	  }

   }

}