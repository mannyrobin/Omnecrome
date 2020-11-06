package ru.armd.pbk.mappers.report.standard;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.views.report.So12EmployeeCountView;
import ru.armd.pbk.views.report.So12VenueShiftView;

import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * Маппер стандартного отчёта "Совместный график по местам встреч".
 */
@IsMapper
public interface So12Mapper
	extends IDomainMapper<BaseDomain> {

   /**
	* Получить список мест встреч.
	*
	* @param params параметры фильтрации
	* @return список мест встреч
	*/
   List<So12VenueShiftView> getVenueShifts(BaseGridViewParams params);

   /**
	* Получить набор пар "место встречи - смена" -
	* "количества контролёров по дням".
	*
	* @param params  параметры фильтрации
	* @param venueId id места встречи
	* @param shiftId id смены
	* @return набор пар
	*/
   List<So12EmployeeCountView> getEmployeeCountsForVenueShift(@Param("params") BaseGridViewParams params,
															  @Param("venueId") Long venueId, @Param("shiftId") Long shiftId);

   /**
	* Получить дату начала периода (на самом деле, берётся из фильтра,
	* переданного в params, т.е. это workaround)
	*
	* @param params параметры фильтрации
	* @return самая первая дата периода
	*/
   Date getDateStart(BaseGridViewParams params);

   /**
	* Получить дату конца периода (на самом деле, берётся из фильтра,
	* переданного в params, т.е. это workaround)
	*
	* @param params параметры фильтрации
	* @return самая последняя дата периода
	*/
   Date getDateEnd(BaseGridViewParams params);

   /**
	* Получает значения для строк "итого".
	*
	* @param <View> тип представления.
	* @param params параметры
	* @return значения для строк "итого"
	*/
   <View extends BaseGridView> List<View> getGridViewTotal(Map<String, Object> params);
}
