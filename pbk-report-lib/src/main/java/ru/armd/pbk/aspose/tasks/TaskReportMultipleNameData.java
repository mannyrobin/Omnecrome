package ru.armd.pbk.aspose.tasks;

import java.util.Date;
import java.util.List;

/**
 * Класс, предназначенный для построения имени файла итогового множественного отчёта по заданиям.
 */
public class TaskReportMultipleNameData
	extends AbstractTaskReportNameData {

   private final List<String> bsoNumbers;

   /**
	* Конструктор по времени создания отчёта и номерам БСО для использования в имени.
	*
	* @param reportDateTime время создания отчёта
	* @param bsoNumbers     номера БСО
	*/
   public TaskReportMultipleNameData(Date reportDateTime, List<String> bsoNumbers) {
	  super(reportDateTime);
	  this.bsoNumbers = bsoNumbers;
   }

   private String formatBsoNumbers() {
	  StringBuilder sb = new StringBuilder();
	  for (String bsoNumber : bsoNumbers) {
		 sb.append(bsoNumber);
		 sb.append("_");
	  }
	  sb.delete(sb.length() - 2, sb.length());
	  return sb.toString();
   }

   /**
	* Построить имя файла итогового множественного отчёта.
	*
	* @return Имя файла итогового множественного отчёта
	*/
   @Override
   public String buildName() {
	  StringBuilder sb = new StringBuilder();
	  sb.append(super.buildName());
	  sb.append(formatBsoNumbers());
	  return sb.toString();
   }
}
