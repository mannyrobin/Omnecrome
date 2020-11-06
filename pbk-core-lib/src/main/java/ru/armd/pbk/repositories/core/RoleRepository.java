package ru.armd.pbk.repositories.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.core.Role;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.mappers.core.RoleMapper;

/**
 * Репозиторий ролей.
 */
@Repository
public class RoleRepository
	extends BaseDomainRepository<Role> {

   public static final Logger LOGGER = Logger.getLogger(UserRepository.class);

   /**
	* Создаёт репозиторий для ролей.
	*
	* @param mapper маппер для ролей.
	*/
   @Autowired
   RoleRepository(RoleMapper mapper) {
	  super(CoreAuditType.CORE_ROLE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
