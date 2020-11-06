package ru.armd.pbk.repositories.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.IHasCreater;
import ru.armd.pbk.core.IHasUpdater;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.core.UserRole;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.enums.core.EmbeddedUser;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.core.UserRoleMapper;

/**
 * Репозиторий для связки ролей и пользователей.
 */
@Repository
public class UserRoleRepository
	extends BaseDomainRepository<UserRole> {
   public static final Logger LOGGER = Logger.getLogger(UserRoleRepository.class);

   /**
	* Создаёт репозиторий для связок пользователей и ролей.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public UserRoleRepository(UserRoleMapper mapper) {
	  super(CoreAuditType.CORE_ROLE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   protected IHasCreater initCreaterInfo(IHasCreater object) {
	  try {
		 super.initCreaterInfo(object);
	  } catch (PBKException e) {
		 object.setCreateUserId(EmbeddedUser.SYSTEM.getId());
	  }
	  return object;
   }

   @Override
   protected IHasUpdater initUpdaterInfo(IHasUpdater object) {
	  try {
		 super.initUpdaterInfo(object);
	  } catch (PBKException e) {
		 object.setUpdateUserId(EmbeddedUser.SYSTEM.getId());
	  }
	  return object;
   }

}
