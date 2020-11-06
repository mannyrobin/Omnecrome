package armd.lightSoap.client;


import armd.lightHttp.client.BaseHttpClient;
import armd.lightHttp.common.IRequestListener;
import armd.lightHttp.common.IRequestListenerContext;
import armd.lightHttp.common.ResultObjectWrapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import java.io.IOException;

/**
 * Базовый класс SOAP клиентов.
 */
public abstract class BaseSoapClient
	extends BaseHttpClient
	implements ISoapClient {

   private static final Logger LOGGER = Logger.getLogger(BaseSoapClient.class);

   /**
	* Конструктор.
	*
	* @param clientParameters Параметры клиента сервиса. Не может быть null.
	*/
   public BaseSoapClient(BaseSoapClientParameters clientParameters, IRequestListener requestListener) {
	  super(clientParameters, requestListener);
   }


   @Override
   protected HttpUriRequest createHttpRequest(IRequestListenerContext requestListenerContext, String bindAddress)
	   throws IOException {
	  HttpPost httpPost = new HttpPost(bindAddress);

	  StringEntity stringEntity = new StringEntity(requestListenerContext.getRawRequest(), getEncoding());
	  stringEntity.setChunked(false);
	  stringEntity.setContentType("text/xml;charset=" + getEncoding());
	  httpPost.setEntity(stringEntity);

	  httpPost.addHeader("SOAPAction", requestListenerContext.getAction());

	  if (getClientParameters().getServiceUsername() != null && !getClientParameters().getServiceUsername().isEmpty()) {
		 UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(getClientParameters().getServiceUsername(), getClientParameters().getServicePassword());
		 httpPost.addHeader(BasicScheme.authenticate(credentials, "US-ASCII", false));
	  }

	  return httpPost;
   }

   @Override
   protected void readHttpResponse(IRequestListenerContext requestListenerContext, HttpResponse response)
	   throws IOException {
	  HttpEntity entity = response.getEntity();
	  if (entity != null) {
		 requestListenerContext.setRawResponse(EntityUtils.toString(entity, getEncoding()));
	  }
   }


   @Override
   public <TResponse> TResponse callSoap(Object req, String wsMethod, Class<TResponse> expectedResult)
	   throws SoapClientException {
	  IRequestListenerContext requestListenerContext = createSoapRequestListenerContext(req, wsMethod);
	  try {
		 Object response = call(requestListenerContext);
		 if (response == null) {
			throw new SoapClientException("null as result ", SoapClientException.UNEXPECTED);
		 }
		 if (expectedResult.isInstance(response)) {
			@SuppressWarnings("unchecked")
			TResponse formattedResponse = (TResponse) response;
			return formattedResponse;
		 }
		 if (ResultObjectWrapper.class.isInstance(response) && expectedResult.isInstance(((ResultObjectWrapper) response).getObj())) {
			@SuppressWarnings("unchecked")
			TResponse formattedResponse = (TResponse) ((ResultObjectWrapper) response).getObj();
			return formattedResponse;
		 }
		 throw new SoapClientException("Unexpected object type: " + response.getClass().getName(), SoapClientException.PROCESSING_ERROR);
	  } catch (Exception e) {
		 LOGGER.error(e);
		 throw new SoapClientException(e, (e instanceof SoapClientException) ? ((SoapClientException) e).getSysCode() : SoapClientException.PROCESSING_ERROR);
	  } finally {
	  }
   }

   public IRequestListenerContext createSoapRequestListenerContext(Object req, String wsMethod) {
	  return createRequestListenerContext(null, req, null, getClientParameters().getClientId(), wsMethod);
   }
}
