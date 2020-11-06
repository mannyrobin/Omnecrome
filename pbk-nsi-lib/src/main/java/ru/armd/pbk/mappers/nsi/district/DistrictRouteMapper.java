package ru.armd.pbk.mappers.nsi.district;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.district.DistrictRoute;

import java.util.List;

/**
 * Маппер маршрутов "Тип сопутствующего маршрута".
 */
@IsMapper
public interface DistrictRouteMapper
	extends IVersionDomainMapper<DistrictRoute> {

   /**
	* Получить список районов маршрута.
	*
	* @param routeId - ИД маршрута.
	* @return
	*/
   List<ISelectItem> getDistrictsByRoute(@Param("routeId") Long routeId);

}
