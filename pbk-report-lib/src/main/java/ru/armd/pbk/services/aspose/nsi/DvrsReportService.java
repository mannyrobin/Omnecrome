package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.DvrsReportMapper;

/**
 * Сервис отчетов видеорегистраторов.
 */
@Service
public class DvrsReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public DvrsReportService(DvrsReportMapper mapper) {
	  super(mapper, "Видеорегистраторов");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
