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
public class ISO8601DateSerializer
	extends JsonSerializer<Date> {
   @Override
   public void serialize(Date value, JsonGenerator gen, SerializerProvider arg2)
	   throws IOException, JsonProcessingException {
	  SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ");
	  String formattedDate = formatter.format(value);

	  gen.writeString(formattedDate);

   }
}
