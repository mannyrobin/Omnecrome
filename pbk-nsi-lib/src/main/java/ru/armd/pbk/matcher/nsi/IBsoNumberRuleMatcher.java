package ru.armd.pbk.matcher.nsi;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;
import ru.armd.pbk.core.matcher.IDomainMatcher;
import ru.armd.pbk.domain.nsi.bsonumberrule.BsoNumberRule;
import ru.armd.pbk.dto.nsi.bsonumberrule.BsoNumberRuleDTO;

/**
 * Маппер для сопоставления различных типов сущности "правило формирования номера БСО".
 */
@Mapper
public interface IBsoNumberRuleMatcher
	extends IDomainMatcher<BsoNumberRule, BsoNumberRuleDTO> {

   IBsoNumberRuleMatcher INSTANCE = Mappers.getMapper(IBsoNumberRuleMatcher.class);
}
