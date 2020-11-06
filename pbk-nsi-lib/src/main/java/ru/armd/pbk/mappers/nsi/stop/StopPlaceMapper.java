package ru.armd.pbk.mappers.nsi.stop;

import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.domain.nsi.stop.StopPlace;

/**
 * Маппер для работы со сущностью "Остановочное место".
 */
@IsMapper
public interface StopPlaceMapper
	extends IDomainMapper<StopPlace> {

   /**
	* Получить остоновочное место по ID остановочного пункта.
	*
	* @param id - ИД остановочного пункта.
	* @return остановочное место.
	*/
   StopPlace getStopPlaceByGmStopId(Long id);

}
