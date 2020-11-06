package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.EmployeeReportMapper;

/**
 * Сервис отчетов сотрудников.
 */
@Service
public class EmployeeReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public EmployeeReportService(EmployeeReportMapper mapper) {
	  super(mapper, "Сотрудников");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
