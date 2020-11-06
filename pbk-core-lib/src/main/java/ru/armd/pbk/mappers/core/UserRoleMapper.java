package ru.armd.pbk.mappers.core;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.core.UserRole;

/**
 * Маппер связок пользователя и роли.
 */
@IsMapper
public interface UserRoleMapper
	extends IDomainMapper<UserRole> {
}
