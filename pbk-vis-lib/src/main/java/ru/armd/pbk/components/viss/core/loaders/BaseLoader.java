package ru.armd.pbk.components.viss.core.loaders;

import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.utils.StringUtils;
import ru.armd.pbk.utils.date.DateUtils;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BaseLoader
	extends BaseComponent {

   private DateFormat hourMinuteFormat = new SimpleDateFormat("HH:mm");

   protected Double getDoubleValue(String field) {
	  if (StringUtils.isBlank(field) || "н/д".equalsIgnoreCase(field) || "null".equalsIgnoreCase(field)) {
		 return null;
	  }
	  try {
		 String str = getStringValue(field);
		 return str == null ? null : Double.parseDouble(str);
	  } catch (Exception e) {
		 getLogger().error(e.getMessage(), e);
	  }
	  return null;
   }

   protected Integer getIntegerValue(String field) {
	  if (StringUtils.isBlank(field) || "н/д".equalsIgnoreCase(field) || "null".equalsIgnoreCase(field)) {
		 return null;
	  }
	  try {
		 String str = getStringValue(field);
		 return str == null ? null : Integer.parseInt(str);
	  } catch (Exception e) {
		 getLogger().error(e.getMessage(), e);
	  }
	  return null;
   }

   protected Long getLongValue(String field) {
	  if (StringUtils.isBlank(field) || "н/д".equalsIgnoreCase(field) || "null".equalsIgnoreCase(field)) {
		 return null;
	  }
	  try {
		 String str = getStringValue(field);
		 return str == null ? null : Long.parseLong(str);
	  } catch (Exception e) {
		 getLogger().error(e.getMessage(), e);
	  }
	  return null;
   }

   protected String getStringValue(String field) {
	  if (StringUtils.isBlank(field) || "н/д".equalsIgnoreCase(field) || "null".equalsIgnoreCase(field)) {
		 return null;
	  }
	  return field;
   }

   protected Integer getIntegerValueOfHourAndMinute(Date workDate, String field, DateFormat df) {
	  String[] dateTime = field.split(" ");
	  Date date = getDateValue(dateTime[0], df);
	  Date time = getDateValue(dateTime[1], hourMinuteFormat);
	  if (time == null || date == null) {
		 return null;
	  }
	  return workDate.before(date) ? DateUtils.getIntOfHourAndMinute(time) + 1440 : DateUtils.getIntOfHourAndMinute(time);
   }

   protected Integer getIntegerValueOfHourAndMinute(String field) {
	  String[] result = field.split(":");
	  return Integer.parseInt(result[0]) * 60 + Integer.parseInt(result[1]);
   }

   protected Date getDateValue(String field, DateFormat df) {
	  try {
		 String str = getStringValue(field);
		 return str == null ? null : df.parse(str);
	  } catch (Exception e) {
		 getLogger().error(e.getMessage(), e);
	  }
	  return null;
   }

}
