package ru.armd.pbk.utils.json;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * Created by Yakov Volkov.
 */
public class DotSeparatedListDateTimeSerializer
	extends JsonSerializer<List<Date>> {

   public static final String DATE_FORMAT = "dd.MM.yyyy";
   public static final String TIME_FORMAT = "HH:mm";

   @Override
   public void serialize(List<Date> values, JsonGenerator jgen, SerializerProvider provider)
	   throws IOException {
	  jgen.writeStartArray();
	  for (Date value : values) {
		 jgen.writeString(new SimpleDateFormat(DATE_FORMAT).format(value) + " "
			 + new SimpleDateFormat(TIME_FORMAT).format(value));
	  }
	  jgen.writeEndArray();
   }
}