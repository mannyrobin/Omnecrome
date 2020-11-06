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
 * Json Deserializer времени.
 */
public class DotSepratedTimeDeserializer
	extends JsonDeserializer<Date> {

   public static final Logger LOGGER = Logger.getLogger(DotSepratedTimeDeserializer.class);
   public static final String TIME_FORMAT = "HH:mm";

   @Override
   public Date deserialize(JsonParser jp, DeserializationContext ctxt)
	   throws IOException, JsonProcessingException {
	  SimpleDateFormat formatter = new SimpleDateFormat(TIME_FORMAT);
	  String str = jp.getText().trim();
	  Date date = null;
	  try {
		 String[] tmpDate = str.split(":");
		 if (tmpDate.length != 2) {
			return date;
		 }
		 if (tmpDate[0].length() != 2) {
			return date;
		 }
		 int hours = Integer.parseInt(tmpDate[0]);
		 if (hours < 0 || hours > 23) {
			return date;
		 }
		 if (tmpDate[1].length() != 2) {
			return date;
		 }
		 int minuts = Integer.parseInt(tmpDate[1]);
		 if (minuts < 0 || minuts > 59) {
			return date;
		 }
		 date = formatter.parse(str);
	  } catch (ParseException e) {
		 LOGGER.error(e.getMessage(), e);
	  }

	  return date;
   }
}
