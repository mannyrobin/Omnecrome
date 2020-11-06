package ru.armd.pbk.mappers.report.standard;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.views.report.So23EmployeeView;
import ru.armd.pbk.views.report.So23DaySummariesView;
import ru.armd.pbk.views.report.So23DaysTotalSummariesView;

import java.util.Date;
import java.util.List;

/**
 * Маппер стандартного отчёта "Сводные данные по работе контролеров за период".
 */
@IsMapper
public interface So23Mapper
	extends IDomainMapper<BaseDomain> {

   /**
	* Получить список контролеров.
	*
	* @param params параметры фильтрации
	* @return список контролеров
	*/
   List<So23EmployeeView> getEmployees(BaseGridViewParams params);

   /**
	* Получить табельный для контролера.
	*
	* @param params   параметры фильтрации
	* @param employeesId id контролера
	* @return табельный номер
	*/
   List<So23EmployeeView> getPersonalNumber(BaseGridViewParams params);

    /**
	* Получить сводные данные по контролёрам.
	*
	* @param params   параметры фильтрации
	* @param employeesId id контролера
	* @return сводные данные по контролёрам по датам
	*/
   List<So23DaySummariesView> getDaysSummariesForEmployee(@Param("params") BaseGridViewParams params, @Param("employeesId") Long employeesId);

     /**
	* Получить сводные данные по контролёрам итого за период.
	*
	* @param params   параметры фильтрации
	* @param employeesId id контролера
	* @return сводные данные по контролёрам итого за период
	*/
   So23DaysTotalSummariesView getDaysTotalSummariesForEmployee(@Param("params") BaseGridViewParams params, @Param("employeesId") Long employeesId);

   /**
	* Получить дату начала периода (на самом деле, берётся из фильтра, переданного в params, т.е. это workaround)
	*
	* @param params параметры фильтрации
	* @return самая первая дата периода
	*/
   Date getDateStart(BaseGridViewParams params);

   /**
	* Получить дату конца периода (на самом деле, берётся из фильтра, переданного в params, т.е. это workaround)
	*
	* @param params параметры фильтрации
	* @return самая последняя дата периода
	*/
   Date getDateEnd(BaseGridViewParams params);
}
