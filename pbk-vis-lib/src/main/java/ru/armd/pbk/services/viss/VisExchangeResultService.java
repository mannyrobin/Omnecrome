package ru.armd.pbk.services.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.VisExchangeResult;
import ru.armd.pbk.repositories.viss.VisExchangeResultRepository;

/**
 * Сервис записи журнала взаимодействия с ВИС.
 */
@Service
public class VisExchangeResultService
	extends BaseDomainService<VisExchangeResult, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(VisExchangeResultService.class);

   @Autowired
   VisExchangeResultService(VisExchangeResultRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
