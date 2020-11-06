package ru.armd.pbk.components.viss.dvr.records;

import armd.lightHttp.client.BaseHttpClientParameters;
import armd.lightHttp.common.IRequestListenerContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import ru.armd.pbk.components.viss.core.clients.DefaultRestClient;

/**
 * Рест клиент для записей с видеорегистраторов.
 */
public class DvrRecordRestClient
	extends DefaultRestClient {

   /**
	* Конструктор.
	*
	* @param parameters - параметры.
	*/
   public DvrRecordRestClient(BaseHttpClientParameters parameters) {
	  super(parameters);
   }

   /**
	* Вызвать рест запрос.
	*
	* @return - ответ.
	* @throws Exception - исполючение.
	*/
   public Object callRest()
	   throws Exception {
	  IRequestListenerContext requestListenerContext = createRestRequestListenerContext(null);
	  Object response = call(requestListenerContext);
	  JsonParser parser = new JsonParser();
	  JsonElement jsonElement = parser.parse(response.toString());
	  int code = jsonElement.getAsJsonObject().getAsJsonObject("error").get("code").getAsInt();
	  return code == 0 ? response : null;
   }
}
