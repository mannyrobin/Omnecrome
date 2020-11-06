package armd.lightSoap.server;


public class SoapSecurityException
	extends armd.lightSoap.server.SoapException {
   private static final long serialVersionUID = 1;

   protected int httpCode;
   private static int genericHttpCode = 403;
   private static String genericCode = "Security exception";

   public SoapSecurityException(int httpCode, String code, String message, String details, Exception ex) {
	  super(code, message, details, ex);
	  this.httpCode = httpCode;
   }

   public SoapSecurityException(String code, String message, String details, Exception ex) {
	  this(genericHttpCode, code, message, details, ex);
   }

   public SoapSecurityException(String message, String details, Exception ex) {
	  this(genericHttpCode, genericCode, message, details, ex);
   }

   public int getHttpCode() {
	  return httpCode;
   }

   public void setHttpCode(int httpCode) {
	  this.httpCode = httpCode;
   }
}
