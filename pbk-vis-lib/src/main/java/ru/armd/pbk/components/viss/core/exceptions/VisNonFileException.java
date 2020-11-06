package ru.armd.pbk.components.viss.core.exceptions;

/**
 * Исключение VisNonFileException.
 */
public class VisNonFileException
	extends Exception {

   private static final long serialVersionUID = -2850135105979817245L;

   /**
	* Конструктор по умолчанию.
	*/
   public VisNonFileException() {
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	*/
   public VisNonFileException(String message) {
	  super(message);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	* @param cause   исключение.
	*/
   public VisNonFileException(String message, Throwable cause) {
	  super(message, cause);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param cause исключение.
	*/
   public VisNonFileException(Throwable cause) {
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
   public VisNonFileException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
	  super(message, cause, enableSuppression, writableStackTrace);
   }

}
