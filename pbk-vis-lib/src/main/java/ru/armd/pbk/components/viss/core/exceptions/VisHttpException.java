package ru.armd.pbk.components.viss.core.exceptions;

/**
 * Исключение VisHttpException.
 */
public class VisHttpException
	extends Exception {

   private static final long serialVersionUID = -5532925849980604398L;

   /**
	* Конструктор по умолчанию.
	*/
   public VisHttpException() {
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	*/
   public VisHttpException(String message) {
	  super(message);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	* @param cause   исключение.
	*/
   public VisHttpException(String message, Throwable cause) {
	  super(message, cause);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param cause исключение.
	*/
   public VisHttpException(Throwable cause) {
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
   public VisHttpException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	  super(message, cause, enableSuppression, writableStackTrace);
   }
}
