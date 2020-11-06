package ru.armd.pbk.dto.viss;

import ru.armd.pbk.enums.viss.VisExchangeObjects;

import java.io.Serializable;
import java.util.Date;

/**
 * JMS сообщение модуля ВИС.
 */
public class JmsVisExchangeStartMessage
	implements Serializable {

   private static final long serialVersionUID = -7060523832097356419L;

   private VisExchangeObjects objType;
   private String parameter;
   private Date start;
   private Date end;

   /**
	* Конструктор по умолчанию.
	*/
   public JmsVisExchangeStartMessage() {
   }

   /**
	* Конструктор.
	*
	* @param objType   тип объекта.
	* @param parameter параметр.
	* @param start     начало.
	* @param end       конец.
	*/
   public JmsVisExchangeStartMessage(VisExchangeObjects objType, String parameter, Date start, Date end) {
	  this.objType = objType;
	  this.parameter = parameter;
	  this.start = start;
	  this.end = end;
   }

   public VisExchangeObjects getObjType() {
	  return objType;
   }

   public void setObjType(VisExchangeObjects objType) {
	  this.objType = objType;
   }

   public String getParameter() {
	  return parameter;
   }

   public void setParameter(String parameter) {
	  this.parameter = parameter;
   }

   public Date getStart() {
	  return start;
   }

   public void setStart(Date start) {
	  this.start = start;
   }

   public Date getEnd() {
	  return end;
   }

   public void setEnd(Date end) {
	  this.end = end;
   }

   @Override
   public String toString() {
	  return "JmsVisExchangeStartMessage{"
		  + "objType=" + objType
		  + ", parameter='" + parameter + '\''
		  + ", start=" + start
		  + ", end=" + end
		  + '}';
   }
}
