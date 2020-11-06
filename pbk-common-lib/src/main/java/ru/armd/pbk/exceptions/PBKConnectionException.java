package ru.armd.pbk.exceptions;

/**
 * Ошибка подключения к сторонним системам(rest, soap...).
 */
public class PBKConnectionException
	extends PBKException {

   private static final long serialVersionUID = -887581428088534038L;

   /**
	* Конструктор по умолчанию.
	*/
   public PBKConnectionException() {
   }

   /**
	* Конструтор.
	*
	* @param message сообщение.
	*/
   public PBKConnectionException(String message) {
	  super(message);
   }

   /**
	* Констрктор.
	*
	* @param message сообщение.
	* @param cause   причина.
	*/
   public PBKConnectionException(String message, Throwable cause) {
	  super(message, cause);
   }

   /**
	* Контсруктор.
	*
	* @param cause причина.
	*/
   public PBKConnectionException(Throwable cause) {
	  super(cause);
   }

}
