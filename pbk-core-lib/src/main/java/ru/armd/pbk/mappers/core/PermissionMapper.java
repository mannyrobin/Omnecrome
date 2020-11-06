package ru.armd.pbk.mappers.core;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.core.Permission;

import java.util.Set;

/**
 * Маппер разрешений для ролей.
 */
@IsMapper
public interface PermissionMapper
	extends IDomainMapper<Permission> {


   /**
	* Необходим для аутенфикации пользователя. Загружает список Permissions для
	* пользователя.
	*
	* @param login логин пользователя.
	* @return
	*/
   Set<String> getPermissionsByUserLogin(String login);
}
