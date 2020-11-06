package ru.armd.pbk.components.viss.core.clients;

import armd.lightHttp.common.IRequestListenerContext;
import armd.lightSoap.client.BaseSoapClient;
import armd.lightSoap.client.BaseSoapClientParameters;

/**
 * СОАП клиент для обмена по умолчанию.
 */
public class DefaultSoapClient
	extends BaseSoapClient {

   IRequestListenerContext requestListenerContext;

   /**
	* Конструктор.
	*
	* @param parameters параметры.
	*/
   public DefaultSoapClient(BaseSoapClientParameters parameters) {
	  super(parameters, null);
   }

   @Override
   public IRequestListenerContext createSoapRequestListenerContext(Object req, String wsMethod) {
	  requestListenerContext = super.createSoapRequestListenerContext(req, wsMethod);
	  return requestListenerContext;
   }

   /**
	* Получить ответ.
	*
	* @return
	*/
   public String getRawResponse() {
	  if (requestListenerContext != null) {
		 return requestListenerContext.getRawResponse();
	  }

	  return null;
   }
}
