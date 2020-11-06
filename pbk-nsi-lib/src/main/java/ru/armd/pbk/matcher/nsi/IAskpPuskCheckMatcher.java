package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.askppuskcheck.AskpPuskCheck;
import ru.armd.pbk.dto.nsi.askppuskcheck.AskpPuskCheckDTO;

/**
 * Маппер для сопоставления различных типов сущности "Данные от АСКП".
 */
@Mapper
public interface IAskpPuskCheckMatcher
	extends IDomainMatcher<AskpPuskCheck, AskpPuskCheckDTO> {

   IAskpPuskCheckMatcher INSTANCE = Mappers.getMapper(IAskpPuskCheckMatcher.class);
}
