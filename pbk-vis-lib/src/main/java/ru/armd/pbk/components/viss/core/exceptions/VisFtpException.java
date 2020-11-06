package ru.armd.pbk.components.viss.core.exceptions;

/**
 * Исключение VisFtpException.
 */
public class VisFtpException
	extends Exception {

   private static final long serialVersionUID = 3870161978618875588L;

   /**
	* Конструктор по умолчанию.
	*/
   public VisFtpException() {
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	*/
   public VisFtpException(String message) {
	  super(message);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	* @param cause   исключение.
	*/
   public VisFtpException(String message, Throwable cause) {
	  super(message, cause);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param cause исключение.
	*/
   public VisFtpException(Throwable cause) {
	  super(cause);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message            сообщение.
	* @param cause              исключение.
	* @param enableSuppression  доступность suppression.
	* @param writableStackTrace доступность трассировки стека.
	*/
   public VisFtpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	  super(message, cause, enableSuppression, writableStackTrace);
   }
}
