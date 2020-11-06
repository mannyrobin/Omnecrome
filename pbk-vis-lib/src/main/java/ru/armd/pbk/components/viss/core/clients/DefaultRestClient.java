package ru.armd.pbk.components.viss.core.clients;

import armd.lightHttp.client.BaseHttpClientParameters;
import armd.lightHttp.common.IRequestListenerContext;
import armd.lightRest.client.BaseRestClient;

/**
 * Дефолтный рест клиент для обмена.
 */
public class DefaultRestClient
	extends BaseRestClient {

   IRequestListenerContext requestListenerContext;

   /**
	* Конструктор.
	*
	* @param parameters параметры.
	*/
   public DefaultRestClient(BaseHttpClientParameters parameters) {
	  super(parameters, null);
   }

   @Override
   public IRequestListenerContext createRestRequestListenerContext(Object req) {
	  requestListenerContext = super.createRestRequestListenerContext(req);
	  return requestListenerContext;
   }

   /**
	* Получить ответ.
	*
	* @return
	*/
   public byte[] getRawResponse() {
	  if (requestListenerContext != null && requestListenerContext.getAnyObjects() != null
		  && requestListenerContext.getAnyObjects().containsKey("respBytes")) {
		 return (byte[]) requestListenerContext.getAnyObjects().get("respBytes");
	  }

	  return null;
   }
}
