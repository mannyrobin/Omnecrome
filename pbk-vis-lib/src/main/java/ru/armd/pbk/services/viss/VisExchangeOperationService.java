package ru.armd.pbk.services.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.VisExchangeOperation;
import ru.armd.pbk.repositories.viss.VisExchangeOperationRepository;

/**
 * Сервис типов операций с ВИС.
 */
@Service
public class VisExchangeOperationService
	extends BaseDomainService<VisExchangeOperation, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(VisExchangeOperationService.class);

   @Autowired
   VisExchangeOperationService(VisExchangeOperationRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
