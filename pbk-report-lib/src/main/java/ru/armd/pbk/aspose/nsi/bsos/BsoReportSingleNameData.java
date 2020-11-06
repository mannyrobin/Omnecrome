package ru.armd.pbk.aspose.nsi.bsos;

import java.util.Date;

/**
 * Класс, предназначенный для построения имени файла итогового отдельного отчёта по акту об изъятии.
 */
public class BsoReportSingleNameData
	extends AbstractBsoReportNameData {

   private final String bsoNumber;

   /**
	* Конструктор по времени создания отчёта и номеру БСО для использования в имени.
	*
	* @param reportDateTime время создания отчёта
	* @param bsoNumber      номер БСО
	*/
   public BsoReportSingleNameData(Date reportDateTime, String bsoNumber) {
	  super(reportDateTime);
	  this.bsoNumber = bsoNumber;
   }

   /**
	* Построить имя файла итогового отдельного отчёта.
	*
	* @return Имя файла итогового отдельного отчёта
	*/
   @Override
   public String buildName() {
	  StringBuilder sb = new StringBuilder();
	  sb.append(super.buildName());
	  sb.append(bsoNumber);
	  return sb.toString();
   }
}
