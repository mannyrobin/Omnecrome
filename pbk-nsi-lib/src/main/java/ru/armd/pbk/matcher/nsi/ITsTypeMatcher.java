package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.TsType;
import ru.armd.pbk.dto.nsi.TsTypeDTO;

/**
 * Мапер для сопостовления различных типов сущности "тип ТС".
 */
@Mapper
public interface ITsTypeMatcher
	extends IDomainMatcher<TsType, TsTypeDTO> {

   ITsTypeMatcher INSTANCE = Mappers.getMapper(ITsTypeMatcher.class);
}