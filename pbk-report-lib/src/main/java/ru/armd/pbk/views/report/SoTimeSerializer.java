package ru.armd.pbk.views.report;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Сериализатор для представления времени в стандартных отчётах.
 */
public class SoTimeSerializer
	extends JsonSerializer<Date> {

   public static final String TIME_FORMAT = "HH:mm";

   /**
	* Сериализовать время для стандартного отчёта.
	*
	* @param value    время
	* @param jgen     json-генератор
	* @param provider провайдер сериализации
	* @throws IOException
	*/
   @Override
   public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
	   throws IOException {
	  jgen.writeString((new SimpleDateFormat(TIME_FORMAT)).format(value));
   }

}
