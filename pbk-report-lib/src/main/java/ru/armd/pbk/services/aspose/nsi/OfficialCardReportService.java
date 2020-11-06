package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.OfficialCardReportMapper;

/**
 * Сервис отчетов СКК.
 */
@Service
public class OfficialCardReportService
	extends NsiReportService {


   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public OfficialCardReportService(OfficialCardReportMapper mapper) {
	  super(mapper, "СКК");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
