package ru.armd.pbk.mappers.nsi.district;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.domain.nsi.district.District;

import java.util.List;

/**
 * Маппер для работы с "Сопутствующие маршруты".
 */
@IsMapper
public interface DistrictMapper
	extends IVersionDomainMapper<District> {

   /**
	* Получение районнов, которые можно привязать к месту встречи.
	*
	* @param venueId ИД
	* @return
	*/
   List<ISelectItem> getDistrictsByVenueId(@Param("venueId") Long venueId);

   /**
	* Получить территорию района.
	*
	* @param id - ИД района.
	* @return
	*/
   List<String> getEgko(@Param("id") Long id);

   /**
	* Получение привязаных районов к месту встречи.
	*
	* @param venueId ИД
	* @return
	*/
   List<ISelectItem> getDistrictsBelongsVenue(@Param("venueId") Long venueId);
}
