package ru.armd.pbk.mappers.core;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.core.Role;

import java.util.Set;

/**
 * Маппер для ролей и прав.
 */
@IsMapper
public interface RoleMapper
	extends IDomainMapper<Role> {

   Set<String> getRoleNamesByUserLogin(String login);

}
