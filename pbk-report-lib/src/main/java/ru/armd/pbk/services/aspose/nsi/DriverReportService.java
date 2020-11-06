package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.DriverReportMapper;

/**
 * Сервис отчетов водителей.
 */
@Service
public class DriverReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public DriverReportService(DriverReportMapper mapper) {
	  super(mapper, "Водителей");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
