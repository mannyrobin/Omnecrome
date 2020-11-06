package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Pusk;
import ru.armd.pbk.dto.nsi.PuskDTO;

/**
 * Мапер для сопостовления различных типов сущности "ПУсК".
 */
@Mapper
public interface IPuskMatcher
	extends IDomainMatcher<Pusk, PuskDTO> {

   IPuskMatcher INSTANCE = Mappers.getMapper(IPuskMatcher.class);
}