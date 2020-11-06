package ru.armd.pbk.aspose.nsi.askp;

import ru.armd.pbk.aspose.core.IReportNameData;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Построение имени отчета для АСКП.
 */
public class AskpExportReportNameData
	implements IReportNameData {

   private final Date reportDateTime;

   private final String DATETIME_FORMAT = "ddMMyyyy_HHmm";
   private final String caption;

   protected AskpExportReportNameData(Date reportDateTime, String caption) {
	  this.reportDateTime = reportDateTime;
	  this.caption = caption;
   }

   private String formatReportDateTime() {
	  return new SimpleDateFormat(DATETIME_FORMAT).format(reportDateTime);
   }

   @Override
   public String buildName() {
	  StringBuilder sb = new StringBuilder();
	  sb.append(caption);
	  sb.append("_");
	  sb.append(formatReportDateTime());
	  return sb.toString();
   }

}
