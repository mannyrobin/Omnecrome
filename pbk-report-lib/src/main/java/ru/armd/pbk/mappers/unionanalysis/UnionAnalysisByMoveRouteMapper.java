package ru.armd.pbk.mappers.unionanalysis;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;

/**
 * Маппер сводного анализа по выходам маршрута.
 */
@IsMapper
public interface UnionAnalysisByMoveRouteMapper
	extends IDomainMapper<BaseDomain> {

}
