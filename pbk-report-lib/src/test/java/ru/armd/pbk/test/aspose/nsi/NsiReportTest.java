package ru.armd.pbk.test.aspose.nsi;

import org.apache.log4j.Logger;
import org.junit.Test;
import ru.armd.pbk.aspose.core.AsposeReports;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.nsi.NsiExportCellsReportType;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.services.aspose.nsi.NsiReportService;
import ru.armd.pbk.test.aspose.BaseAsposeReportTest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class NsiReportTest
	extends BaseAsposeReportTest {

   private static final Logger LOGGER = Logger.getLogger(NsiReportTest.class);

   private NsiReportService nsiReportService;

   protected void setNsiReportTest(NsiReportService nsiReportService) {
	  this.nsiReportService = nsiReportService;
   }

   @Test
   public void generateNsiTest() {
	  Map<String, File> files = new HashMap<>();
	  IReportType rtNsiCells = new NsiExportCellsReportType();
	  String format = "xlsx";
//		String format = "pdf";
	  ReportFormat rformat = null;
	  Map<ReportFormat, File> result = null;
	  Long id = 1L;

	  rformat = AsposeReports.checkReportFormatOwnOrPdf(rtNsiCells, format);
	  rtNsiCells.setReportFormat(rformat);
	  Map<String, Object> filters = new HashMap<String, Object>();
	  result = nsiReportService.generate(rtNsiCells, filters);
	  if (result == null) {
		 throw new PBKAsposeReportException("sdasdas");
	  }
	  File file = result.get(rformat);
	  if (file == null) {
		 throw new PBKAsposeReportException("sckfjasfasf");
	  }
	  files.put(rtNsiCells.getFileName(id), file);
   }

}
