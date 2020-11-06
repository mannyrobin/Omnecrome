package ru.armd.pbk.services.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.dto.BaseDTO;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.core.Permission;
import ru.armd.pbk.repositories.core.PermissionRepository;

/**
 * Сервис для разрешений пользователя.
 */
@Service
public class PermissionService
	extends BaseDomainService<Permission, BaseDTO> {

   private static final Logger LOGGER = Logger.getLogger(PermissionService.class);

   @Autowired
   PermissionService(PermissionRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
