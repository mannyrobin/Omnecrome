package ru.armd.pbk.components.viss.core.exceptions;

/**
 * Исключение VisSoapException.
 */
public class VisSoapException
	extends Exception {

   private static final long serialVersionUID = -7789156096049959519L;

   /**
	* Конструктор по умолчанию.
	*/
   public VisSoapException() {
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	*/
   public VisSoapException(String message) {
	  super(message);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	* @param cause   исключение.
	*/
   public VisSoapException(String message, Throwable cause) {
	  super(message, cause);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param cause исключение.
	*/
   public VisSoapException(Throwable cause) {
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
   public VisSoapException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	  super(message, cause, enableSuppression, writableStackTrace);
   }
}
