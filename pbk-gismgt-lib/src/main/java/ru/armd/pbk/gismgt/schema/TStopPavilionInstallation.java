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
 * Информация об установке павильона
 * <p>
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;tags xmlns="http://mappl.ru/2015/XMLSchema/GISMGT" xmlns:gismgt="http://mappl.ru/2015/XMLSchema/GISMGT" xmlns:p578540_="http://java.sun.com/xml/ns/jaxb/xjc" xmlns:p729618_="http://java.sun.com/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;tag&gt;mtrans&lt;/tag&gt;&lt;/tags&gt;
 * </pre>
 * <p>
 * <p>
 * <p>Java class for TStopPavilionInstallation complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="TStopPavilionInstallation">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://mappl.ru/2015/XMLSchema/GISMGT}TDictionaryObject"/>
 *         &lt;element name="stopPavilion" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RStopPavilion" maxOccurs="unbounded"/>
 *         &lt;element name="stopPlace" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RStopPlace" maxOccurs="unbounded"/>
 *         &lt;element name="installationDate" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TDate" minOccurs="0"/>
 *         &lt;element name="installationOrder" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RStopPavilionOrder" maxOccurs="unbounded"/>
 *         &lt;element name="deinstallationDate" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TDate" minOccurs="0"/>
 *         &lt;element name="deinstallationOrder" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RStopPavilionOrder" maxOccurs="unbounded"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TStopPavilionInstallation", propOrder = {
	"muid",
	"version",
	"signDeleted",
	"stopPavilions",
	"stopPlaces",
	"installationDate",
	"installationOrders",
	"deinstallationDate",
	"deinstallationOrders"
})
public class TStopPavilionInstallation {

   @XmlElement(required = true)
   protected String muid;
   @XmlElement(defaultValue = "1")
   protected BigInteger version;
   @XmlElementRef(name = "signDeleted", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<BigInteger> signDeleted;
   @XmlElement(name = "stopPavilion", required = true, nillable = true)
   protected List<RStopPavilion> stopPavilions;
   @XmlElement(name = "stopPlace", required = true, nillable = true)
   protected List<RStopPlace> stopPlaces;
   @XmlElementRef(name = "installationDate", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<XMLGregorianCalendar> installationDate;
   @XmlElement(name = "installationOrder", required = true, nillable = true)
   protected List<RStopPavilionOrder> installationOrders;
   @XmlElementRef(name = "deinstallationDate", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<XMLGregorianCalendar> deinstallationDate;
   @XmlElement(name = "deinstallationOrder", required = true, nillable = true)
   protected List<RStopPavilionOrder> deinstallationOrders;

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
	* Gets the value of the stopPavilions property.
	* <p>
	* <p>
	* This accessor method returns a reference to the live list,
	* not a snapshot. Therefore any modification you make to the
	* returned list will be present inside the JAXB object.
	* This is why there is not a <CODE>set</CODE> method for the stopPavilions property.
	* <p>
	* <p>
	* For example, to add a new item, do as follows:
	* <pre>
	*    getStopPavilions().add(newItem);
	* </pre>
	* <p>
	* <p>
	* <p>
	* Objects of the following type(s) are allowed in the list
	* {@link RStopPavilion }
	*/
   public List<RStopPavilion> getStopPavilions() {
	  if (stopPavilions == null) {
		 stopPavilions = new ArrayList<RStopPavilion>();
	  }
	  return this.stopPavilions;
   }

   /**
	* Gets the value of the stopPlaces property.
	* <p>
	* <p>
	* This accessor method returns a reference to the live list,
	* not a snapshot. Therefore any modification you make to the
	* returned list will be present inside the JAXB object.
	* This is why there is not a <CODE>set</CODE> method for the stopPlaces property.
	* <p>
	* <p>
	* For example, to add a new item, do as follows:
	* <pre>
	*    getStopPlaces().add(newItem);
	* </pre>
	* <p>
	* <p>
	* <p>
	* Objects of the following type(s) are allowed in the list
	* {@link RStopPlace }
	*/
   public List<RStopPlace> getStopPlaces() {
	  if (stopPlaces == null) {
		 stopPlaces = new ArrayList<RStopPlace>();
	  }
	  return this.stopPlaces;
   }

   /**
	* Gets the value of the installationDate property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public JAXBElement<XMLGregorianCalendar> getInstallationDate() {
	  return installationDate;
   }

   /**
	* Sets the value of the installationDate property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public void setInstallationDate(JAXBElement<XMLGregorianCalendar> value) {
	  this.installationDate = value;
   }

   /**
	* Gets the value of the installationOrders property.
	* <p>
	* <p>
	* This accessor method returns a reference to the live list,
	* not a snapshot. Therefore any modification you make to the
	* returned list will be present inside the JAXB object.
	* This is why there is not a <CODE>set</CODE> method for the installationOrders property.
	* <p>
	* <p>
	* For example, to add a new item, do as follows:
	* <pre>
	*    getInstallationOrders().add(newItem);
	* </pre>
	* <p>
	* <p>
	* <p>
	* Objects of the following type(s) are allowed in the list
	* {@link RStopPavilionOrder }
	*/
   public List<RStopPavilionOrder> getInstallationOrders() {
	  if (installationOrders == null) {
		 installationOrders = new ArrayList<RStopPavilionOrder>();
	  }
	  return this.installationOrders;
   }

   /**
	* Gets the value of the deinstallationDate property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public JAXBElement<XMLGregorianCalendar> getDeinstallationDate() {
	  return deinstallationDate;
   }

   /**
	* Sets the value of the deinstallationDate property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public void setDeinstallationDate(JAXBElement<XMLGregorianCalendar> value) {
	  this.deinstallationDate = value;
   }

   /**
	* Gets the value of the deinstallationOrders property.
	* <p>
	* <p>
	* This accessor method returns a reference to the live list,
	* not a snapshot. Therefore any modification you make to the
	* returned list will be present inside the JAXB object.
	* This is why there is not a <CODE>set</CODE> method for the deinstallationOrders property.
	* <p>
	* <p>
	* For example, to add a new item, do as follows:
	* <pre>
	*    getDeinstallationOrders().add(newItem);
	* </pre>
	* <p>
	* <p>
	* <p>
	* Objects of the following type(s) are allowed in the list
	* {@link RStopPavilionOrder }
	*/
   public List<RStopPavilionOrder> getDeinstallationOrders() {
	  if (deinstallationOrders == null) {
		 deinstallationOrders = new ArrayList<RStopPavilionOrder>();
	  }
	  return this.deinstallationOrders;
   }

}
