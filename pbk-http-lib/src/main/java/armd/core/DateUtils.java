package armd.core;

import org.joda.time.DateTime;
import org.joda.time.DateTimeUtils;
import org.joda.time.DateTimeZone;
import org.joda.time.LocalDateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.ISODateTimeFormat;

import javax.xml.datatype.DatatypeConfigurationException;
import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;
import java.sql.Timestamp;
import java.text.DateFormatSymbols;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.GregorianCalendar;

/**
 */
public class DateUtils {

   public static final long ONE_HOUR_IN_SEC = 3600L;
   public static final long ONE_DAY_IN_SEC = ONE_HOUR_IN_SEC * 24L;

   private static final String[] RUSSIAN_MONTHS = {
	   "января",
	   "февраля",
	   "марта",
	   "апреля",
	   "мая",
	   "июня",
	   "июля",
	   "августа",
	   "сентября",
	   "октября",
	   "ноября",
	   "декабря"
   };

   public static String format(Date date, String format) {
	  if (date == null) {
		 return "";
	  } else {
		 return new DateTime(date.getTime()).toString(format);
	  }
   }


   public static String formatToXml(Date date) {

	  return new DateTime(date.getTime()).toString(ISODateTimeFormat.dateTimeNoMillis().withZoneUTC());
   }


   public static String formatRus(Date date, String format) {
	  DateFormatSymbols russianSymbols = new DateFormatSymbols();
	  russianSymbols.setMonths(RUSSIAN_MONTHS);
	  SimpleDateFormat dateFormat = new SimpleDateFormat(format, russianSymbols);
	  return dateFormat.format(date);
   }

   public static java.sql.Date parseSqlDate(String text, String format) {
	  return new java.sql.Date(LocalDateTime.parse(text, DateTimeFormat.forPattern(format)).toDate().getTime());
   }

   public static java.sql.Timestamp parseSqlTimestamp(String text, String format) {
	  return new Timestamp(LocalDateTime.parse(text, DateTimeFormat.forPattern(format)).toDateTime().getMillis());
   }

   public static java.sql.Timestamp addTimestamp(Timestamp source, Timestamp value) {
	  return new Timestamp(source.getTime() + value.getTime());
   }

   public static java.sql.Timestamp parseTime(String t) {
	  return new Timestamp(LocalDateTime.parse(t, DateTimeFormat.forPattern("HH:mm")).toDateTime(DateTimeZone.forOffsetHours(0)).getMillis());
   }

   public static long currentTimeMillis() {
	  return DateTimeUtils.currentTimeMillis();
   }

   public static java.sql.Timestamp now() {
	  return new Timestamp(currentTimeMillis());
   }

   public static java.util.Date currentDate() {
	  return LocalDateTime.now().toDate();
   }

   public static java.sql.Date currentSqlDate() {
	  return new java.sql.Date(currentTimeMillis());
   }

   public static java.sql.Date toSqlDate(XMLGregorianCalendar calendar) {
	  return (calendar == null)
		  ? null : new java.sql.Date(calendar.toGregorianCalendar().getTime().getTime());
   }

   public static XMLGregorianCalendar toXMLGregorianCalendar(java.sql.Date date)
	   throws DatatypeConfigurationException {
	  GregorianCalendar calendar = new GregorianCalendar();
	  calendar.setTime(date);

	  return DatatypeFactory.newInstance().newXMLGregorianCalendar(calendar);
   }

   public static class InvalidDateTimeFormat
	   extends Exception {

	  private static final long serialVersionUID = 0;

	  private String text;
	  private String format;
	  private String fieldName;

	  public InvalidDateTimeFormat(String text, String format, String fieldName, Throwable throwable) {
		 super(throwable);
		 this.text = text;
		 this.format = format;
		 this.fieldName = fieldName;
	  }
   }
}
