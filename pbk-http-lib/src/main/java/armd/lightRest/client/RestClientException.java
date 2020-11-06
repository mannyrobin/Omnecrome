package armd.lightRest.client;

import armd.lightHttp.client.HttpClientException;

public class RestClientException
	extends HttpClientException {

   public static final long serialVersionUID = 42L;

   public RestClientException(String reason) {
	  super(reason);
   }

   public RestClientException(String reason, int sysCode) {
	  super(reason, sysCode);
   }

   public RestClientException(Exception reason, int sysCode) {
	  super(reason, sysCode);
   }

   public RestClientException(String reason, int sysCode, Object causeObject) {
	  super(reason, sysCode, causeObject);
   }

}
