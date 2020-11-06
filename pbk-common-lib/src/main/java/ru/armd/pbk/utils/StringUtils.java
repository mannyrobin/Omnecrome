package ru.armd.pbk.utils;

import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.utils.date.Month;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Класс-утилита для работы со строками.
 */
public class StringUtils {

   public static final int X_SHORT_INPUT_SIZE = 32;
   public static final int XX_SHORT_INPUT_SIZE = 64;
   public static final int SHORT_INPUT_SIZE = 128;
   public static final int MIDDLE_INPUT_SIZE = 256;
   public static final int LONG_INPUT_SIZE = 512;
   public static final int X_LONG_INPUT_SIZE = 1024;
   public static final int MAX_INPUT_SIZE = 4000;

   public static final String CRLF = "\r\n";
   public static final char COMMA = ',';
   public static final char COLON = ':';
   public static final String DIGITS_REGEXP = "\\d+";
   public static final String DIGITS_INT_RANGE_REGEXP = "\\d{0,10}";
   public static final HashMap<String, String> TRANSLIT = new HashMap<String, String>() {
	  {
		 put("а", "a");
		 put("б", "b");
		 put("в", "v");
		 put("г", "g");
		 put("д", "d");
		 put("е", "e");
		 put("ё", "yo");
		 put("ж", "zh");
		 put("з", "z");
		 put("и", "i");
		 put("й", "j");
		 put("к", "k");
		 put("л", "l");
		 put("м", "m");
		 put("н", "n");
		 put("о", "o");
		 put("п", "p");
		 put("р", "r");
		 put("с", "s");
		 put("т", "t");
		 put("у", "u");
		 put("ф", "f");
		 put("х", "h");
		 put("ц", "c");
		 put("ч", "ch");
		 put("ш", "sh");
		 put("щ", "shh");
		 put("ъ", "");
		 put("ы", "y");
		 put("ь", "");
		 put("э", "e");
		 put("ю", "yu");
		 put("я", "ya");
	  }
   };
   public static final String CHECK_MARK = "\u2714";
   public static final String CROSS_MARK = "\u2718";
   private static SimpleDateFormat dateTimeFormat = new SimpleDateFormat("dd.MM.yyyy HH:mm");
   private static char symbolForFileName = '_';

   /**
	* Проверяет, что строка пуста.
	*
	* @param cs Строка.
	* @return
	*/
   public static boolean isBlank(CharSequence cs) {
	  int strLen;
	  if (cs == null || (strLen = cs.length()) == 0) {
		 return true;
	  }
	  for (int i = 0; i < strLen; i++) {
		 if (Character.isWhitespace(cs.charAt(i)) == false) {
			return false;
		 }
	  }
	  return true;
   }

   /**
	* Приведение строки.
	*
	* @param str Строка
	* @return
	*/
   public static String generateStringForLikeExpression(String str) {
	  return str.trim().replace("/", "//").replaceAll("'", "''").replace("_", "/_").replace("%", "/%");
   }

   /**
	* Проверяет строку на null. Если срока пуста, то возвращает значение по
	* умолванию.
	*
	* @param cs       Строка.
	* @param defValue Значение по умолванию.
	* @return
	*/
   public static CharSequence nvl(CharSequence cs, CharSequence defValue) {
	  return isBlank(cs) ? defValue : cs;
   }

   /**
	* Проверяет строку на null. Если срока пуста, то возвращает значение по
	* умолванию иначе значение @param value.
	*
	* @param cs       Строка.
	* @param value    Значение.
	* @param defValue Значение по умолванию.
	* @return
	*/
   public static CharSequence nvl(CharSequence cs, CharSequence value, CharSequence defValue) {
	  return isBlank(cs) ? defValue : value;
   }

   /**
	* Проверяет значение параметра value на null. Если null, то возвращает
	* значение по умолванию.
	*
	* @param <T>      Тип значения.
	* @param value    Значение.
	* @param defValue Значение по умолванию.
	* @return
	*/
   public static <T extends Object> CharSequence nvl(T value, CharSequence defValue) {
	  return value == null ? defValue : value.toString();
   }

   /**
	* Whitespace-символы в начале и в конце строки будут удалены.
	*
	* @param s Строка.
	* @return
	*/
   public static String trimString(String s) {
	  return s == null ? s : s.trim();
   }

   /**
	* Пример: строка "сТраННиК" будет преобразована к строке "Странник".
	* Whitespace-символы в начале и в конце строки будут удалены.
	*
	* @param str Строка для преобразования.
	* @return Преобразованная строка.
	*/
   public static String makeLowerCaseWithUpperFirst(String str) {
	  String string = str;
	  if (string == null || (string = string.trim()).isEmpty()) {
		 return string;
	  }

	  return string.substring(0, 1).toUpperCase() + string.substring(1).toLowerCase();
   }

   /**
	* @param pStartDate  Дата начала интервала.
	* @param pFinishDate Дата окончания интервала.
	* @return Соответствующая надпись для интервала, содержащего заданный, в
	* виде "декабрь 2013 года", "декабрь 2013 года - январь 2014 года".
	*/
   public static String makeMonthYearCaption(Date pStartDate, Date pFinishDate) {
	  Date startDate = pStartDate;
	  Date finishDate = pFinishDate;
	  if (startDate == null || finishDate == null) {
		 return null;
	  }
	  if (startDate.after(finishDate)) {
		 Date tmp = startDate;
		 startDate = finishDate;
		 finishDate = tmp;
	  }

	  String startMonth = Month.getByDate(startDate).getName().toLowerCase();
	  String startYear = DateUtils.getYear(startDate);
	  String finishMonth = Month.getByDate(finishDate).getName().toLowerCase();
	  String finishYear = DateUtils.getYear(finishDate);

	  StringBuilder builder = new StringBuilder();
	  if (startYear.equals(finishYear)) {
		 if (startMonth.equals(finishMonth)) {
			builder.append(startMonth);
		 } else {
			builder.append(startMonth).append(" - ").append(finishMonth);
		 }

		 builder.append(' ').append(startYear).append(" года");
	  } else {
		 builder.append(startMonth).append(' ').append(startYear).append(" года - ").append(finishMonth).append(' ')
			 .append(finishYear).append(" года");
	  }

	  return builder.toString();
   }

   /**
	* Метод формирует строку с назвнаием месяца и года.
	*
	* @param startDateStr  startDateStr.
	* @param finishDateStr finishDateStr.
	* @return
	*/
   public static String makeMonthYearCaption(String startDateStr, String finishDateStr) {
	  if (StringUtils.isBlank(startDateStr) || StringUtils.isBlank(finishDateStr)) {
		 return null;
	  }
	  Date startDate;
	  Date finishDate;
	  try {
		 SimpleDateFormat parser = DateUtils.makeGeneralDateFormat();
		 startDate = parser.parse(startDateStr);
		 finishDate = parser.parse(finishDateStr);
	  } catch (ParseException e) {
		 return null;
	  }

	  if (startDate.after(finishDate)) {
		 Date tmp = startDate;
		 startDate = finishDate;
		 finishDate = tmp;
	  }

	  String startMonth = Month.getByDate(startDate).getName();
	  String startYear = DateUtils.getYear(startDate);
	  String finishMonth = Month.getByDate(finishDate).getName();
	  String finishYear = DateUtils.getYear(finishDate);

	  StringBuilder builder = new StringBuilder();
	  if (startYear.equals(finishYear) && startMonth.equals(finishMonth)) {
		 builder.append(startMonth);
		 builder.append(' ').append(startYear).append(" г.");
	  } else {
		 builder.append(startMonth).append(' ').append(startYear).append(" г. - ").append(finishMonth).append(' ')
			 .append(finishYear).append(" г.");
	  }

	  return builder.toString();
   }

   /**
	* Находит в строке первое целое число.
	*
	* @param string Строка для поиска.
	* @return Найденное целое число в виде строки или {@code null}.
	*/
   public static String extractFirstInteger(String string) {
	  if (string == null) {
		 return null;
	  }

	  Pattern pattern = Pattern.compile(StringUtils.DIGITS_REGEXP);
	  Matcher matcher = pattern.matcher(string);
	  if (matcher.find()) {
		 return matcher.group();
	  }

	  return null;
   }

   /**
	* Находит в строке первое целое число.
	*
	* @param string Строка для поиска.
	* @return Найденное целое число в формате целого числа
	*/
   public static int extractFirstIntegerInInt(String string) {
	  return extractFirstInteger(string) == null ? 0 : Integer.parseInt(extractFirstInteger(string));
   }

   /**
	* Преобразует строку в целое число или возвращает 0, если строка null/пуста/нечисловая.
	*
	* @param string Строка для преобразования.
	* @return Полученное целое число
	*/
   public static int stringToIntOrZero(String string) {
	  try {
		 return !isBlank(string) ? Integer.parseInt(string) : 0;
	  } catch (NumberFormatException e) {
		 return 0;
	  }
   }

   /**
	* Метод замены всей кирилицы в строке транслитом.
	*
	* @param cs входная строка
	* @return возвращает строку, в которой вся кирилица заменена транслитом
	*/
   public static String getTranslitStr(String cs) {
	  String str = cs;
	  if (str == null || str != null && str.isEmpty()) {
		 return "";
	  }
	  StringBuilder resultStr = new StringBuilder();
	  str = str.toLowerCase();
	  for (int i = 0; i < str.length(); i++) {
		 if (TRANSLIT.containsKey(str.charAt(i) + "")) {
			resultStr.append(TRANSLIT.get(str.charAt(i) + ""));
		 } else {
			resultStr.append(str.charAt(i));
		 }
	  }
	  return resultStr.toString();
   }

   /**
	* Метод получения строкового представления даты dd.MM.yyyy HH:mm.
	*
	* @param date дата
	* @return возвращает строковое представление даты dd.MM.yyyy HH:mm
	*/
   public static String getStringByDateTime(Date date) {
	  if (date == null) {
		 return "";
	  }
	  return dateTimeFormat.format(date);
   }

   /**
	* Метод получения строкового представления даты dd.MM.yyyy.
	*
	* @param date дата
	* @return возвращает строковое представление даты dd.MM.yyyy
	*/
   public static String getStringByDate(Date date) {
	  if (date == null) {
		 return "";
	  }
	  return dateTimeFormat.format(date);
   }

   /**
	* Метод получения даты из строки dd.MM.yyyy HH:mm.
	*
	* @param str дата
	* @return возвращает дату из строки dd.MM.yyyy HH:mm
	*/
   public static Date getDateByString(String str) {
	  if (str == null || str.isEmpty()) {
		 return null;
	  }
	  try {
		 return dateTimeFormat.parse(str);
	  } catch (ParseException ex) {
		 return null;
	  }
   }

   /**
	* Формирвет строку с разделителем - запятая исз списка.
	*
	* @param list Список.
	* @return
	*/
   public static String mkString(List<?> list) {
	  if (list == null) {
		 return null;
	  }
	  StringBuilder str = new StringBuilder();
	  for (Object obj : list) {
		 str.append(obj.toString()).append(", ");
	  }
	  str.delete(str.length() - 2, str.length());
	  return str.toString();
   }

   /**
	* Метод, заменяющий запрещенные символы для имен файлов на
	* symbolForFileName.
	*
	* @param fileName входное имя файла
	* @return возвращает корректное имя файла
	*/
   public static String getCorrectFileName(String fileName) {
	  return fileName.replace('/', symbolForFileName).replace('\\', symbolForFileName).replace('|', symbolForFileName)
		  .replace('*', symbolForFileName).replace('+', symbolForFileName).replace(':', symbolForFileName)
		  .replace('?', symbolForFileName).replace('"', symbolForFileName).replace('<', symbolForFileName)
		  .replace('>', symbolForFileName);
   }

   /**
	* Убирает все пробелы из строки.
	*
	* @param str строка
	* @return
	*/
   public static String removeSpaces(String str) {
	  if (str == null) {
		 return str;
	  }
	  return str.trim().replace(" ", "").replace("\t", "");
   }

   /**
	* Преобразует в строку денежной суммы.
	*
	* @param price Число.
	* @return
	*/
   public static String priceToString(Double price) {
	  DecimalFormatSymbols symbols = new DecimalFormatSymbols();
	  symbols.setGroupingSeparator(' ');
	  symbols.setDecimalSeparator(',');
	  DecimalFormat format = new DecimalFormat("#,##0.00");
	  return format.format(price);
   }

   /**
	* Преобразует в строку денежной суммы.
	*
	* @param price Число.
	* @return
	*/
   public static String priceToString(BigDecimal price) {
	  return priceToString(price.doubleValue());
   }

   /**
	* Отрезает строку до определенного кол-ва символов @param messageLength.
	*
	* @param message       Строка.
	* @param messageLength Кол-во символов.
	* @return
	*/
   public static String trimMessage(String message, int messageLength) {
	  if (!isBlank(message) && message.length() > messageLength) {
		 return message.substring(0, messageLength);
	  }
	  return message;
   }

   /**
	* Приводит строку для запроса в БД по like.
	*
	* @param cs Строка.
	* @return
	*/
   public static String escapeLikeCharacters(String cs) {
	  String str = cs;
	  if (str != null) {
		 str = str.replaceAll("\\\\", "\\\\%"); // исправляем ошибку поиска
		 // по слешу (ORA-01424)
		 str = str.replaceAll("%", "\\\\%"); // экранируем специальные
		 // символы для LIKE
		 str = str.replaceAll("_", "\\\\_");
	  }
	  return str;
   }

   /**
	* Преобразует булево значение во флажок либо крестик.
	*
	* @param binary булево значение
	* @return флажок либо крестик
	*/
   public static String binaryToMarkSymbol(Boolean binary) {
	  return binary ? CHECK_MARK : CROSS_MARK;
   }

   /**
	* Преобразует строку, содержащую бинарное значение (0 или 1) во флажок либо крестик.
	*
	* @param binary строка, содержащая бинарное значение
	* @return флажок либо крестик
	*/
   public static String binaryToMarkSymbol(String binary) {
	  return binaryToMarkSymbol(!binary.equals("0"));
   }

	public static String binaryToCheckMark(String binary) {
		return !binary.equals("0") ? CHECK_MARK : "";
	}

}
