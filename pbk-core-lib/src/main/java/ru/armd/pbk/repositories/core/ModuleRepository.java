package ru.armd.pbk.repositories.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.core.Module;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.mappers.core.ModuleMapper;

/**
 * Репозиторий События Аудита.
 */
@Repository
public class ModuleRepository
	extends BaseDomainRepository<Module> {

   public static final Logger LOGGER = Logger.getLogger(UserRepository.class);

   @Autowired
   ModuleRepository(ModuleMapper mapper) {
	  super(CoreAuditType.CORE_MODULE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
