package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.TsModelReportMapper;

/**
 * Сервис отчетов моделей ТС.
 */
@Service
public class TsModelReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер
	*/
   @Autowired
   public TsModelReportService(TsModelReportMapper mapper) {
	  super(mapper, "Моделей TC");
   }


   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
