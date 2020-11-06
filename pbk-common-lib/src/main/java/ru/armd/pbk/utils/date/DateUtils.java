package ru.armd.pbk.utils.date;

import org.apache.log4j.Logger;
import org.joda.time.DateTime;
import org.joda.time.DateTimeZone;
import org.joda.time.Duration;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static org.apache.commons.lang.time.DateUtils.isSameDay;

/**
 * Класс-утилита для работы с датами.
 */
public class DateUtils {

   public static final Logger LOGGER = Logger.getLogger(DateUtils.class);

   public static final int MILLISECONDS_IN_SECOND = 1000;

   public static final int MILLISECONDS_IN_MINUTE = 60 * MILLISECONDS_IN_SECOND;

   public static final int MILLISECONDS_IN_HOUR = 60 * MILLISECONDS_IN_MINUTE;

   public static final int MILLISECONDS_IN_DAY = 24 * MILLISECONDS_IN_HOUR;

   public static final String DATE_FORMAT = "dd.MM.yyyy";
   public static final String DATE_DM_SPACE_FORMAT = "dd MM";
   public static final String TIME_FORMAT = "HH:mm";
   public static final String DATE_TIME_FORMAT = "dd.MM.yyyy HH.mm.ss";
   public static final String DATE_AND_TIME_FORMAT = "dd.MM.yyyy_HH.mm.ss";

   public static final String DATETIME_FORMAT = "dd.MM.yyyy HH:mm";
   public static final String YEAR_FORMAT = "yyyy";
   public static final String HOUR_OF_DAY_FORMAT = "HH";
   public static final String MINUTE_FORMAT = "mm";

   private static final String DATE_WITH_MONTH_WORD = "\u00AB%s\u00BB %s %s г.";
   private static final String TIME_FORMAT_WITH_UNITS = "HH час. mm мин.";

   /**
	* Проверяет находится ли дата в диапазоне, включая границы диапазона с
	* точностью до дня.
	*
	* @param date - дата для проверки
	* @param from - левая граница
	* @param to   - правая граница
	*/
   public static boolean isDateInRange(Date date, Date from, Date to) {
	  if (date.after(from) && date.before(to)) {
		 return true;
	  }

	  return isSameDay(date, from) || isSameDay(date, to);
   }

   /**
	* Проверяет находится ли время в диапазоне, игнорируя даты (сравнивается
	* толко время).
	*
	* @param time - время для проверки
	* @param from - левая граница
	* @param to   - правая граница
	*/
   public static boolean isTimeInRange(Date time, Date from, Date to) {
	  DateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
	  String formatedTime = dateFormat.format(time);
	  String formatedFromTime = dateFormat.format(from);
	  String formatedToTime = dateFormat.format(to);

	  return formatedFromTime.compareTo(formatedTime) <= 0 && formatedTime.compareTo(formatedToTime) <= 0;
   }

   /**
	* Проверят что вермя начала раньше чем вермя окончания.
	*
	* @param from Время начала.
	* @param to   Верям окончания.
	* @return
	*/
   public static boolean isTimeFromNotAfterTimeTo(Date from, Date to) {
	  DateFormat dateFormat = new SimpleDateFormat(TIME_FORMAT);
	  String formatedFromTime = dateFormat.format(from);
	  String formatedToTime = dateFormat.format(to);

	  return formatedFromTime.compareTo(formatedToTime) <= 0;
   }

   /**
	* Сдвигает дату к началу дня. Например, дате '2013-11-14 15:30:35.734'
	* будет соответствовать дата '2013-11-14 00:00:00.000'
	*
	* @param date Дата, которую необходимо преобразовать.
	* @return Преобразованная дата.
	*/
   public static Date shiftToDayStart(Date date) {
	  if (date == null) {
		 return null;
	  }

	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(date);
	  calendar.set(Calendar.HOUR_OF_DAY, 0);
	  calendar.set(Calendar.MINUTE, 0);
	  calendar.set(Calendar.SECOND, 0);
	  calendar.set(Calendar.MILLISECOND, 0);

	  return calendar.getTime();
   }

   /**
	* Возвращает разницу в календарных днях между двумя датами, вычисленную без
	* учёта времени в рамках дня. Например, для дат '2013-11-14 23:59' и
	* '2013-11-15 00:01' разница будет равна 1, а для дат '2013-11-14 15:30' и
	* '2013-11-14 12:30' равна нулю. Если первая дата больше второй,
	* соответствующая разница возвращается с отрицательным знаком.
	*
	* @param pFirst  Первая дата.
	* @param pSecond Вторая дата.
	* @return Разница в днях между двумя датами.
	*/
   public static int getDaysDifference(Date pFirst, Date pSecond) {
	  Date first = pFirst;
	  Date second = pSecond;
	  if (first == null || second == null) {
		 return Integer.MIN_VALUE;
	  }

	  first = shiftToDayStart(first);
	  second = shiftToDayStart(second);

	  return (int) ((second.getTime() - first.getTime()) / MILLISECONDS_IN_DAY);
   }

   /**
	* Возвращает разницу в минутах между двумя датами. Если первая дата больше
	* второй, соответствующая разница возвращается с отрицательным знаком.
	*
	* @param first  Первая дата.
	* @param second Вторая дата.
	* @return Разница в днях между двумя датами.
	*/
   public static int getMinutesDifference(Date first, Date second) {
	  if (first == null || second == null) {
		 return Integer.MIN_VALUE;
	  }

	  return (int) ((second.getTime() - first.getTime()) / MILLISECONDS_IN_MINUTE);
   }

   /**
	* @param calendar Дата для проверки, представленная календарём.
	* @return <code>true</code>, если указанная дата попадает на выходной день:
	* субботу или воскресенье.
	*/
   public static boolean isWeekend(Calendar calendar) {
	  if (calendar == null) {
		 return false;
	  }

	  int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
	  return dayOfWeek == Calendar.SATURDAY || dayOfWeek == Calendar.SUNDAY;
   }

   /**
	* @param date Дата для проверки.
	* @return <code>true</code>, если указанная дата попадает на выходной день:
	* субботу или воскресенье.
	*/
   public static boolean isWeekend(Date date) {
	  if (date == null) {
		 return false;
	  }

	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(date);

	  return isWeekend(calendar);
   }

   /**
	* Возвращает дату в виде: "18" ноября 2013 г. Число заключается в
	* кавычки-ёлочки.
	*
	* @param date Дата для преобразования в строку.
	* @return Дата в виде строки.
	*/
   public static String getDateStringWithMonthWord(Date date) {
	  if (date == null) {
		 return null;
	  }

	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(date);
	  return String.format(DATE_WITH_MONTH_WORD, calendar.get(Calendar.DAY_OF_MONTH),
		  Month.getByDate(date).getNameGenitive(), calendar.get(Calendar.YEAR));
   }

   /**
	* @param date Дата.
	* @return Время в формате "12 час. 15 мин."
	*/
   public static String getTimeStringWithUnits(Date date) {
	  if (date == null) {
		 return null;
	  }

	  return new SimpleDateFormat(TIME_FORMAT_WITH_UNITS).format(date);
   }

   /**
	* @param date Дата.
	* @return Время в формате HH:mm
	*/
   public static String makeGeneralTimeFormat(Date date) {
	  if (date == null) {
		 return null;
	  }

	  return new SimpleDateFormat(TIME_FORMAT).format(date);
   }

   /**
	* @return Форматтер даты в виде дд.мм.гггг.
	*/
   public static SimpleDateFormat makeGeneralDateFormat() {
	  return new SimpleDateFormat(DATE_FORMAT);
   }

   /**
	* @return Форматтер времени в виде чч:мм.
	*/
   public static SimpleDateFormat makeGeneralTimeFormat() {
	  return new SimpleDateFormat(TIME_FORMAT);
   }

   /**
	* @return Форматтер даты-времени в виде дд.мм.гггг чч:мм.
	*/
   public static SimpleDateFormat makeGeneralDateTimeFormat() {
	  return new SimpleDateFormat(DATETIME_FORMAT);
   }

   /**
	* Переобразование даты на строку по дд.мм.гггг.
	*
	* @param date дата
	* @return
	*/
   public static String dateToString(Date date) {
	  try {
		 return makeGeneralDateFormat().format(date);
	  } catch (Exception e) {
		 return "";
	  }
   }

   /**
	* Соединянет дату и время.
	*
	* @param date Дата.
	* @param time Время.
	* @return
	*/
   public static Date combineDateTime(Date date, Date time) {
	  Calendar calendarA = Calendar.getInstance();
	  calendarA.setTime(date);
	  Calendar calendarB = Calendar.getInstance();
	  calendarB.setTime(time);

	  calendarA.set(Calendar.HOUR_OF_DAY, calendarB.get(Calendar.HOUR_OF_DAY));
	  calendarA.set(Calendar.MINUTE, calendarB.get(Calendar.MINUTE));
	  calendarA.set(Calendar.SECOND, calendarB.get(Calendar.SECOND));
	  calendarA.set(Calendar.MILLISECOND, calendarB.get(Calendar.MILLISECOND));

	  Date result = calendarA.getTime();
	  return result;
   }

   /**
	* Приведение к дате.
	*
	* @param dateStr Строка даты.
	* @return
	* @throws ParseException
	*/
   public static Date toDate(String dateStr)
	   throws ParseException {
	  SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT);
	  Date result = dateFormat.parse(dateStr);
	  if (!isDateInRange(result, dateFormat.parse("01.01.2010"), dateFormat.parse("01.01.2050"))) {
		 throw new ParseException("Дата должна быть в диапазоне от 2010 до 2050 года!", 7);
	  }
	  return result;
   }

   /**
	* Приведение к дате и веремени.
	*
	* @param dateTimeStr Строка даты и веремени.
	* @return
	* @throws ParseException
	*/
   public static Date toDateTime(String dateTimeStr)
	   throws ParseException {
	  SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_TIME_FORMAT);
	  Date result = dateFormat.parse(dateTimeStr);
	  if (!isDateInRange(result, dateFormat.parse("01.01.2010"), dateFormat.parse("01.01.2050"))) {
		 throw new ParseException("Дата должна быть в диапазоне от 2010 до 2050 года!", 7);
	  }
	  return result;
   }

   /**
	* Добавляет к дате дни.
	*
	* @param date Дата.
	* @param days Кол-во дней.
	* @return
	*/
   public static Date addDays(Date date, int days) {
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(date);
	  cal.add(Calendar.DATE, days); // minus number would decrement the days
	  return cal.getTime();
   }

   /**
	* Добавление к дате лет.
	*
	* @param date  Дата.
	* @param years Кол-во лет.
	* @return
	*/
   public static Date addYears(Date date, int years) {
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(date);
	  cal.add(Calendar.YEAR, years); // minus number would decrement the years
	  return cal.getTime();
   }

   /**
	* Возвращает веремнную зону.
	*
	* @return
	*/
   public static TimeZone getTimeZone() {
	  return TimeZone.getTimeZone("Europe/Moscow");
	  // return TimeZone.
   }

   /**
	* Возвращает веремнную зону.
	*
	* @return
	*/
   public static DateTimeZone getJodaTimeZone() {
	  // return DateTimeZone.forTimeZone(getTimeZone());
	  return DateTimeZone.forOffsetHours(4);// FIXME:get timezone universal
	  // for Moscow(+3 or +4)
   }

   /**
	* Получение наибольшей даты.
	*
	* @param dates список дат.
	*/
   public static Date greater(Date... dates) {
	  if (dates.length == 0) {
		 return null;
	  }
	  Date grather = dates[0];
	  for (int i = 1; i < dates.length; i++) {
		 if (dates[i] != null) {
			if (grather.compareTo(dates[i]) < 0) {
			   grather = dates[i];
			}
		 }
	  }
	  return grather;
   }

   /**
	* Получение наименьшей даты.
	*
	* @param dates список дат.
	*/
   public static Date less(Date... dates) {
	  if (dates.length == 0) {
		 return null;
	  }
	  Date less = dates[0];
	  for (int i = 1; i < dates.length; i++) {
		 if (dates[i] != null) {
			if (less.compareTo(dates[i]) > 0) {
			   less = dates[i];
			}
		 }
	  }
	  return less;
   }

   /**
	* Возвращает длительность в днях между датами.
	*
	* @param start Дата.
	* @param end   Дата.
	* @return
	*/
   public static Long getDurationInDays(Date start, Date end) {
	  DateTime s = new DateTime(start, getJodaTimeZone());
	  DateTime e = new DateTime(end, getJodaTimeZone());
	  return getDuration(s, e).getStandardDays() + 1;
   }

   /**
	* Возвращает длительность.
	*
	* @param start Дата.
	* @param end   Дата.
	* @return
	*/
   private static Duration getDuration(DateTime start, DateTime end) {
	  return new Duration(start, end);
   }

   /**
	* Метод проверят, что дата находится в диапазоне.
	*
	* @param date  Дата.
	* @param start Дата начала диапазона.
	* @param end   Дата окончания диапазона.
	* @return
	*/
   public static boolean inRange(Date date, Date start, Date end) {
	  return date.compareTo(start) >= 0 && date.compareTo(end) <= 0;
   }

   /**
	* Метод проверят, что дата находится в диапазоне.
	*
	* @param date  Дата.
	* @param start Дата начала диапазона.
	* @param end   Дата окончания диапазона.
	* @return
	*/
   public static boolean inRangeMonth(Date date, Date start, Date end) {
	  DateTime s = new DateTime(start, getJodaTimeZone());
	  DateTime e = new DateTime(end, getJodaTimeZone());
	  DateTime d = new DateTime(date, getJodaTimeZone());

	  if (s.getMonthOfYear() <= e.getMonthOfYear()) { // нормальный период (с
		 // 1 по 12 месяц)
		 if (d.getMonthOfYear() >= s.getMonthOfYear() && d.getMonthOfYear() <= e.getMonthOfYear()) { // дата
			// входит
			// в
			// промежуток
			// [нач.мес.,
			// кон.мес.]
			if (d.getMonthOfYear() == s.getMonthOfYear()) { // дата совпала
			   // с месяцем
			   // начала
			   // промежутка
			   if (d.getDayOfMonth() < s.getDayOfMonth()) // но число
			   // раньше
			   {
				  return false;
			   }
			}
			if (d.getMonthOfYear() == e.getMonthOfYear()) { // дата совпала
			   // с месяцем
			   // окончания
			   // промежутка
			   if (d.getDayOfMonth() > e.getDayOfMonth()) // но число позже
			   {
				  return false;
			   }
			}
			return true;
		 } else {
			return false;
		 }
	  } else { // перескок через новый год (с 11 по 4 месяц, например)
		 if (d.getMonthOfYear() < s.getMonthOfYear() && d.getMonthOfYear() > e.getMonthOfYear()) // дата
		 // на
		 // входит
		 // в
		 // промежуток
		 // [нач.мес.,
		 // кон.мес.]
		 {
			return false;
		 } else if (d.getMonthOfYear() == s.getMonthOfYear()) { // дата совпала
			// с месяцем
			// начала
			// промежутка
			if (d.getDayOfMonth() < s.getDayOfMonth()) // но число раньше
			// числа начала
			// промежутка
			{
			   return false;
			}
		 } else if (d.getMonthOfYear() == e.getMonthOfYear()) { // дата
			// совпала с
			// месяцем
			// окончания
			// промежутка
			if (d.getDayOfMonth() > e.getDayOfMonth()) // но число позже
			// числа начала
			// промежутка
			{
			   return false;
			}
		 }
		 return true;
	  }
   }

   /**
	* Возвращает год, содержащиеся в дате.
	*
	* @param date Дата.
	* @return Год, содержащиеся в дате.
	*/
   public static String getYear(Date date) {
	  if (date == null) {
		 return null;
	  }
	  return new SimpleDateFormat(YEAR_FORMAT).format(date);
   }

   /**
	* Возвращает часы, содержащиеся в дате-времени.
	*
	* @param date Дата-время.
	* @return Часы.
	*/
   public static String getHour(Date date) {
	  if (date == null) {
		 return null;
	  }
	  return new SimpleDateFormat(HOUR_OF_DAY_FORMAT).format(date);
   }

   /**
	* Возвращает минуты, содержащиеся в дате-времени.
	*
	* @param date Дата-время.
	* @return Минуты.
	*/
   public static String getMinute(Date date) {
	  if (date == null) {
		 return null;
	  }
	  return new SimpleDateFormat(MINUTE_FORMAT).format(date);
   }

   /**
	* Возвращает дату окончания периода актуальности по умолчанию.
	*
	* @return Дата 01.01.2100.
	*/
   public static Date getVersionEndDate() {
	  Calendar cal = Calendar.getInstance();
	  cal.set(2100, 0, 1);
	  return shiftToDayStart(cal.getTime());
   }

   /**
	* Сравнить дату с окончанием периода актуальности.
	*
	* @param date дата
	* @return
	*/
   public static Boolean compareWithVersionEndDate(Date date) {
	  return date.getTime() >= getVersionEndDate().getTime();
   }

   /**
	* Const.DATE_FORMAT.
	*
	* @param strDate
	* @return
	*/
   protected Date parseParamDate(String strDate) {
	  Date date = null;
	  try {
		 if (strDate != null && strDate.length() > 0) {
			date = (new SimpleDateFormat(DateUtils.DATE_FORMAT)).parse(strDate);
		 }
	  } catch (ParseException e) {
		 String errorMessage = "Неверный формат даты. Дата должна быть в формате " + DateUtils.DATE_FORMAT;
		 LOGGER.error(errorMessage, e);
	  }
	  return date;
   }

   /**
	* Const.TIME_FORMAT.
	*
	* @param strDate
	* @return
	*/
   protected Date parseParamTime(String strDate) {
	  Date date = null;
	  try {
		 if (strDate != null && strDate.length() > 0) {
			date = (new SimpleDateFormat(DateUtils.TIME_FORMAT)).parse(strDate);
		 }
	  } catch (ParseException e) {
		 String errorMessage = "Неверный формат времени. Время должно быть в формате " + DateUtils.TIME_FORMAT;
		 LOGGER.error(errorMessage, e);
	  }
	  return date;
   }

   /**
	* Перевести часы и минуты в число.
	*
	* @param date дата
	* @return
	*/
   public static Integer getIntOfHourAndMinute(Date date) {
	  Calendar cal = Calendar.getInstance();
	  cal.setTime(date);
	  return cal.get(Calendar.HOUR_OF_DAY) * 60 + cal.get(Calendar.MINUTE);
   }

   /**
	* Проверяет дату на равество с месяцем.
	*
	* @param date  дата
	* @param month месяц
	* @return
	*/
   public static Boolean checkDateMonth(Date date, int month) {
	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(date);
	  return calendar.get(Calendar.MONTH) == month;
   }

   /**
	* Возвращает значение данного календарного поля из даты.
	*
	* @param date  дата.
	* @param field календарное значение.
	* @return
	*/
   public static Integer get(Date date, int field) {
	  if (date == null) {
		 return null;
	  }

	  Calendar calendar = Calendar.getInstance();
	  calendar.setTime(date);
	  return calendar.get(field);
   }

   /**
	* Проверяет, что две даты относится к одной недели.
	*
	* @param first  первая дата.
	* @param second вторая дата.
	* @return
	*/
   public static Boolean isOneWeek(Date first, Date second) {
	  Calendar firstCal = Calendar.getInstance();
	  Calendar secondCal = Calendar.getInstance();
	  firstCal.setTime(first);
	  secondCal.setTime(second);
	  return firstCal.get(Calendar.WEEK_OF_YEAR)
		  == secondCal.get(Calendar.WEEK_OF_YEAR);
   }

   public static String getFusedDate(Date date) {
	  DateFormat df = new SimpleDateFormat("yyyyMMdd");
	  return df.format(date);
   }

   public static List<Date> getDateInRange(Date start, Date end) {
	  List<Date> result = new ArrayList<Date>();
	  Calendar c = Calendar.getInstance();
	  c.setTime(shiftToDayStart(start));
	  Calendar c2 = Calendar.getInstance();
	  c2.setTime(shiftToDayStart(end));
	  while (!c.getTime().after(c2.getTime())) {
		 result.add(c.getTime());
		 c.add(Calendar.DAY_OF_YEAR, 1);
	  }
	  return result;
   }

   /**
	* Возвращает дату первого дня текущего месяца.
	* Например: для даты 17.07.2017 вернет 01.07.2017
	*
	* @return Дата первого дня текущего месяца
	*/
   public static Date getFirstDateInCurrentMonth() {
	  Calendar c = Calendar.getInstance();
	  c.set(Calendar.DAY_OF_MONTH, 1);
	  return c.getTime();
   }

   /**
	* Возвращает дату первого дня предыдущего месяца.
	* Например: для даты 17.07.2017 вернет 01.06.2017
	*
	* @return Дата первого дня предыдущего месяца
	*/
   public static Date getFirstDateInPrevMonth() {
	  Calendar c = Calendar.getInstance();
	  c.add(Calendar.MONTH, -1);
	  c.set(Calendar.DAY_OF_MONTH, 1);
	  return c.getTime();
   }

   /**
	* Возвращает дату последнего дня предыдущего месяца.
	* Например: для даты 17.07.2017 вернет 30.06.2017
	*
	* @return
	*/
   public static Date getLastDateInPrevMonth() {
	  return addDays(getFirstDateInCurrentMonth(), -1);
   }
}
