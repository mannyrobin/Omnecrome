package ru.armd.pbk.components.viss.core.exceptions;

/**
 * Исключение VisFileSysException.
 */
public class VisFileSysException
	extends Exception {

   private static final long serialVersionUID = -6457692649034690577L;

   /**
	* Конструктор по умолчанию.
	*/
   public VisFileSysException() {
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	*/
   public VisFileSysException(String message) {
	  super(message);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	* @param cause   исключение.
	*/
   public VisFileSysException(String message, Throwable cause) {
	  super(message, cause);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param cause исключение.
	*/
   public VisFileSysException(Throwable cause) {
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
   public VisFileSysException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	  super(message, cause, enableSuppression, writableStackTrace);
   }
}
