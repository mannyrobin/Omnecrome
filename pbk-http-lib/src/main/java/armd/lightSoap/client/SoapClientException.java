package armd.lightSoap.client;

import armd.lightHttp.client.HttpClientException;

public class SoapClientException
	extends HttpClientException {

   public static final long serialVersionUID = 42L;

   public SoapClientException(String reason) {
	  super(reason);
   }

   public SoapClientException(String reason, int sysCode) {
	  super(reason, sysCode);
   }

   public SoapClientException(Exception reason, int sysCode) {
	  super(reason, sysCode);
   }

   public SoapClientException(String reason, int sysCode, Object causeObject) {
	  super(reason, sysCode, causeObject);
   }
}
