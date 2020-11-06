package ru.armd.pbk.core.mappers;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;

import java.util.List;

/**
 * Маппер для приборов с историей владения.
 *
 * @param <VersionDomain> версионный домен
 */
public interface IDeviceOwnersHistoryMapper<VersionDomain extends BaseVersionDomain>
	extends IVersionDomainMapper<VersionDomain> {

   /**
	* Получение истории смена владельцев у прибора.
	*
	* @param params параметры с идентификатором устройства.
	* @return
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getOwnersHistoryViews(Params params);
}
