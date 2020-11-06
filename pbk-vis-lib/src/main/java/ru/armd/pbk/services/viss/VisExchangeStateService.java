package ru.armd.pbk.services.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.VisExchangeState;
import ru.armd.pbk.repositories.viss.VisExchangeStateRepository;

/**
 * Сервис статусов взаимодействия с ВИС.
 */
@Service
public class VisExchangeStateService
	extends BaseDomainService<VisExchangeState, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(VisExchangeStateService.class);

   @Autowired
   VisExchangeStateService(VisExchangeStateRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
