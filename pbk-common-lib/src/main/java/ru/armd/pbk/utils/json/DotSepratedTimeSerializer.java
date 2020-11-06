package ru.armd.pbk.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Json Serializer времени.
 */
public class DotSepratedTimeSerializer
	extends JsonSerializer<Date> {

   public static final String TIME_FORMAT = "HH:mm";

   @Override
   public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
	   throws IOException, JsonProcessingException {
	  jgen.writeString(new SimpleDateFormat(TIME_FORMAT).format(value));
   }
}
