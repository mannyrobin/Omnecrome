//
// This file was generated by the JavaTM Architecture for XML Binding(JAXB) Reference Implementation, v2.2.4-2 
// See <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// Any modifications to this file will be lost upon recompilation of the source schema. 
// Generated on: 2016.06.24 at 01:07:10 PM MSK 
//


package ru.armd.pbk.gismgt.schema;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for ROrder complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="ROrder">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header">
 *           &lt;complexType>
 *             &lt;complexContent>
 *               &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *                 &lt;sequence>
 *                   &lt;element name="objectType" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TObjectType"/>
 *                   &lt;group ref="{http://mappl.ru/2015/XMLSchema/GISMGT}TReferenceHeaderBase"/>
 *                 &lt;/sequence>
 *               &lt;/restriction>
 *             &lt;/complexContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *         &lt;element name="body" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TOrder" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "ROrder", propOrder = {
	"header",
	"body"
})
public class ROrder {

   @XmlElement(required = true)
   protected ROrder.Header header;
   protected TOrder body;

   /**
	* Gets the value of the header property.
	*
	* @return possible object is
	* {@link ROrder.Header }
	*/
   public ROrder.Header getHeader() {
	  return header;
   }

   /**
	* Sets the value of the header property.
	*
	* @param value allowed object is
	*              {@link ROrder.Header }
	*/
   public void setHeader(ROrder.Header value) {
	  this.header = value;
   }

   /**
	* Gets the value of the body property.
	*
	* @return possible object is
	* {@link TOrder }
	*/
   public TOrder getBody() {
	  return body;
   }

   /**
	* Sets the value of the body property.
	*
	* @param value allowed object is
	*              {@link TOrder }
	*/
   public void setBody(TOrder value) {
	  this.body = value;
   }


   /**
	* <p>Java class for anonymous complex type.
	* <p>
	* <p>The following schema fragment specifies the expected content contained within this class.
	* <p>
	* <pre>
	* &lt;complexType>
	*   &lt;complexContent>
	*     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
	*       &lt;sequence>
	*         &lt;element name="objectType" type="{http://mappl.ru/2015/XMLSchema/GISMGT}TObjectType"/>
	*         &lt;group ref="{http://mappl.ru/2015/XMLSchema/GISMGT}TReferenceHeaderBase"/>
	*       &lt;/sequence>
	*     &lt;/restriction>
	*   &lt;/complexContent>
	* &lt;/complexType>
	* </pre>
	*/
   @XmlAccessorType(XmlAccessType.FIELD)
   @XmlType(name = "", propOrder = {
	   "objectType",
	   "identSet",
	   "operation"
   })
   public static class Header {

	  @XmlElement(required = true)
	  protected TObjectType objectType;
	  @XmlElement(required = true)
	  protected TComponentIds identSet;
	  @XmlElement(required = true)
	  protected MsgTEventName operation;

	  /**
	   * Gets the value of the objectType property.
	   *
	   * @return possible object is
	   * {@link TObjectType }
	   */
	  public TObjectType getObjectType() {
		 return objectType;
	  }

	  /**
	   * Sets the value of the objectType property.
	   *
	   * @param value allowed object is
	   *              {@link TObjectType }
	   */
	  public void setObjectType(TObjectType value) {
		 this.objectType = value;
	  }

	  /**
	   * Gets the value of the identSet property.
	   *
	   * @return possible object is
	   * {@link TComponentIds }
	   */
	  public TComponentIds getIdentSet() {
		 return identSet;
	  }

	  /**
	   * Sets the value of the identSet property.
	   *
	   * @param value allowed object is
	   *              {@link TComponentIds }
	   */
	  public void setIdentSet(TComponentIds value) {
		 this.identSet = value;
	  }

	  /**
	   * Gets the value of the operation property.
	   *
	   * @return possible object is
	   * {@link MsgTEventName }
	   */
	  public MsgTEventName getOperation() {
		 return operation;
	  }

	  /**
	   * Sets the value of the operation property.
	   *
	   * @param value allowed object is
	   *              {@link MsgTEventName }
	   */
	  public void setOperation(MsgTEventName value) {
		 this.operation = value;
	  }

   }

}
