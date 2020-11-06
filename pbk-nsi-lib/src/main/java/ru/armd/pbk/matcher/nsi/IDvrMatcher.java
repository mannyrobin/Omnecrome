package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Dvr;
import ru.armd.pbk.dto.nsi.DvrDTO;

/**
 * Мапер для сопостовления различных типов сущности "видеорегистратор".
 */
@Mapper
public interface IDvrMatcher
	extends IDomainMatcher<Dvr, DvrDTO> {

   IDvrMatcher INSTANCE = Mappers.getMapper(IDvrMatcher.class);
}