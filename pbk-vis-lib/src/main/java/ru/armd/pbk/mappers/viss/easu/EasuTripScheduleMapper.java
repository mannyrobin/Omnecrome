package ru.armd.pbk.mappers.viss.easu;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.viss.easu.EasuTripSchedule;

import java.util.List;

/**
 * Маппер для нарядов ЕАСУ.
 */
@IsMapper
public interface EasuTripScheduleMapper
	extends IDomainMapper<EasuTripSchedule> {

   void insertBulk(List<EasuTripSchedule> list);
}
