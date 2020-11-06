package ru.armd.pbk.mappers.viss.gtfs;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.viss.gtfs.GtfsChangeEquipment;

/**
 * Маппер изменения бортового оборудования на ТС.
 */
@IsMapper
public interface GtfsChangeEquipmentMapper
	extends IDomainMapper<GtfsChangeEquipment> {

}
