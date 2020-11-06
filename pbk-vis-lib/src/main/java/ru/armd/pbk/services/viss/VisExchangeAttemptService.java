package ru.armd.pbk.services.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.VisExchangeAttempt;
import ru.armd.pbk.repositories.viss.VisExchangeAttemptRepository;

/**
 * Сервис записи журнала взаимодействия с ВИС.
 */
@Service
public class VisExchangeAttemptService
	extends BaseDomainService<VisExchangeAttempt, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(VisExchangeAttemptService.class);

   @Autowired
   VisExchangeAttemptService(VisExchangeAttemptRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
