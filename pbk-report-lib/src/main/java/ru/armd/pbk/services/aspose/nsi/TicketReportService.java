package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.TicketReportMapper;

/**
 * Сервис отчетов билетов.
 */
@Service
public class TicketReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер
	*/
   @Autowired
   public TicketReportService(TicketReportMapper mapper) {
	  super(mapper, "Билетов");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
