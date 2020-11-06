package ru.armd.pbk.utils.http;

import java.util.Arrays;

/**
 * Содержит методы для обработки заголовков http-запросов.
 */
public class HttpHeaderUtils {

   /**
	* Проверяет на совпадение значения с заголовком Accept.
	*
	* @param acceptHeader заголовок Accept.
	* @param toAccept     значение
	* @return true если заголовок содержит значение.
	*/
   public static boolean accepts(String acceptHeader, String toAccept) {
	  String[] acceptValues = acceptHeader.split("\\s*(,|;)\\s*");
	  Arrays.sort(acceptValues);
	  return Arrays.binarySearch(acceptValues, toAccept) > -1
		  || Arrays.binarySearch(acceptValues, toAccept.replaceAll("/.*$", "/*")) > -1
		  || Arrays.binarySearch(acceptValues, "*/*") > -1;
   }

   /**
	* Проверяет на совпадение значения с заголовком.
	*
	* @param matchHeader заголовок.
	* @param toMatch     значение
	* @return true если заголовок содержит значение.
	*/
   public static boolean matches(String matchHeader, String toMatch) {
	  String[] matchValues = matchHeader.split("\\s*,\\s*");
	  Arrays.sort(matchValues);
	  return Arrays.binarySearch(matchValues, toMatch) > -1 || Arrays.binarySearch(matchValues, "*") > -1;
   }

   /**
	* Получить название файла из заголовка Content-Disposition.
	*
	* @param header заголовок.
	* @return название файла.
	*/
   public static String filename(String header) {
	  if (header != null && header.indexOf("=") != -1) {
		 return header.split("=")[1];
	  }
	  return null;
   }

}
