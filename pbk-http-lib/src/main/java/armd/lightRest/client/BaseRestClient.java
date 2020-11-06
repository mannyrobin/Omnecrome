package armd.lightRest.client;

import armd.lightHttp.client.BaseHttpClient;
import armd.lightHttp.client.BaseHttpClientParameters;
import armd.lightHttp.client.HttpClientException;
import armd.lightHttp.common.FileLoggerAction;
import armd.lightHttp.common.IRequestListener;
import armd.lightHttp.common.IRequestListenerContext;
import armd.lightHttp.common.IRequestLoggerAction;
import armd.lightHttp.common.ResultObjectWrapper;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.auth.UsernamePasswordCredentials;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.auth.BasicScheme;
import org.apache.log4j.Logger;

import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;

public abstract class BaseRestClient
	extends BaseHttpClient
	implements IRestClient {

   private static final Logger LOGGER = Logger.getLogger(BaseRestClient.class);

   public BaseRestClient(BaseHttpClientParameters clientParameters, IRequestListener requestListener) {
	  super(clientParameters, requestListener);
   }


   @Override
   protected HttpUriRequest createHttpRequest(IRequestListenerContext requestListenerContext, String bindAddress)
	   throws IOException {
	  HttpGet httpGet = new HttpGet(bindAddress);

	  if (getClientParameters().getServiceUsername() != null && !getClientParameters().getServiceUsername().isEmpty()) {
		 UsernamePasswordCredentials credentials = new UsernamePasswordCredentials(getClientParameters().getServiceUsername(), getClientParameters().getServicePassword());
		 httpGet.addHeader(BasicScheme.authenticate(credentials, "US-ASCII", false));
	  }

	  return httpGet;
   }

   @Override
   protected void readHttpResponse(IRequestListenerContext requestListenerContext, HttpResponse response)
	   throws IOException {
	  HttpEntity entity = response.getEntity();
	  if (entity != null) {

		 BufferedInputStream bis = new BufferedInputStream(entity.getContent());
		 ByteArrayOutputStream bos = new ByteArrayOutputStream();
		 int inByte;
		 while ((inByte = bis.read()) != -1) {
			bos.write(inByte);
		 }
		 if (requestListenerContext.getAnyObjects() == null) {
			requestListenerContext.setAnyObjects(new HashMap<String, Object>());
		 }
		 requestListenerContext.getAnyObjects().put("respBytes", bos.toByteArray());
		 bis.close();
		 bos.close();

		 try {
			requestListenerContext.setRawResponse(
				new String((byte[]) requestListenerContext.getAnyObjects().get("respBytes"), getEncoding()));
		 } catch (Throwable t) {
		 }
	  }
   }

   @Override
   public <TResponse> TResponse callRest(Object req, Class<TResponse> expectedResult)
	   throws HttpClientException {
	  IRequestListenerContext requestListenerContext = createRestRequestListenerContext(req);
	  try {
		 Object response = call(requestListenerContext);
		 if (response == null) {
			throw new HttpClientException("null as result ", HttpClientException.UNEXPECTED);
		 }
		 if (expectedResult != null) {
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
			throw new HttpClientException("Unexpected object type: " + response.getClass().getName(), HttpClientException.PROCESSING_ERROR);
		 }
		 return null;
	  } catch (Exception e) {
		 LOGGER.error(e);
		 throw new HttpClientException(e, (e instanceof HttpClientException) ? ((HttpClientException) e).getSysCode() : HttpClientException.PROCESSING_ERROR);
	  } finally {
	  }
   }

   public IRequestListenerContext createRestRequestListenerContext(Object req) {
	  return createRequestListenerContext(null, req, null, getClientParameters().getClientId(), null);
   }

   @Override
   protected IRequestLoggerAction getAfterRegisterRequestAction() {
	  if (getClientParameters() != null && getClientParameters().isLogExchangeToFiles()) {
		 return new RestLoggerAction(getClientParameters().getLogExchangeDir(), "request", FileLoggerAction.logRequest);
	  }
	  return null;
   }

   @Override
   protected IRequestLoggerAction getAfterRegisterResponseAction() {
	  if (getClientParameters() != null && getClientParameters().isLogExchangeToFiles()) {
		 return new RestLoggerAction(getClientParameters().getLogExchangeDir(), "response", RestLoggerAction.logResponse);
	  }
	  return null;
   }
}
