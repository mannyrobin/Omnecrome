package armd.lightHttp.client;

import armd.core.DateUtils;
import armd.integration.IntegrationException;
import armd.lightHttp.common.BaseRequestListener;
import armd.lightHttp.common.FileLoggerAction;
import armd.lightHttp.common.IRequestListener;
import armd.lightHttp.common.IRequestListenerContext;
import armd.lightHttp.common.IRequestLoggerAction;
import org.apache.http.HttpHost;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.conn.HttpHostConnectException;
import org.apache.http.conn.params.ConnRoutePNames;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.net.SocketTimeoutException;
import java.net.URI;
import java.util.Map;
import java.util.UUID;

/**
 * Базовый класс клиента HTTP сервиса.
 */
public abstract class BaseHttpClient
	implements IHttpClient {

   private static final Logger LOGGER = Logger.getLogger(BaseHttpClient.class);

   private static final Object objSync = new Object();

   private BaseHttpClientParameters clientParameters = null;
   private IRequestListener requestListener = null;
   private IHttpPacketProcessor packetProcessor = null;


   private String defaultEncoding = "UTF-8";

   public BaseHttpClient(BaseHttpClientParameters clientParameters, IRequestListener requestListener) {
	  setClientParameters(clientParameters);
	  if (requestListener == null) {
		 setRequestListener(new BaseRequestListener());
	  } else {
		 setRequestListener(requestListener);
	  }
   }

   @Override
   public BaseHttpClientParameters getClientParameters() {
	  return clientParameters;
   }

   @Override
   public void setClientParameters(BaseHttpClientParameters clientParameters) {
	  if (clientParameters == null) {
		 throw new UnsupportedOperationException("Client parameters can not be null");
	  }
	  synchronized (objSync) {
		 this.clientParameters = clientParameters;
		 onClientParamentersChanged();
	  }
   }

   protected void onClientParamentersChanged() {
	  if ((getClientParameters().getServiceAddress() == null) || getClientParameters().getServiceAddress().isEmpty()) {
		 throw new UnsupportedOperationException("Service address can not be null");
	  }
   }

   @Override
   public IRequestListener getRequestListener() {
	  return requestListener;
   }

   @Override
   public void setRequestListener(IRequestListener requestListener) {
	  if (requestListener == null) {
		 throw new UnsupportedOperationException("Request Listener can not be null");
	  }
	  synchronized (objSync) {
		 this.requestListener = requestListener;
//			onClientParamentersChanged();
	  }
   }

   @Override
   public IHttpPacketProcessor getPacketProcessor() {
	  return packetProcessor;
   }

   @Override
   public void setPacketProcessor(IHttpPacketProcessor packetProcessor) {
	  this.packetProcessor = packetProcessor;
   }

   @Override
   public String getEncoding() {
	  return defaultEncoding;
   }

   public String generateGUID() {
	  UUID guid = UUID.randomUUID();
	  return guid.toString().toUpperCase();
   }

   protected HttpClient createHttpClient() {
	  HttpClient httpClient = new DefaultHttpClient();
	  HttpParams httpParams = httpClient.getParams();
	  if (getClientParameters().getHttpSoTimeout() > 0) {
		 HttpConnectionParams.setSoTimeout(httpParams, getClientParameters().getHttpSoTimeout() * 1000);
	  }
	  if (getClientParameters().getHttpConnectionTimeout() > 0) {
		 HttpConnectionParams.setConnectionTimeout(httpParams, getClientParameters().getHttpConnectionTimeout() * 1000);
	  }
	  if (getClientParameters().isServiceUseProxy() && getClientParameters().getServiceProxyAddress() != null && (!getClientParameters().getServiceProxyAddress().isEmpty())) {
		 String proxyAddress = getClientParameters().getServiceProxyAddress();
		 URI proxyUri = URI.create(proxyAddress);
		 HttpHost proxy = new HttpHost(proxyUri.getHost(), proxyUri.getPort(), proxyUri.getScheme());
		 httpClient.getParams().setParameter(ConnRoutePNames.DEFAULT_PROXY, proxy);
	  }
	  return httpClient;
   }

   protected abstract HttpUriRequest createHttpRequest(IRequestListenerContext requestListenerContext, String bindAddress)
	   throws IOException;

   protected abstract void readHttpResponse(IRequestListenerContext requestListenerContext, HttpResponse response)
	   throws IOException;

   protected void prepareRequestPacket(IRequestListenerContext requestListenerContext)
	   throws Exception {
	  if (packetProcessor != null) {
		 requestListenerContext.setRawRequest(packetProcessor.prepareRequestPacket(requestListenerContext.getRequestObject()));
	  }
   }

   protected Object parseResponsePacket(IRequestListenerContext requestListenerContext)
	   throws Exception {
	  if (packetProcessor != null) {
		 return packetProcessor.parseResponsePacket(requestListenerContext.getRawResponse());
	  }
	  return requestListenerContext.getRawResponse();
   }

   protected Object call(IRequestListenerContext requestListenerContext)
	   throws Exception {
	  try {
		 requestListenerContext.registerBeginCall();
		 prepareRequestPacket(requestListenerContext);
		 callEndpoint(requestListenerContext, getClientParameters().getServiceAddress());
		 requestListenerContext.setResponseObject(parseResponsePacket(requestListenerContext));
		 return requestListenerContext.getResponseObject();
	  } finally {
		 requestListenerContext.registerEndCall();
	  }
   }

   private void callEndpoint(IRequestListenerContext requestListenerContext, String bindAddress)
	   throws IOException, HttpClientException {
	  HttpClient httpclient = createHttpClient();
	  try {
		 HttpUriRequest httpRequest = createHttpRequest(requestListenerContext, bindAddress);

		 requestListenerContext.setRequestStart(DateUtils.now());
		 requestListenerContext.registerRequest();

		 HttpResponse response = httpclient.execute(httpRequest);
		 if (response != null) {
				/*if (LOGGER.isTraceEnabled()){
					LOGGER.trace("Status line: " + response.getStatusLine());
					Header headers[] = response.getAllHeaders();
					if (headers!=null)
						LOGGER.trace("Headers:");
					for(Header h: headers){
						LOGGER.trace(h.getName() + " -> " + h.getValue());
					}
				}*/
		 } else {
			LOGGER.error("HttpResponse is null");
		 }
		 readHttpResponse(requestListenerContext, response);
	  } catch (SocketTimeoutException ste) {
		 throw new HttpClientException(ste.getMessage(), HttpClientException.TIMEOUT);
	  } catch (HttpHostConnectException hhce) {
		 throw new HttpClientException(hhce.getMessage(), HttpClientException.TIMEOUT_CONNECT);
	  } catch (Exception e) {
		 LOGGER.error(e);
		 e.printStackTrace();
		 //TODO throw  error up
	  } finally {
		 try {
			requestListenerContext.setRequestEnd(DateUtils.now());
			requestListenerContext.registerResponse();
		 } catch (IntegrationException e) {
			throw new IOException(e.getMessage(), e.getCause());
		 } finally {
			httpclient.getConnectionManager().shutdown();
		 }
	  }
   }


   protected IRequestListenerContext createRequestListenerContext(Object header, Object req, Map<String, Object> anyObjects, Long clientId, String action) {
	  IRequestListenerContext requestListenerContext = getRequestListener().createContext(clientId,
		  getAfterRegisterRequestAction(),
		  getAfterRegisterResponseAction(),
		  getAfterRegisterBeginCallAction(),
		  getAfterRegisterEndCallAction());

	  requestListenerContext.setHeaderObject(header);
	  requestListenerContext.setRequestObject(req);
	  requestListenerContext.setAction(action);

	  requestListenerContext.setAnyObjects(anyObjects);
	  return requestListenerContext;
   }

   protected IRequestLoggerAction getAfterRegisterRequestAction() {
	  if (getClientParameters() != null && getClientParameters().isLogExchangeToFiles()) {
//			return new FileLoggerAction("./soap-logs/", "request", FileLoggerAction.logRequest); //TODO chain actions instead of replace
		 return new FileLoggerAction(getClientParameters().getLogExchangeDir(), "request", FileLoggerAction.logRequest);
	  }
	  return null;
   }

   protected IRequestLoggerAction getAfterRegisterResponseAction() {
	  if (getClientParameters() != null && getClientParameters().isLogExchangeToFiles()) {
		 return new FileLoggerAction(getClientParameters().getLogExchangeDir(), "response", FileLoggerAction.logResponse);
	  }
	  return null;
   }

   protected IRequestLoggerAction getAfterRegisterBeginCallAction() {
	  return null;
   }

   protected IRequestLoggerAction getAfterRegisterEndCallAction() {
	  return null;
   }

}
