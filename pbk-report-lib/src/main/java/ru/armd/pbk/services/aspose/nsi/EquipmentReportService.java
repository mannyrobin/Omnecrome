package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.EquipmentReportMapper;

/**
 * Сервис отчетов бортового обордования.
 */
@Service
public class EquipmentReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public EquipmentReportService(EquipmentReportMapper mapper) {
	  super(mapper, "Бортового оборудования");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
