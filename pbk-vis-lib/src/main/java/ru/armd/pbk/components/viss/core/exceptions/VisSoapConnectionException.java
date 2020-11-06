package ru.armd.pbk.components.viss.core.exceptions;

/**
 * Исключение VisSoapConnectionException.
 */
public class VisSoapConnectionException
	extends VisSoapException {

   private static final long serialVersionUID = 6568365952235356567L;

   /**
	* Конструктор по умолчанию.
	*/
   public VisSoapConnectionException() {
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	*/
   public VisSoapConnectionException(String message) {
	  super(message);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	* @param cause   исключение.
	*/
   public VisSoapConnectionException(String message, Throwable cause) {
	  super(message, cause);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param cause исключение.
	*/
   public VisSoapConnectionException(Throwable cause) {
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
   public VisSoapConnectionException(String message, Throwable cause, boolean enableSuppression,
									 boolean writableStackTrace) {
	  super(message, cause, enableSuppression, writableStackTrace);
   }
}
