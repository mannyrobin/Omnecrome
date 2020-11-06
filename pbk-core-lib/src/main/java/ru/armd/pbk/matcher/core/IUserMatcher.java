package ru.armd.pbk.matcher.core;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.core.User;
import ru.armd.pbk.dto.core.UserDTO;

/**
 * Преобразует dto и domain объекты пользователей.
 */
@Mapper
public interface IUserMatcher
	extends IDomainMatcher<User, UserDTO> {

   IUserMatcher INSTANCE = Mappers.getMapper(IUserMatcher.class);

}
