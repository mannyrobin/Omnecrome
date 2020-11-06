package ru.armd.pbk.utils.json;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Property Editor времени.
 */
public class TimePropertyEditor
	extends PropertyEditorSupport {

   public static final String TIME_FORMAT = "HH:mm";

   @Override
   public void setAsText(String value) {
	  try {
		 setValue(new SimpleDateFormat(TIME_FORMAT).parse(value));
	  } catch (ParseException e) {
		 setValue(null);
	  }
   }

   @Override
   public String getAsText() {
	  if (getValue() == null) {
		 return "";
	  }
	  return new SimpleDateFormat(TIME_FORMAT).format(getValue());
   }
}
