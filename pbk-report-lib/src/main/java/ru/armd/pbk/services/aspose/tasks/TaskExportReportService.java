package ru.armd.pbk.services.aspose.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.tasks.TaskReportingMapper;
import ru.armd.pbk.services.aspose.nsi.NsiReportService;

@Service
public class TaskExportReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(TaskExportReportService.class);

   @Autowired
   TaskExportReportService(TaskReportingMapper mapper) {
	  super(mapper, "Заданий");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
