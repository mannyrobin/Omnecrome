package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.StopReportMapper;

/**
 * Сервис отчетов остановочных пунктов.
 */
@Service
public class StopReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер
	*/
   @Autowired
   public StopReportService(StopReportMapper mapper) {
	  super(mapper, "Остановочных пунктов");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
