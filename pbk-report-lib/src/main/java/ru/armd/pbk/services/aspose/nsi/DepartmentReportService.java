package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.DepartmentReportMapper;

/**
 * Сервис отчетов подразделений.
 */
@Service
public class DepartmentReportService
	extends NsiReportService {


   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);


   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public DepartmentReportService(DepartmentReportMapper mapper) {
	  super(mapper, "Подразделений");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
