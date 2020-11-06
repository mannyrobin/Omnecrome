package ru.armd.pbk.services.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.core.RolePermission;
import ru.armd.pbk.dto.core.RolePermissionDTO;
import ru.armd.pbk.matcher.core.IRolePermissionMatcher;
import ru.armd.pbk.repositories.core.RolePermissionsRepository;

import java.util.List;

/**
 *
 */
@Service
public class RolePermissionService
	extends BaseDomainService<RolePermission, RolePermissionDTO> {

   private static final Logger LOGGER = Logger.getLogger(RolePermissionService.class);

   private RolePermissionsRepository domainRepository;

   /**
	* Конструктор.
	*
	* @param domainRepository Реализация интерфейса репозитория домена.
	*/
   @Autowired
   public RolePermissionService(RolePermissionsRepository domainRepository) {
	  super(domainRepository);
	  this.domainRepository = domainRepository;
   }

   @Override
   public RolePermission toDomain(RolePermissionDTO dto) {
	  return IRolePermissionMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public RolePermissionDTO toDTO(RolePermission domain) {
	  return IRolePermissionMatcher.INSTANCE.toDTO(domain);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Добавить привелегии роли.
	*
	* @param id  - ИД роли.
	* @param ids - ИДС привелегий.
	*/
   @Transactional
   public void addPermissions(Long id, List<Long> ids) {
	  domainRepository.addPermissions(id, ids);
   }

   /**
	* Удалить привелегии роли.
	*
	* @param id  - ИД роли.
	* @param ids - ИДС привелегий.
	*/
   @Transactional
   public void removePermissions(Long id, List<Long> ids) {
	  domainRepository.removePermissions(id, ids);
   }

}
