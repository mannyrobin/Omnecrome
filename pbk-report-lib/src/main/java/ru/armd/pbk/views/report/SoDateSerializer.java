package ru.armd.pbk.views.report;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Сериализатор для представления даты в стандартных отчётах.
 */
public class SoDateSerializer
	extends JsonSerializer<Date> {

   public static final String DATE_FORMAT = "dd.MM.yyyy";

   /**
	* Сериализовать дату для стандартного отчёта.
	*
	* @param value    дата
	* @param jgen     json-генератор
	* @param provider провайдер сериализации
	* @throws IOException
	*/
   @Override
   public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
	   throws IOException {
	  jgen.writeString((new SimpleDateFormat(DATE_FORMAT)).format(value));
   }

}
