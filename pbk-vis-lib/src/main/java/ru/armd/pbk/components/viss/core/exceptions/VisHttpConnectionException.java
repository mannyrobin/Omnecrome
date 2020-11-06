package ru.armd.pbk.components.viss.core.exceptions;

/**
 * Исключение VisHttpConnectionException.
 */
public class VisHttpConnectionException
	extends VisHttpException {

   private static final long serialVersionUID = -3022217182578944504L;

   /**
	* Конструктор по умолчанию.
	*/
   public VisHttpConnectionException() {
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	*/
   public VisHttpConnectionException(String message) {
	  super(message);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	* @param cause   исключение.
	*/
   public VisHttpConnectionException(String message, Throwable cause) {
	  super(message, cause);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param cause исключение.
	*/
   public VisHttpConnectionException(Throwable cause) {
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
   public VisHttpConnectionException(String message, Throwable cause, boolean enableSuppression,
									 boolean writableStackTrace) {
	  super(message, cause, enableSuppression, writableStackTrace);
   }
}
