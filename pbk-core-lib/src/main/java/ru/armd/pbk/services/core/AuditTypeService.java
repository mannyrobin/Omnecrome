package ru.armd.pbk.services.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.core.AuditType;
import ru.armd.pbk.dto.core.AuditTypeDTO;
import ru.armd.pbk.matcher.core.IAuditTypeMatcher;
import ru.armd.pbk.repositories.core.AuditTypeRepository;

/**
 * Сервис для работы с событиями аудита.
 */
@Service
public class AuditTypeService
	extends BaseDomainService<AuditType, AuditTypeDTO> {

   private static final Logger LOGGER = Logger.getLogger(AuditTypeService.class);

   @Autowired
   AuditTypeService(AuditTypeRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public AuditType toDomain(AuditTypeDTO dto) {
	  return IAuditTypeMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public AuditTypeDTO toDTO(AuditType domain) {
	  return IAuditTypeMatcher.INSTANCE.toDTO(domain);
   }

}
