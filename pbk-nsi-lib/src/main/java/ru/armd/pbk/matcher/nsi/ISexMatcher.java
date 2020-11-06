package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Sex;
import ru.armd.pbk.dto.nsi.SexDTO;

/**
 * Маппер сопоставления различных типов сущности "Пол".
 *
 * @author nikita_chebotaryov
 */
@Mapper
public interface ISexMatcher
	extends IDomainMatcher<Sex, SexDTO> {

   ISexMatcher INSTANCE = Mappers.getMapper(ISexMatcher.class);
}
