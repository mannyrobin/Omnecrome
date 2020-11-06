package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.TsModel;
import ru.armd.pbk.dto.nsi.TSModelDTO;

/**
 * Мапер для сопостовления различных типов сущности "модель ТС".
 */
@Mapper
public interface ITSModelMatcher
	extends IDomainMatcher<TsModel, TSModelDTO> {

   ITSModelMatcher INSTANCE = Mappers.getMapper(ITSModelMatcher.class);
}