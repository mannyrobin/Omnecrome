package ru.armd.pbk.mappers.nsi.bsonumberrule;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.bso.Bso;
import ru.armd.pbk.domain.nsi.bsonumberrule.BsoNumberRule;

import java.util.List;

/**
 * Маппер правил формирования номеров БСО.
 */
@IsMapper
public interface BsoNumberRuleMapper
	extends IDomainMapper<BsoNumberRule> {

   /**
	* Возвращает список БСО для указанного правила.
	*
	* @param ruleId ИД правила.
	* @return
	*/
   List<Bso> getBsosByRule(@Param("ruleId") Long ruleId);

   /**
	* Возвращает максимальное значение номера БСО для данного правила БСО начиная с текущего значения.
	*
	* @param ruleId ИД правила
	* @param code   код
	* @param incr   инкремент
	* @return
	*/
   List<Long> getLastNumberForRule(@Param("ruleId") Long ruleId, @Param("code") String code, @Param("incr") Long incr);
}
