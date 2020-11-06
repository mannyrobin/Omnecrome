package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.CountyReportMapper;

/**
 * Сервис отчетов округов.
 */
@Service
public class CountyReportService
	extends NsiReportService {
   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер
	*/
   @Autowired
   public CountyReportService(CountyReportMapper mapper) {
	  super(mapper, "Округов");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
