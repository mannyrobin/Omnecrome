package ru.armd.pbk.utils.json;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.log4j.Logger;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Json Deserializer даты.
 */
public class DotSepratedDateDeserializer
	extends JsonDeserializer<Date> {

   public static final Logger LOGGER = Logger.getLogger(DotSepratedDateDeserializer.class);
   public static final String DATE_FORMAT = "dd.MM.yyyy";

   @Override
   public Date deserialize(JsonParser jp, DeserializationContext ctxt)
	   throws IOException, JsonProcessingException {
	  SimpleDateFormat formatter = new SimpleDateFormat(DATE_FORMAT);
	  String str = jp.getText().trim();
	  if (str == null || str.isEmpty()) {
		 return null;
	  }
	  Date date = null;

	  try {
		 date = formatter.parse(str);
	  } catch (ParseException e) {
		 LOGGER.error(e.getMessage(), e);
	  }
	  return date;
   }
}
