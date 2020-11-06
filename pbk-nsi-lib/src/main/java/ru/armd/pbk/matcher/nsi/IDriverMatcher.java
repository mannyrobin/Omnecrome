package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Driver;
import ru.armd.pbk.dto.nsi.DriverDTO;

/**
 * Мапер для сопостовления различных типов сущности "водитель".
 */
@Mapper
public interface IDriverMatcher
	extends IDomainMatcher<Driver, DriverDTO> {

   IDriverMatcher INSTANCE = Mappers.getMapper(IDriverMatcher.class);
}