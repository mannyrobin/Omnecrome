package ru.armd.pbk.services.nsi.askp;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.repositories.nsi.askp.AskpChecksByHoursRepository;

/**
 * Сервис данных от АСКП.
 */
@Service
public class AskpChecksByHoursService
	extends BaseDomainService<BaseDomain, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(AskpChecksByHoursService.class);

   @Autowired
   AskpChecksByHoursService(AskpChecksByHoursRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
