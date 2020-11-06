package ru.armd.pbk.utils.json;

import org.apache.commons.lang.exception.ExceptionUtils;

import java.sql.SQLException;

/**
 * Класс исключений.
 */
public class JsonResultException {

   String name;
   String message;
   String stack;

   /**
	* Конструктор.
	*
	* @param t Исключение.
	*/
   public JsonResultException(Throwable t) {
	  name = t.getClass().getName();
	  if (t.getCause() instanceof SQLException) {
		 message = t.getCause().getLocalizedMessage();
	  } else {
		 message = t.getMessage();
	  }
	  stack = ExceptionUtils.getStackTrace(t);
   }

   public String getName() {
	  return name;
   }

   public void setName(String name) {
	  this.name = name;
   }

   public String getMessage() {
	  return message;
   }

   public void setMessage(String message) {
	  this.message = message;
   }

   public String getStack() {
	  return stack;
   }

   public void setStack(String stack) {
	  this.stack = stack;
   }
}
