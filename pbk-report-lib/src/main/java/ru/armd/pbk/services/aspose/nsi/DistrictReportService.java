package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.DistrictReportMapper;

/**
 * Сервис отчетов районов.
 */
@Service
public class DistrictReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер
	*/
   @Autowired
   public DistrictReportService(DistrictReportMapper mapper) {
	  super(mapper, "Районов");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
