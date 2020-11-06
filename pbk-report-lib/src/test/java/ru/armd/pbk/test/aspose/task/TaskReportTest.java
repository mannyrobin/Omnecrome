package ru.armd.pbk.test.aspose.task;

import org.apache.log4j.Logger;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.armd.pbk.aspose.core.AsposeReports;
import ru.armd.pbk.aspose.core.IReportType;
import ru.armd.pbk.aspose.core.ReportFormat;
import ru.armd.pbk.aspose.tasks.TaskCellsReportType;
import ru.armd.pbk.exceptions.PBKAsposeReportException;
import ru.armd.pbk.services.aspose.tasks.TaskReportService;
import ru.armd.pbk.test.aspose.BaseAsposeReportTest;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

public class TaskReportTest
	extends BaseAsposeReportTest {

   private static final Logger LOGGER = Logger.getLogger(TaskReportTest.class);

   @Autowired
   private TaskReportService taskReportService;

   @Test
   public void generateTaskTest() {
	  Map<String, File> files = new HashMap<>();
	  IReportType rtControllerTask = new TaskCellsReportType();
	  String format = "xlsx";
	  ReportFormat rformat = null;
	  Map<ReportFormat, File> result = null;
	  Long id = 1L;

	  rformat = AsposeReports.checkReportFormatOwnOrPdf(rtControllerTask, format);
	  rtControllerTask.setReportFormat(rformat);
	  result = taskReportService.generate(rtControllerTask, id);
	  if (result == null) {
		 throw new PBKAsposeReportException("sdasdas");
	  }
	  File file = result.get(rformat);
	  if (file == null) {
		 throw new PBKAsposeReportException("sckfjasfasf");
	  }
	  files.put(rtControllerTask.getFileName(id), file);
   }

}
