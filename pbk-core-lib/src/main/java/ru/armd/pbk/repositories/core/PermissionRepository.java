package ru.armd.pbk.repositories.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.core.Permission;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.mappers.core.PermissionMapper;


/**
 * Репозиторий разрешений ролей.
 */
@Repository
public class PermissionRepository
	extends BaseDomainRepository<Permission> {

   public static final Logger LOGGER = Logger.getLogger(UserRepository.class);

   /**
	* Создаёт репозиторий для прав.
	*
	* @param mapper маппер для прав.
	*/
   @Autowired
   PermissionRepository(PermissionMapper mapper) {
	  super(CoreAuditType.CORE_ROLE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
