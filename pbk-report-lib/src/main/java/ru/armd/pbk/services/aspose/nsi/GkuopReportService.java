package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.GkuopReportMapper;

/**
 * Сервис отчетов сотрудников ГКУ ОП.
 */
@Service
public class GkuopReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public GkuopReportService(GkuopReportMapper mapper) {
	  super(mapper, "Сотрудников ГКУ ОП");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
