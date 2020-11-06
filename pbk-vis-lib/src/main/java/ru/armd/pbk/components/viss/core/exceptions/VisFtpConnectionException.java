package ru.armd.pbk.components.viss.core.exceptions;

/**
 * Исключение VisFtpConnectionException.
 */
public class VisFtpConnectionException
	extends VisFtpException {

   private static final long serialVersionUID = -3721737613065410170L;

   /**
	* Конструктор по умолчанию.
	*/
   public VisFtpConnectionException() {
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	*/
   public VisFtpConnectionException(String message) {
	  super(message);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	* @param cause   исключение.
	*/
   public VisFtpConnectionException(String message, Throwable cause) {
	  super(message, cause);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param cause исключение.
	*/
   public VisFtpConnectionException(Throwable cause) {
	  super(cause);
   }
}
