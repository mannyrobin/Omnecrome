package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.Shift;
import ru.armd.pbk.dto.nsi.ShiftDTO;

/**
 * Мапер для сопостовления различных типов сущности "смена".
 */
@Mapper
public interface IShiftMatcher
	extends IDomainMatcher<Shift, ShiftDTO> {

   IShiftMatcher INSTANCE = Mappers.getMapper(IShiftMatcher.class);
}