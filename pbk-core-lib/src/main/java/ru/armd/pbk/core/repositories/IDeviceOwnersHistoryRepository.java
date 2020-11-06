package ru.armd.pbk.core.repositories;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.mappers.IDeviceOwnersHistoryMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;

import java.util.List;

/**
 * Интерфейс репозитория для усройств с поддержкой истории смены владельцев.
 */
public interface IDeviceOwnersHistoryRepository<VersionDomain extends BaseVersionDomain>
	extends IVersionDomainRepository<VersionDomain> {

   /**
	* Возвращает маппер домена.
	*
	* @return
	*/
   IDeviceOwnersHistoryMapper<VersionDomain> getOwnerHistoryDomainMapper();

   /**
	* Возвращает историю смены владельце для приборов.
	*
	* @param params
	* @param <Views>
	* @param <Params>
	* @return
	*/
   <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getOwnersHistoryViews(Params params);
}
