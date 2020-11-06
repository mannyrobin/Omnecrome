package ru.armd.pbk.services.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.core.AuditLog;
import ru.armd.pbk.repositories.core.AuditLogRepository;

/**
 * Сервис результатов очистки логов аудита.
 */
@Service
public class AuditLogService
	extends BaseDomainService<AuditLog, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(AuditLogService.class);

   @Autowired
   AuditLogService(AuditLogRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
