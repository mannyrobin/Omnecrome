package ru.armd.pbk.mappers.viss.gismgt;

import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;

/**
 * Базовый маппер для БД ГИС МГТ.
 *
 * @param <Domain> - Домен.
 */
public interface IGmMapper<Domain extends BaseDomain>
	extends IDomainMapper<Domain> {

   /**
	* Получить домен по МУИД ГИС МГТ.
	*
	* @param muid - муид гис мгт.
	* @return
	*/
   Domain getByMuid(Long muid);

}
