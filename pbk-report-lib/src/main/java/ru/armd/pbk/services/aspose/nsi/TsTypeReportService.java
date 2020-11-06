package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.TsTypeReportMapper;

/**
 * Сервис типов ТС.
 */
@Service
public class TsTypeReportService
	extends NsiReportService {

   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер
	*/
   @Autowired
   public TsTypeReportService(TsTypeReportMapper mapper) {
	  super(mapper, "Типов TC");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
