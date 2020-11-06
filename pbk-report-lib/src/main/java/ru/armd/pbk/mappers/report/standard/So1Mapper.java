package ru.armd.pbk.mappers.report.standard;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.views.report.So1EmployeeView;
import ru.armd.pbk.views.report.So1ShiftView;

import java.util.Date;
import java.util.List;

/**
 * Маппер стандартного отчёта "График работы контролеров по территориальному подразделению".
 */
@IsMapper
public interface So1Mapper
	extends IDomainMapper<BaseDomain> {

   /**
	* Получить список контролёров.
	*
	* @param params параметры фильтрации
	* @return список сотрудников
	*/
   List<So1EmployeeView> getEmployees(BaseGridViewParams params);

   /**
	* Получить расписание (список смен) контролёра.
	*
	* @param params     параметры фильтрации
	* @param employeeId id контролёра
	* @return расписание (список смен)
	*/
   List<So1ShiftView> getScheduleForEmployee(@Param("params") BaseGridViewParams params, @Param("employeeId") Long employeeId);

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
