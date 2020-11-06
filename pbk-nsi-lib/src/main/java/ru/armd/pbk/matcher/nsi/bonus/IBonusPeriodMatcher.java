package ru.armd.pbk.matcher.nsi.bonus;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.bonus.BonusPeriod;
import ru.armd.pbk.dto.nsi.bonus.BonusPeriodDTO;

/**
 * Маппер для сопоставления различных типов сущности "Коэфициент премирования".
 */
@Mapper
public interface IBonusPeriodMatcher
	extends IDomainMatcher<BonusPeriod, BonusPeriodDTO> {

   IBonusPeriodMatcher INSTANCE = Mappers.getMapper(IBonusPeriodMatcher.class);

}
