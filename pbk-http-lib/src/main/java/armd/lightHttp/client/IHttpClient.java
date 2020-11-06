package armd.lightHttp.client;

import armd.lightHttp.common.IRequestListener;

public interface IHttpClient {

   /**
	* Возвращает кодировку, в коорой работает клиент.
	*
	* @return Кодировка. По-умолчанию: UTF-8
	*/
   String getEncoding();

   /**
	* Возвращает параметры клиента.
	*
	* @return Параметры клиента.
	*/
   BaseHttpClientParameters getClientParameters();

   /**
	* Устанавливает параметры клиента.
	*
	* @param clientParameters Параметры клиента.
	*/
   void setClientParameters(BaseHttpClientParameters clientParameters);

   /**
	* Возвращает слушатель контекста.
	*
	* @return Слушатель контекста.
	*/
   IRequestListener getRequestListener();

   /**
	* Устанавливает слушатель контекста.
	*
	* @param requestListener Слушатель контекста.
	*/
   void setRequestListener(IRequestListener requestListener);

   /**
	* Возвращает процессор пакета.
	*
	* @return Процессор пакета.
	*/
   IHttpPacketProcessor getPacketProcessor();

   /**
	* Устанавливает процессор пакета.
	*
	* @param packetProcessor Процессор пакета.
	*/
   void setPacketProcessor(IHttpPacketProcessor packetProcessor);
}
