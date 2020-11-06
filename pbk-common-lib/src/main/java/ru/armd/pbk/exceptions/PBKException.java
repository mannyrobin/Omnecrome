package ru.armd.pbk.exceptions;

/**
 * Базовый класс исключений.
 */
public class PBKException
	extends RuntimeException {

   private static final long serialVersionUID = -176661691912698390L;

   /**
	* Конструктор по умолчанию.
	*/
   public PBKException() {
	  super();
   }

   /**
	* Конструтор.
	*
	* @param message сообщение.
	*/
   public PBKException(String message) {
	  super(message);
   }

   /**
	* Контсруктор.
	*
	* @param cause причина.
	*/
   public PBKException(Throwable cause) {
	  super(cause);
   }

   /**
	* Констрктор.
	*
	* @param message сообщение.
	* @param cause   причина.
	*/
   public PBKException(String message, Throwable cause) {
	  super(message, cause);
   }

}
