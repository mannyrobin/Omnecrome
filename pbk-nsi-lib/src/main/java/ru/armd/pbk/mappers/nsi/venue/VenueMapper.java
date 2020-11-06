package ru.armd.pbk.mappers.nsi.venue;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.mappers.IVersionDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.ISelectItem;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.venue.Venue;
import ru.armd.pbk.domain.nsi.venue.VenueDistrict;
import ru.armd.pbk.views.nsi.venue.VenueHistoryView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Маппер мест втреч.
 */
@IsMapper
public interface VenueMapper
	extends IVersionDomainMapper<Venue> {

   /**
	* Получение списка мест встреч для отображения в комбобоксах планов.
	*
	* @param params параметры фильтрации.
	* @return список мест встреч объектов.
	*/
   List<ISelectItem> getSelectItemsForPlan(Map<String, Object> params);

   /**
	* Получение списка мест встреч для подразделения {@code deptId}
	* на дату {@code workDate}.
	*
	* @param deptId   подразделение
	* @param workDate дата
	* @return
	*/
   List<Venue> getCityVenueByDept(@Param("deptId") Long deptId, @Param("workDate") Date workDate);

   /**
	* Связывание места встречи и района.
	*
	* @param dto дто МВ.
	* @return
	*/
   int linkDistrict(Venue dto);

   /**
	* Удаление связи места встречи и района.
	*
	* @param dto дто МВ.
	* @return
	*/
   int unlinkDistrict(Venue dto);

   /**
	* Связывание ночного места встречи и подразделения.
	*
	* @param dto дто МВ.
	* @return
	*/
   int linkDepartment(Venue dto);

   /**
	* Удаление связи ночного места встречи и района.
	*
	* @param dto дто МВ.
	* @return
	*/
   int unlinkDepartment(Venue dto);

   /**
	* Получение выпадающего списка для подразделений.
	*
	* @param venueId ИД МВ.
	* @return
	*/
   List<ISelectItem> getSelectDepts(@Param("venueId") Long venueId);

   /**
	* Получение списка районов для места встречи.
	*
	* @param params пармаетры
	* @return список
	*/
   List<VenueHistoryView> getVenueDistrictsHistoryViews(BaseGridViewParams params);

   /**
	* Получение списка районов для места встречи.
	*
	* @param ids ИДС.
	* @return список
	*/
   List<VenueDistrict> getVenueDistrictByIds(List<Long> ids);

   /**
	* Получить список объектов для выпадаюшего списка фильтра райнов места
	* встречи.
	*
	* @param params фильтры.
	* @return список объектов для выпадаюшего списка фильтра райнов места
	* встречи
	*/
   List<ISelectItem> getSelectDistricts(Map<String, Object> params);

   /**
	* Получить список объектов для выпадаюшего списка фильтра маршрутов места
	* встречи.
	*
	* @param params фильтры.
	* @return список объектов для выпадаюшего списка фильтра маршрутов места
	* встречи
	*/
   List<SelectItem> getSelectVenueDistrictRoutes(Map<String, Object> params);

   /**
	* Получить список объектов для выпадаюшего списка фильтра остановок места
	* встречи.
	*
	* @param params фильтры.
	* @return список объектов для выпадаюшего списка фильтра остановок места
	* встречи
	*/
   List<SelectItem> getSelectStops(Map<String, Object> params);

   /**
	* Обновить ссылку.
	*
	* @return
	*/
   int updateHead();
}