package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Duty;
import ru.armd.pbk.dto.nsi.DutyDTO;

/**
 * Мапер для сопостовления различных типов сущности "наряд".
 */
@Mapper
public interface IDutyMatcher
	extends IDomainMatcher<Duty, DutyDTO> {

   IDutyMatcher INSTANCE = Mappers.getMapper(IDutyMatcher.class);
}
