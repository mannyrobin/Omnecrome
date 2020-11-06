package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.PuskReportMapper;

/**
 * Сервис отчетов ПУсК.
 */
@Service
public class PuskReportService
	extends NsiReportService {


   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер
	*/
   @Autowired
   public PuskReportService(PuskReportMapper mapper) {
	  super(mapper, "ПУсК");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
