package ru.armd.pbk.mappers.nsi;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.domain.nsi.Park;

/**
 * Маппер для операций с парками.
 *
 * @author nikita_chebotaryov
 */
@IsMapper
public interface ParkMapper
	extends IVersionDomainMapper<Park> {

}
