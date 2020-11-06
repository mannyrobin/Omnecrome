package armd.lightSoap.client;

public interface ISoapClient {

   <TResponse> TResponse callSoap(Object req, String wsMethod, Class<TResponse> expectedResult)
	   throws SoapClientException;

}
