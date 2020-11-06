package ru.armd.pbk.matcher.core;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.core.RolePermission;
import ru.armd.pbk.dto.core.RolePermissionDTO;

/**
 *
 */
@Mapper
public interface IRolePermissionMatcher
	extends IDomainMatcher<RolePermission, RolePermissionDTO> {

   IRolePermissionMatcher INSTANCE = Mappers.getMapper(IRolePermissionMatcher.class);

}
