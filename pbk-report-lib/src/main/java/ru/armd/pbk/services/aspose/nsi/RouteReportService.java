/**
 *
 */
package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.RouteReportMapper;

/**
 * Сервис отчетов маршрутов.
 */
@Service
public class RouteReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public RouteReportService(RouteReportMapper mapper) {
	  super(mapper, "Маршрутов");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
