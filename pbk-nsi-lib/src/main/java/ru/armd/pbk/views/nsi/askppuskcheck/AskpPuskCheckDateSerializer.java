package ru.armd.pbk.views.nsi.askppuskcheck;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Сериализатор для представления дат периода проверки в данных из АСКП.
 */
public class AskpPuskCheckDateSerializer
	extends JsonSerializer<Date> {

   public static final String DATE_FORMAT = "dd.MM.yyyy HH:mm:ss";

   @Override
   public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
	   throws IOException {
	  jgen.writeString((new SimpleDateFormat(DATE_FORMAT)).format(value));
   }

}
