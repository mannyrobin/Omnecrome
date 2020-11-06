package armd.lightRest.client;

import armd.lightHttp.client.HttpClientException;

public interface IRestClient {

   <TResponse> TResponse callRest(Object req, Class<TResponse> expectedResult)
	   throws HttpClientException;
}
