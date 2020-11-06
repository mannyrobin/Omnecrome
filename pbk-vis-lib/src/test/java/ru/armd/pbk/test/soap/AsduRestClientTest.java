package ru.armd.pbk.test.soap;

import armd.lightHttp.client.BaseHttpClientParameters;
import armd.lightHttp.client.HttpClientException;
import armd.lightRest.client.BaseRestClient;
import org.junit.Test;

public class AsduRestClientTest {

   @Test
   public void sendTest() {
	  BaseHttpClientParameters clientParameters = new BaseHttpClientParameters();
	  clientParameters.setServiceAddress("http://10.68.1.27/mgt/file?name=DRIVERS&type=ZIP");
	  clientParameters.setLogExchangeToFiles(true);
	  clientParameters.setLogExchangeDir("./http-logs/");

	  BaseRestClient client = new BaseRestClient(clientParameters, null) {
	  };
	  try {
		 client.callRest(null, null);
	  } catch (HttpClientException e) {
		 e.printStackTrace();
	  }
   }
}
