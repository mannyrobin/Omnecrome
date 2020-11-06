package ru.armd.pbk.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Сериализатор для представления даты окончания плана.
 */
public class PlanDateSerializer
	extends JsonSerializer<Date> {

   public static final String DATE_FORMAT = "MMMM - YYYY";

   @Override
   public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
	   throws IOException, JsonProcessingException {
	  jgen.writeString((new SimpleDateFormat(DATE_FORMAT)).format(value));
   }

}