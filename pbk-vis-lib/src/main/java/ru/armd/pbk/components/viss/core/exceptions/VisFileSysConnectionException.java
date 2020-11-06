package ru.armd.pbk.components.viss.core.exceptions;

/**
 * Исключение VisFileSysConnectionException.
 */
public class VisFileSysConnectionException
	extends VisFileSysException {

   private static final long serialVersionUID = 3835474182874262749L;

   /**
	* Конструктор по умолчанию.
	*/
   public VisFileSysConnectionException() {
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	*/
   public VisFileSysConnectionException(String message) {
	  super(message);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param message сообщение.
	* @param cause   исключение.
	*/
   public VisFileSysConnectionException(String message, Throwable cause) {
	  super(message, cause);
   }

   /**
	* Конструктор с параметрами.
	*
	* @param cause исключение.
	*/
   public VisFileSysConnectionException(Throwable cause) {
	  super(cause);
   }
}
