package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.ParkReportMapper;

/**
 * Сервис отчетов парков.
 */
@Service
public class ParkReportService
	extends NsiReportService {


   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public ParkReportService(ParkReportMapper mapper) {
	  super(mapper, "Эксплуатационных парков");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}

