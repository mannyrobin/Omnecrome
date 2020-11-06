package ru.armd.pbk.services.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.core.UserRole;
import ru.armd.pbk.dto.core.UserRoleDTO;
import ru.armd.pbk.matcher.core.IUserRoleMatcher;
import ru.armd.pbk.repositories.core.UserRoleRepository;

/**
 * Сервис для связок пользователя и роли.
 */
@Service
public class UserRoleService
	extends BaseDomainService<UserRole, UserRoleDTO> {

   private static final Logger LOGGER = Logger.getLogger(UserRoleService.class);

   /**
	* Конструктор.
	*
	* @param domainRepository Реализация интерфейса репозитория домена.
	*/
   @Autowired
   public UserRoleService(UserRoleRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public UserRole toDomain(UserRoleDTO dto) {
	  return IUserRoleMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public UserRoleDTO toDTO(UserRole domain) {
	  return IUserRoleMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
