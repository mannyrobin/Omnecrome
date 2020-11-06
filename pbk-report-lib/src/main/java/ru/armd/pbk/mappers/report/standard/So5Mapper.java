package ru.armd.pbk.mappers.report.standard;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.views.report.So5BranchView;
import ru.armd.pbk.views.report.So5DaySummariesView;
import ru.armd.pbk.views.report.So5DaysTotalSummariesView;

import java.util.Date;
import java.util.List;

/**
 * Маппер стандартного отчёта "Посменная численность контролёров ГУП "Мосгортранс" и среднее значение
 * численности за период".
 */
@IsMapper
public interface So5Mapper
	extends IDomainMapper<BaseDomain> {

   /**
	* Получить список эксплуатационных филиалов.
	*
	* @param params параметры фильтрации
	* @return список эксплуатационных филиалов
	*/
   List<So5BranchView> getBranches(BaseGridViewParams params);

   /**
	* Получить численность (ПЛАН) для данного филиала.
	*
	* @param params   параметры фильтрации
	* @param branchId id филиала
	* @return численность (ПЛАН)
	*/
   int getPlanCountForBranch(@Param("params") BaseGridViewParams params, @Param("branchId") Long branchId);

   /**
	* Получить численность (ФАКТ) для данного филиала.
	*
	* @param params   параметры фильтрации
	* @param branchId id филиала
	* @return численность (ФАКТ)
	*/
   int getFactCountForBranch(@Param("params") BaseGridViewParams params, @Param("branchId") Long branchId);

   /**
	* Получить сводные данные по контролёрам по датам для данного филиала.
	*
	* @param params   параметры фильтрации
	* @param branchId id филиала
	* @return сводные данные по контролёрам по датам
	*/
   List<So5DaySummariesView> getDaysSummariesForBranch(@Param("params") BaseGridViewParams params, @Param("branchId") Long branchId);

   /**
	* Получить сводные данные по контролёрам итого за период для данного филиала.
	*
	* @param params   параметры фильтрации
	* @param branchId id филиала
	* @return сводные данные по контролёрам итого за период
	*/
   So5DaysTotalSummariesView getDaysTotalSummariesForBranch(@Param("params") BaseGridViewParams params, @Param("branchId") Long branchId);

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
