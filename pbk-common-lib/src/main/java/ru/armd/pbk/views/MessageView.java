package ru.armd.pbk.views;

import ru.armd.pbk.core.views.IView;

/**
 * Общий класс для передачи информации.
 */
public class MessageView
	implements IView {
   /**
	* Сообщение при успехе.
	*/
   private String success = "";

   /**
	* Сообщение об ошибке при ошибке.
	*/
   private String error = "";
   private String text = "";

   public String getSuccess() {
	  return success;
   }

   public void setSuccess(String success) {
	  this.success = success;
   }

   public String getError() {
	  return error;
   }

   public void setError(String error) {
	  this.error = error;
   }

   public String getText() {
	  return text;
   }

   public void setText(String text) {
	  this.text = text;
   }
}
