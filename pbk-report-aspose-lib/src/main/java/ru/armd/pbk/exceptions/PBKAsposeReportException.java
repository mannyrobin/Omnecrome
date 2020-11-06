package ru.armd.pbk.exceptions;


public class PBKAsposeReportException
	extends PBKException {

   /**
	* Конструктор по умолчанию.
	*/
   public PBKAsposeReportException() {
	  super();
   }

   /**
	* Конструтор.
	*
	* @param message сообщение.
	*/
   public PBKAsposeReportException(String message) {
	  super(message);
   }

   /**
	* Контсруктор.
	*
	* @param cause причина.
	*/
   public PBKAsposeReportException(Throwable cause) {
	  super(cause);
   }

   /**
	* Констрктор.
	*
	* @param message сообщение.
	* @param cause   причина.
	*/
   public PBKAsposeReportException(String message, Throwable cause) {
	  super(message, cause);
   }

}
