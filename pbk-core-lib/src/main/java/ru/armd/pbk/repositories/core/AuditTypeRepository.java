package ru.armd.pbk.repositories.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.core.AuditType;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.mappers.core.AuditTypeMapper;

/**
 * Репозиторий События Аудита.
 */
@Repository
public class AuditTypeRepository
	extends BaseDomainRepository<AuditType> {

   public static final Logger LOGGER = Logger.getLogger(UserRepository.class);

   @Autowired
   AuditTypeRepository(AuditTypeMapper mapper) {
	  super(CoreAuditType.CORE_AUDIT_TYPE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
