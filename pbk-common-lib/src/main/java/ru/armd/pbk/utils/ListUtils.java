package ru.armd.pbk.utils;

import java.util.List;

/**
 * Класс-утилита для работы со списками.
 */
public class ListUtils {

   /**
	* Метод возвращает элементы, которые содержатся в списке sourceList и не
	* содержатся в destinationList.
	*
	* @param sourceList      Исходный список.
	* @param destinationList С чем сравниваем.
	* @param resultList      Результирующий список.
	* @return Результирующий список.
	*/
   public static List<Long> getUniqueList(List<Long> sourceList, List<Long> destinationList, List<Long> resultList) {
	  for (Long sourceItem : sourceList) {
		 boolean isExist = false;
		 for (Long destinationItem : destinationList) {
			if (sourceItem.equals(destinationItem)) {
			   isExist = true;
			   break;
			}
		 }
		 if (!isExist) {
			resultList.add(sourceItem);
		 }
	  }
	  return resultList;
   }

}
