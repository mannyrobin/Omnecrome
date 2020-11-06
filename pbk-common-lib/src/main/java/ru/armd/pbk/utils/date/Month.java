package ru.armd.pbk.utils.date;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Месяцы.
 */
public enum Month {
   JANUARY("январь", "января", "Январь"),
   FEBRUARY("февраль", "февраля", "Февраль"),
   MARCH("март", "марта", "Март"),
   APRIL("апрель", "апреля", "Апрель"),
   MAY(
	   "май", "мая", "Май"),
   JUNE("июнь", "июня", "Июнь"),
   JULY("июль", "июля", "Июль"),
   AUGUST("август", "августа", "Август"),
   SEPTEMBER("сентябрь",
	   "сентября", "Сентябрь"),
   OCTOBER("октябрь", "октября", "Октябрь"),
   NOVEMBER("ноябрь", "ноября", "Ноябрь"),
   DECEMBER("декабрь", "декабря",
	   "Декабрь");

   private String name;
   private String nameGenitive;
   private String nameUpper;

   private Month(String name, String nameGenitive, String nameUpper) {
	  this.name = name;
	  this.nameGenitive = nameGenitive;
	  this.nameUpper = nameUpper;
   }

   public String getName() {
	  return name;
   }

   public String getNameGenitive() {
	  return nameGenitive;
   }

   public String getNameUpper() {
	  return nameUpper;
   }

   /**
	* Получение объекта месяца по дате.
	*
	* @param dt Дата.
	* @return
	*/
   public static Month getByDate(Date dt) {
	  if (dt == null) {
		 return null;
	  }
	  SimpleDateFormat format = new SimpleDateFormat("MM");
	  int monthNum = Integer.valueOf(format.format(dt));
	  for (Month month : Month.values()) {
		 if (month.ordinal() + 1 == monthNum) {
			return month;
		 }
	  }
	  return null;
   }

   /**
	* Получение объекта месяца по календарю.
	*
	* @param calendar Календарь.
	* @return
	*/
   public static Month getByCalendar(Calendar calendar) {
	  if (calendar == null) {
		 return null;
	  }

	  int month = calendar.get(Calendar.MONTH) - Calendar.JANUARY;
	  return values()[month];
   }
}
