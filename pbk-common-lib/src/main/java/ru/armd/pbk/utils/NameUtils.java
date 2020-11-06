package ru.armd.pbk.utils;

import static ru.armd.pbk.utils.StringUtils.isBlank;

/**
 * Класс-утилита для обработки имён.
 */
public class NameUtils {

   /**
	* Преобразует набор из фамилии, имени, отчества в строку из фамилии и инициалов
	* (отчество может отсутствовать).
	*
	* @param surname    Фамилия
	* @param name       Имя
	* @param patronumic Отчество
	* @return Строка из фамилии и инициалов
	*/
   public static String toSurnameAndInitials(String surname, String name, String patronumic) {
	  if (isBlank(surname) || isBlank(name)) {
		 throw new IllegalArgumentException("Отсутствует фамилия или имя");
	  }
	  StringBuilder sb = new StringBuilder();
	  sb.append(String.valueOf(surname.charAt(0)).toUpperCase());
	  sb.append(surname.substring(1));
	  sb.append(" ");
	  sb.append(String.valueOf(name.charAt(0)).toUpperCase());
	  sb.append(".");
	  if (!isBlank(patronumic)) {
		 sb.append(String.valueOf(patronumic.charAt(0)).toUpperCase());
		 sb.append(".");
	  }
	  return sb.toString();
   }

   /**
	* Преобразует данные по смене в краткое имя смены.
	*
	* @param shiftId   Тип смены
	* @param isReserve Флаг "резерв"
	* @return краткое имя смены
	*/
   public static String shiftDataToName(Long shiftId, Boolean isReserve) {
	  String name = "";
	  if (shiftId == 1) { // тип long недопустим в switch
		 name += "В";
	  } else if (shiftId == 2) {
		 name += "Д";
	  } else if (shiftId == 3) {
		 name += "Н";
	  } else if (shiftId == 4) {
		 name += "О";
	  } else if (shiftId == 5) {
		 name += "Б";
	  } else if (shiftId == 6) {
		 name += "1";
	  } else if (shiftId == 7) {
		 name += "2";
	  } else if (shiftId == 14) {
		 name += "3";
	  } else if (shiftId == 8) {
		 name += "И";
	  } else {
		 return "";
	  }
	  return isReserve ? name + " (р)" : name;
   }

   /**
	* Преобразует данные по отработанным часам смены в краткое имя (если смена не отработана) либо просто часы.
	*
	* @param absenceShiftId Тип неотработанной смены (при не-null shiftHours ожидается null)
	* @param shiftHours     Количество отработанных часов (при не-null absenceShiftId ожидается null)
	* @return краткое имя (если смена не отработана) либо просто часы
	*/
   public static String shiftHoursDataToName(Long absenceShiftId, Number shiftHours) {
	  String name = "";
	  if (absenceShiftId != null) {
		 if (absenceShiftId == 1) { // тип long недопустим в switch
			name += "В";
		 } else if (absenceShiftId == 4) {
			name += "О";
		 } else if (absenceShiftId == 5) {
			name += "Б";
		 } else {
			return ""; // другие причины отсутствия контролёра пока не предусмотрены
		 }
	  } else if (shiftHours != null) {
		 name += shiftHours;
	  } else {
		 name = ""; // не должно произойти
	  }
	  return name;
   }

}
