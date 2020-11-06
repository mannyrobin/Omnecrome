package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.VehicleReportMapper;

/**
 * Сервис отчетов ТС.
 */
@Service
public class VehicleReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public VehicleReportService(VehicleReportMapper mapper) {
	  super(mapper, "TC");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
