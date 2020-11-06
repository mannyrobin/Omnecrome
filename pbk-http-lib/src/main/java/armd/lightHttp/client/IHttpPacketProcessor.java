package armd.lightHttp.client;

/**
 * Интерфейс процессора пакета.
 */
public interface IHttpPacketProcessor {

   /**
	* Подготавливает пакет к отправке. Преобразует объект в строку.
	*
	* @param in Объект для отправки.
	* @return Строка из объекта для пакета.
	* @throws Exception
	*/
   String prepareRequestPacket(Object in)
	   throws Exception;

   /**
	* Разбирает ответ пакета и предобразует в объект.
	*
	* @param in Строка ответа пакета.
	* @return Объект ответа.
	* @throws Exception
	*/
   Object parseResponsePacket(String in)
	   throws Exception;


//	String prepareRequestPacket(Object header, Object in) throws Exception;
//	String prepareRequestPacket(Object header, Object in, Map<String, Object> anyObjects) throws Exception;

}
