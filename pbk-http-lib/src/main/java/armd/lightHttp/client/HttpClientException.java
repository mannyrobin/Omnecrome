package armd.lightHttp.client;


public class HttpClientException
	extends Exception {

   public static final long serialVersionUID = 42L;

   private int sysCode = -1;
   public static final int BAD_INPUT_DATA = 1;
   public static final int UNEXPECTED = 2;
   public static final int PROCESSING_EXCEPTION = 3;
   public static final int PROCESSING_ERROR = 4;
   public static final int SOAP_FAULT = 5;
   public static final int SERVER_ERROR_MESSAGE = 6;
   public static final int INTEGRATION_ERROR = 7;
   public static final int TIMEOUT = 8;
   public static final int TIMEOUT_CONNECT = 9;

   private Object causeObject = null;

   public HttpClientException(String reason) {
	  super(reason);
   }

   public HttpClientException(String reason, int sysCode) {
	  super(reason);
	  this.sysCode = sysCode;
   }

   public HttpClientException(Exception reason, int sysCode) {
	  super(reason);
	  this.sysCode = sysCode;
   }

   public HttpClientException(String reason, int sysCode, Object causeObject) {
	  this(reason, sysCode);
	  this.causeObject = causeObject;
   }

   public int getSysCode() {
	  return sysCode;
   }

   public void setSysCode(int sysCode) {
	  this.sysCode = sysCode;
   }

   public Object getCauseObject() {
	  return causeObject;
   }

   public void setCauseObject(Object causeObject) {
	  this.causeObject = causeObject;
   }
}
