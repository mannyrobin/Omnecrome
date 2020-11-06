package ru.armd.pbk.mappers.nsi.venue;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.domain.nsi.venue.VenueDistrict;
import ru.armd.pbk.domain.nsi.venue.VenueRoute;

/**
 * Маппер для работы с "Сопутствующие маршруты".
 */
@IsMapper
public interface VenueRouteMapper
	extends IVersionDomainMapper<VenueRoute> {

   /**
	* Отвязать маршруты от связки район - место встречи.
	*
	* @param domain домен.
	*/
   void unlinkRoutes(VenueDistrict domain);

}
