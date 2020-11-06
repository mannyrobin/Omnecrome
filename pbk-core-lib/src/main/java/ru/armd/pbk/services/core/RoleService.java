package ru.armd.pbk.services.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.core.Role;
import ru.armd.pbk.domain.core.RolePermission;
import ru.armd.pbk.dto.core.RoleDTO;
import ru.armd.pbk.matcher.core.IRoleMatcher;
import ru.armd.pbk.repositories.core.RolePermissionsRepository;
import ru.armd.pbk.repositories.core.RoleRepository;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервис для ролей пользователя.
 */
@Service
public class RoleService
	extends BaseDomainService<Role, RoleDTO> {

   private static final Logger LOGGER = Logger.getLogger(RoleService.class);

   private static final Long ALL_DEP_PERM_ID = 10L;

   private RolePermissionsRepository rolePermissionRepository;

   @Autowired
   RoleService(RoleRepository roleRepository, RolePermissionsRepository rolePermissionRepository) {
	  super(roleRepository);
	  this.rolePermissionRepository = rolePermissionRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Role toDomain(RoleDTO dto) {
	  return IRoleMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public RoleDTO toDTO(Role domain) {
	  RoleDTO dto = IRoleMatcher.INSTANCE.toDTO(domain);
	  if (dto.getId() != null) {
		 Map<String, Object> filter = new HashMap<>();
		 filter.put("roleId", dto.getId());
		 filter.put("permissionId", ALL_DEP_PERM_ID);
		 dto.setIsAllDepartments(rolePermissionRepository.getDomain(filter) != null);
	  }
	  return dto;
   }

   @Transactional
   @Override
   public RoleDTO saveDTO(RoleDTO dto) {
	  RoleDTO resultDto = super.saveDTO(dto);
	  Map<String, Object> filter = new HashMap<>();
	  filter.put("roleId", dto.getId());
	  filter.put("permissionId", ALL_DEP_PERM_ID);
	  RolePermission rolePermission = rolePermissionRepository.getDomain(filter);
	  if (dto.getIsAllDepartments() && rolePermission == null) {
		 rolePermissionRepository.addPermissions(resultDto.getId(), Arrays.asList(ALL_DEP_PERM_ID));
	  } else if (!dto.getIsAllDepartments() && rolePermission != null) {
		 rolePermissionRepository.delete(rolePermission);
	  }
	  return resultDto;
   }
}
