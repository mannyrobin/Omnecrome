package ru.armd.pbk.services.aspose.tasks;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.tasks.TaskWithdrawCardReportMapper;
import ru.armd.pbk.services.aspose.nsi.NsiReportService;

/**
 * Сервис изъятых проездных документов.
 */
@Service
public class TaskWithdrawCardReportService
	extends NsiReportService {

   @Autowired
   TaskWithdrawCardReportService(TaskWithdrawCardReportMapper mapper) {
	  super(mapper, "Изъятых проездных документов");
   }
}
