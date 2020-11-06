package ru.armd.pbk.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Json Serializer Даты и веремени с выводом милисекунд.
 */
public class DotSeparatedDateTimeDetailedSerializer
	extends JsonSerializer<Date> {

   public static final String DATE_FORMAT = "dd.MM.yyyy";
   public static final String TIME_FORMAT = "HH:mm:ss.SSS";

   @Override
   public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
	   throws IOException, JsonProcessingException {
	  jgen.writeString(new SimpleDateFormat(DATE_FORMAT).format(value) + " "
		  + new SimpleDateFormat(TIME_FORMAT).format(value));
   }
}