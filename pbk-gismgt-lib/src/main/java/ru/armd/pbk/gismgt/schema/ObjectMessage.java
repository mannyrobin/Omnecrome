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
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlType;


/**
 * Сообщение жизненного цикла объекта, формируемое системой
 * <p>
 * <p>Java class for msg_TObjectMessage complex type.
 * <p>
 * <p>The following schema fragment specifies the expected content contained within this class.
 * <p>
 * <pre>
 * &lt;complexType name="msg_TObjectMessage">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="header" type="{http://mappl.ru/2015/XMLSchema/GISMGT}msg_TObjectMessageHeader"/>
 *         &lt;element name="body" type="{http://mappl.ru/2015/XMLSchema/GISMGT}msg_TObjectMessageBody" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "msg_TObjectMessage", propOrder = {
	"header",
	"body"
})
@XmlRootElement(name = "object-message")
public class ObjectMessage {

   @XmlElement(required = true)
   protected MsgTObjectMessageHeader header;
   protected MsgTObjectMessageBody body;

   /**
	* Gets the value of the header property.
	*
	* @return possible object is
	* {@link MsgTObjectMessageHeader }
	*/
   public MsgTObjectMessageHeader getHeader() {
	  return header;
   }

   /**
	* Sets the value of the header property.
	*
	* @param value allowed object is
	*              {@link MsgTObjectMessageHeader }
	*/
   public void setHeader(MsgTObjectMessageHeader value) {
	  this.header = value;
   }

   /**
	* Gets the value of the body property.
	*
	* @return possible object is
	* {@link MsgTObjectMessageBody }
	*/
   public MsgTObjectMessageBody getBody() {
	  return body;
   }

   /**
	* Sets the value of the body property.
	*
	* @param value allowed object is
	*              {@link MsgTObjectMessageBody }
	*/
   public void setBody(MsgTObjectMessageBody value) {
	  this.body = value;
   }

}
