package ru.armd.pbk.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Json Serializer даты.
 */
public class HyphenSepratedDateSerializer
	extends JsonSerializer<Date> {

   public static final String DATE_FORMAT = "yyyy-MM-dd";

   @Override
   public void serialize(Date value, JsonGenerator jgen, SerializerProvider provider)
	   throws IOException, JsonProcessingException {
	  jgen.writeString((new SimpleDateFormat(DATE_FORMAT)).format(value));
   }
}
