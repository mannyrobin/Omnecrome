package armd.lightSoap.server;


public class SoapException
	extends Exception {

   private static final long serialVersionUID = 0;

   private String code = null;
   private String message = null;
   private String details = null;
   private Exception exception = null;

   public SoapException(String code, String message, String details, Exception ex) {

	  this.code = code;
	  this.message = message;
	  this.details = details;
	  this.exception = ex;

   }

   public SoapException(String code, String message, String details) {
	  this(code, message, details, null);
   }

   public SoapException(String code, String message) {
	  this(code, message, null);
   }

   public SoapException(String code) {
	  this(code, null);
   }


   public String getCode() {
	  return code;
   }

   public String getMessage() {
	  return message;
   }

   public String getDetails() {
	  return details;
   }

   public Exception getException() {
	  return exception;
   }
}
