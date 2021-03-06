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
 * Приказы по остановочным пунктам
 * <p>
 * <pre>
 * &lt;?xml version="1.0" encoding="UTF-8"?&gt;&lt;tags xmlns="http://mappl.ru/2015/XMLSchema/GISMGT" xmlns:gismgt="http://mappl.ru/2015/XMLSchema/GISMGT" xmlns:p578540_="http://java.sun.com/xml/ns/jaxb/xjc" xmlns:p729618_="http://java.sun.com/xml/ns/jaxb" xmlns:xs="http://www.w3.org/2001/XMLSchema"&gt;&lt;tag&gt;mtrans&lt;/tag&gt;&lt;/tags&gt;
 * </pre>
 * <p>
 * <p>
 * <p>Java class for TStopPavilionOrder complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="TStopPavilionOrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;group ref="{http://mappl.ru/2015/XMLSchema/GISMGT}TDictionaryObject"/>
 *         &lt;element name="name" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TOrderName" minOccurs="0"/>
 *         &lt;element name="number" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TOrderNumber" minOccurs="0"/>
 *         &lt;element name="startDate" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TDate" minOccurs="0"/>
 *         &lt;element name="changeDate" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TDate" minOccurs="0"/>
 *         &lt;element name="documentType" type="{http://mappl.ru/2015/XMLSchema/GISMGT}RStopPavilionOrderType" maxOccurs="unbounded"/>
 *         &lt;element name="comment" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TComment500" minOccurs="0"/>
 *         &lt;element name="file" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TFile" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "TStopPavilionOrder", propOrder = {
	"muid",
	"version",
	"signDeleted",
	"name",
	"number",
	"startDate",
	"changeDate",
	"documentTypes",
	"comment",
	"file"
})
public class TStopPavilionOrder {

   @XmlElement(required = true)
   protected String muid;
   @XmlElement(defaultValue = "1")
   protected BigInteger version;
   @XmlElementRef(name = "signDeleted", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<BigInteger> signDeleted;
   @XmlElementRef(name = "name", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<String> name;
   @XmlElementRef(name = "number", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<String> number;
   @XmlElementRef(name = "startDate", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<XMLGregorianCalendar> startDate;
   @XmlElementRef(name = "changeDate", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<XMLGregorianCalendar> changeDate;
   @XmlElement(name = "documentType", required = true, nillable = true)
   protected List<RStopPavilionOrderType> documentTypes;
   @XmlElementRef(name = "comment", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<String> comment;
   @XmlElementRef(name = "file", namespace = "http://mappl.ru/2015/XMLSchema/GISMGT", type = JAXBElement.class, required = false)
   protected JAXBElement<byte[]> file;

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
	* Gets the value of the startDate property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public JAXBElement<XMLGregorianCalendar> getStartDate() {
	  return startDate;
   }

   /**
	* Sets the value of the startDate property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public void setStartDate(JAXBElement<XMLGregorianCalendar> value) {
	  this.startDate = value;
   }

   /**
	* Gets the value of the changeDate property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public JAXBElement<XMLGregorianCalendar> getChangeDate() {
	  return changeDate;
   }

   /**
	* Sets the value of the changeDate property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link XMLGregorianCalendar }{@code >}
	*/
   public void setChangeDate(JAXBElement<XMLGregorianCalendar> value) {
	  this.changeDate = value;
   }

   /**
	* Gets the value of the documentTypes property.
	* <p>
	* <p>
	* This accessor method returns a reference to the live list,
	* not a snapshot. Therefore any modification you make to the
	* returned list will be present inside the JAXB object.
	* This is why there is not a <CODE>set</CODE> method for the documentTypes property.
	* <p>
	* <p>
	* For example, to add a new item, do as follows:
	* <pre>
	*    getDocumentTypes().add(newItem);
	* </pre>
	* <p>
	* <p>
	* <p>
	* Objects of the following type(s) are allowed in the list
	* {@link RStopPavilionOrderType }
	*/
   public List<RStopPavilionOrderType> getDocumentTypes() {
	  if (documentTypes == null) {
		 documentTypes = new ArrayList<RStopPavilionOrderType>();
	  }
	  return this.documentTypes;
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
	* Gets the value of the file property.
	*
	* @return possible object is
	* {@link JAXBElement }{@code <}{@link byte[]}{@code >}
	*/
   public JAXBElement<byte[]> getFile() {
	  return file;
   }

   /**
	* Sets the value of the file property.
	*
	* @param value allowed object is
	*              {@link JAXBElement }{@code <}{@link byte[]}{@code >}
	*/
   public void setFile(JAXBElement<byte[]> value) {
	  this.file = value;
   }

}
