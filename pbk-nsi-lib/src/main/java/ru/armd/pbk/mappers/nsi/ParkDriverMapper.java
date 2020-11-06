package ru.armd.pbk.mappers.nsi;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.domain.nsi.ParkDriver;

/**
 * Маппер водителей парка.
 */
@IsMapper
public interface ParkDriverMapper
	extends IVersionDomainMapper<ParkDriver> {

}
