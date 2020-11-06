package ru.armd.pbk.mappers.nsi.device.history;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDeviceOwnerHistoryDomain;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;

/**
 * Маппер истории БСК.
 */
@IsMapper
public interface ContactlessCardHistoryMapper
	extends IVersionDomainMapper<BaseDeviceOwnerHistoryDomain> {

}
