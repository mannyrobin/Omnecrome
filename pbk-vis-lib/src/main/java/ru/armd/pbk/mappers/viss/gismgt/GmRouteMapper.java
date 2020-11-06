package ru.armd.pbk.mappers.viss.gismgt;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.domain.viss.gismgt.GmRoute;

/**
 * Маппер для работы с Route.
 */
@IsMapper
public interface GmRouteMapper
	extends IGmMapper<GmRoute> {

   GmRoute getRouteByRouteTrajectoryMuid(Long muid);

}
