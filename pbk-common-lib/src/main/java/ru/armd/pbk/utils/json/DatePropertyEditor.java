package ru.armd.pbk.utils.json;

import java.beans.PropertyEditorSupport;
import java.text.ParseException;
import java.text.SimpleDateFormat;

/**
 * Property Editor даты.
 */
public class DatePropertyEditor
	extends PropertyEditorSupport {

   public static final String DATE_FORMAT = "dd.MM.yyyy";

   @Override
   public void setAsText(String value) {
	  try {
		 setValue(new SimpleDateFormat(DATE_FORMAT).parse(value));
	  } catch (ParseException e) {
		 setValue(null);
	  }
   }

   @Override
   public String getAsText() {
	  if (getValue() == null) {
		 return "";
	  }
	  return new SimpleDateFormat(DATE_FORMAT).format(getValue());
   }
}
