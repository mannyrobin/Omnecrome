package ru.armd.pbk.aspose.nsi.bsos;

import ru.armd.pbk.aspose.core.IReportNameData;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс, предназначенный для построения общей (для отдельных и множественных отчётов) части имени файла итогового отчёта.
 */
public class AbstractBsoReportNameData
	implements IReportNameData {

   private final Date reportDateTime;

   private final String DATETIME_FORMAT = "ddMMyyyy_HHmm";

   protected AbstractBsoReportNameData(Date reportDateTime) {
	  this.reportDateTime = reportDateTime;
   }

   private String formatReportDateTime() {
	  return new SimpleDateFormat(DATETIME_FORMAT).format(reportDateTime);
   }

   /**
	* Построить общую (для отдельных и множественных отчётов) часть имени отчёта.
	*
	* @return Общая часть имени отчёта
	*/
   @Override
   public String buildName() {
	  StringBuilder sb = new StringBuilder();
	  sb.append("taskReport_");
	  sb.append(formatReportDateTime());
	  sb.append("_");
	  return sb.toString();
   }
}
