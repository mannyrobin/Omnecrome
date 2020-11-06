package ru.armd.pbk.core.mappers;

import ru.armd.pbk.core.domain.BaseDomain;

/**
 * Интерфейс маппера с общими методами работы со справочными доменами.
 */
public interface IDictionaryMapper<Domain extends BaseDomain>
	extends IMapper {

   /**
	* Выбрать domain по code.
	*
	* @param code code.
	* @return domain.
	*/
   Domain getByCode(String code);

}
