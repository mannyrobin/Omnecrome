package ru.armd.pbk.repositories.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.core.Role;
import ru.armd.pbk.domain.core.RolePermission;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.mappers.core.RolePermissionsMapper;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Репозиторий прав роли.
 */
@Repository
public class RolePermissionsRepository
	extends BaseDomainRepository<RolePermission> {

   public static final Logger LOGGER = Logger.getLogger(RolePermissionsRepository.class);

   @Autowired
   private RoleRepository roleRepository;


   /**
	* Создаёт Репозиторий для связок ролей и пермишенов.
	*
	* @param mapper маппер для связок.
	*/
   @Autowired
   public RolePermissionsRepository(RolePermissionsMapper mapper) {
	  super(CoreAuditType.CORE_ROLE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Добавить привелегии роли.
	*
	* @param id  - ИД роли.
	* @param ids - ИДС привелегии.
	*/
   public void addPermissions(Long id, List<Long> ids) {
	  Role role = roleRepository.getById(id);
	  for (Long i : ids) {
		 RolePermission rolePermission = new RolePermission();
		 rolePermission.setPermId(i);
		 rolePermission.setRoleId(role.getId());
		 this.save(rolePermission);
	  }
   }

   /**
	* Удалить привелегии роли.
	*
	* @param id  - ИД роли.
	* @param ids - ИДС привелегии.
	*/
   public void removePermissions(Long id, List<Long> ids) {
	  for (Long i : ids) {
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("roleId", id);
		 params.put("permissionId", i);
		 RolePermission rolePermission = this.getDomain(params);
		 this.delete(rolePermission.getId());
	  }
   }

}
