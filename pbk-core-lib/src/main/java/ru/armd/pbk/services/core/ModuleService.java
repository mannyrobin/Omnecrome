package ru.armd.pbk.services.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.core.Module;
import ru.armd.pbk.dto.core.ModuleDTO;
import ru.armd.pbk.repositories.core.ModuleRepository;

/**
 * Сервис для разрешений пользователя.
 */
@Service
public class ModuleService
	extends BaseDomainService<Module, ModuleDTO> {

   private static final Logger LOGGER = Logger.getLogger(ModuleService.class);

   @Autowired
   ModuleService(ModuleRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
