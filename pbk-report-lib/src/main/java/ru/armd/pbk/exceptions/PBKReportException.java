package ru.armd.pbk.exceptions;


/**
 * Общий класс исключений модуля Report.
 */
public class PBKReportException
	extends PBKException {

   /**
	* Конструктор по умолчанию.
	*/
   public PBKReportException() {
	  super();
   }

   /**
	* Конструктор.
	*
	* @param message сообщение.
	*/
   public PBKReportException(String message) {
	  super(message);
   }

   /**
	* Конструктор.
	*
	* @param cause причина.
	*/
   public PBKReportException(Throwable cause) {
	  super(cause);
   }

   /**
	* Конструктор.
	*
	* @param message сообщение.
	* @param cause   причина.
	*/
   public PBKReportException(String message, Throwable cause) {
	  super(message, cause);
   }

}
