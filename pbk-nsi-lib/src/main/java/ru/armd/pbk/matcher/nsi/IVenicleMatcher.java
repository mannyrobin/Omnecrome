package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Venicle;
import ru.armd.pbk.dto.nsi.VenicleDTO;

/**
 * Мапер для сопостовления различных типов сущности "ТС".
 */
@Mapper
public interface IVenicleMatcher
	extends IDomainMatcher<Venicle, VenicleDTO> {

   IVenicleMatcher INSTANCE = Mappers.getMapper(IVenicleMatcher.class);
}