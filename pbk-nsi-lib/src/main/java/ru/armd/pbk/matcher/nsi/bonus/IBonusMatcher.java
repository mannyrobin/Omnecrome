package ru.armd.pbk.matcher.nsi.bonus;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.bonus.Bonus;
import ru.armd.pbk.dto.nsi.bonus.BonusDTO;

/**
 * Маппер для сопоставления различных типов сущности "Премирование".
 */
@Mapper
public interface IBonusMatcher
	extends IDomainMatcher<Bonus, BonusDTO> {

   IBonusMatcher INSTANCE = Mappers.getMapper(IBonusMatcher.class);

}
