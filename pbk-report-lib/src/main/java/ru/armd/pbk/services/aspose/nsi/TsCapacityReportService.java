package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.TsCapacityReportMapper;

/**
 * Сервис отчетов вместимостей ТС.
 */
@Service
public class TsCapacityReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер
	*/
   @Autowired
   public TsCapacityReportService(TsCapacityReportMapper mapper) {
	  super(mapper, "Вместимостей TC");
   }


   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
