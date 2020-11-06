package ru.armd.pbk.aspose.nsi;

import ru.armd.pbk.aspose.core.IReportNameData;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Названия выгружаемых отчетов НСИ.
 */
public class NsiExportReportNameData
	implements IReportNameData {

   private final Date reportDateTime;

   private final String DATETIME_FORMAT = "ddMMyyyy_HHmm";
   private final String caption;

   protected NsiExportReportNameData(Date reportDateTime, String caption) {
	  this.reportDateTime = reportDateTime;
	  this.caption = caption;
   }

   private String formatReportDateTime() {
	  return new SimpleDateFormat(DATETIME_FORMAT).format(reportDateTime);
   }

   @Override
   public String buildName() {
	  StringBuilder sb = new StringBuilder();
	  sb.append("Справочник_");
	  sb.append(caption);
	  sb.append("_");
	  sb.append(formatReportDateTime());
	  return sb.toString();
   }

}
