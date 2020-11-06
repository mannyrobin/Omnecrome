/**
 *
 */
package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.RouteTsKindReportMapper;

/**
 * Сервис отчетов видов транспорта.
 */
@Service
public class RouteTsKindReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public RouteTsKindReportService(RouteTsKindReportMapper mapper) {
	  super(mapper, "Видов транспорта");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
