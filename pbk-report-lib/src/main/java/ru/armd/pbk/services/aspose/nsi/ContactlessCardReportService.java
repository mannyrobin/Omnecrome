package ru.armd.pbk.services.aspose.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.mappers.nsi.ContactlessCardReportMapper;

/**
 * Сервис отчетов БСК.
 */
@Service
public class ContactlessCardReportService
	extends NsiReportService {


   private static final Logger LOGGER = Logger.getLogger(NsiReportService.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер
	*/
   @Autowired
   public ContactlessCardReportService(ContactlessCardReportMapper mapper) {
	  super(mapper, "БСК");
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
