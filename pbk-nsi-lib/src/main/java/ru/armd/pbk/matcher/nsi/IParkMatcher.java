package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Park;
import ru.armd.pbk.dto.nsi.ParkDTO;


/**
 * Мапер для сопостовления различных типов сущности "Парк".
 */
@Mapper
public interface IParkMatcher
	extends IDomainMatcher<Park, ParkDTO> {

   IParkMatcher INSTANCE = Mappers.getMapper(IParkMatcher.class);
}
