package ru.armd.pbk.components.jms;

import java.io.Serializable;

/**
 * Конверт сообщения.
 */
public class PbkMessage
	implements Serializable {

   private static final long serialVersionUID = 7255544151886259420L;

   private String message;

   /**
	* Конструктор по умолчанию.
	*/
   public PbkMessage() {
   }

   public String getMessage() {
	  return message;
   }

   public void setMessage(String message) {
	  this.message = message;
   }
}
