package ru.armd.pbk.aspose.report.standard;

import ru.armd.pbk.aspose.core.IReportNameData;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Класс для построения имени файла печатной формы стандартного отчёта.
 */
public class AbstractSoReportNameData
	implements IReportNameData {

   private final Integer soNumber;
   private final Date reportDateTime;

   private final String DATETIME_FORMAT = "ddMMyyyy_HHmm";

   /**
	* Конструктор по типу отчёта и времени генерации отчёта.
	*
	* @param soNumber       тип отчёта
	* @param reportDateTime время генерации отчёта
	*/
   protected AbstractSoReportNameData(Integer soNumber, Date reportDateTime) {
	  this.soNumber = soNumber;
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
	  SoReportName name = SoReportName.getEnumById(soNumber);
	  if (name != null) {
		 return name.getName();
	  }

	  StringBuilder sb = new StringBuilder();
	  sb.append("so");
	  sb.append(soNumber);
	  sb.append("_Report_");
	  sb.append(formatReportDateTime());
	  sb.append("_");
	  return sb.toString();
   }
}
