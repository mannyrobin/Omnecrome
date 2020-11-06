package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Gkuop;
import ru.armd.pbk.dto.nsi.GkuopDTO;

/**
 * Мапер для сопостовления различных типов сущностей сотрудников.
 */
@Mapper
public interface IGkuopMatcher
	extends IDomainMatcher<Gkuop, GkuopDTO> {

   IGkuopMatcher INSTANCE = Mappers.getMapper(IGkuopMatcher.class);
}
