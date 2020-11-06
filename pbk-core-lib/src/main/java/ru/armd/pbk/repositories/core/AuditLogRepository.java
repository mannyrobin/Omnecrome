package ru.armd.pbk.repositories.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.core.AuditLog;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.mappers.core.AuditLogMapper;

/**
 * Репозиторий результатов очистки логов аудита.
 */
@Repository
public class AuditLogRepository
	extends BaseDomainRepository<AuditLog> {

   public static final Logger LOGGER = Logger.getLogger(AuditLogRepository.class);

   @Autowired
   AuditLogRepository(AuditLogMapper mapper) {
	  super(CoreAuditType.CORE_AUDIT, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
