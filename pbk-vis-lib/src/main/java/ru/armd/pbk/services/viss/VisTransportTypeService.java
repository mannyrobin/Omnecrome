package ru.armd.pbk.services.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.VisTransportType;
import ru.armd.pbk.repositories.viss.VisTransportTypeRepository;

/**
 * Сервис видов транспорта ВИС.
 */
@Service
public class VisTransportTypeService
	extends BaseDomainService<VisTransportType, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(VisTransportTypeService.class);

   @Autowired
   VisTransportTypeService(VisTransportTypeRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
