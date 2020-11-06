package ru.armd.pbk.matcher.core;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.core.Role;
import ru.armd.pbk.dto.core.RoleDTO;

/**
 * Матчер ролей.
 */
@Mapper
public interface IRoleMatcher
	extends IDomainMatcher<Role, RoleDTO> {

   static final IRoleMatcher INSTANCE = Mappers.getMapper(IRoleMatcher.class);

}
