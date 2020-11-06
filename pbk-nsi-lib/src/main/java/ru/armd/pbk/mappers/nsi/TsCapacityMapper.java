package ru.armd.pbk.mappers.nsi;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.mappers.ISoftDeleteMapper;
import ru.armd.pbk.domain.nsi.TsCapacity;

/**
 * Маппер вместимость ТС.
 */
@IsMapper
public interface TsCapacityMapper
	extends IDomainMapper<TsCapacity>, ISoftDeleteMapper {

}
