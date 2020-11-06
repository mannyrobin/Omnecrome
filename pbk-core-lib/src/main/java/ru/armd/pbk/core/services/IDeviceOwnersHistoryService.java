package ru.armd.pbk.core.services;

import ru.armd.pbk.core.domain.BaseVersionDomain;
import ru.armd.pbk.core.dto.BaseVersionDTO;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.utils.json.JsonGridData;

/**
 * Интерфейс сервиса устройств с поддержкой истории владения.
 */
public interface IDeviceOwnersHistoryService<DomainVersion extends BaseVersionDomain, DTOVersion extends BaseVersionDTO>
	extends IVersionDomainService<DomainVersion, DTOVersion> {

   <Views extends BaseGridView, Params extends BaseGridViewParams> JsonGridData getOwnersHistoryViews(Params params);
}
