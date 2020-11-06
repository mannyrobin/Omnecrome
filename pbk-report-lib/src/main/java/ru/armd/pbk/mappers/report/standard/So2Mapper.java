package ru.armd.pbk.mappers.report.standard;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.mappers.IDomainMapper;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.So2ShiftHours;
import ru.armd.pbk.views.report.So2EmployeeView;

import java.util.Date;
import java.util.List;

/**
 * Маппер стандартного отчёта "Табель фактически отработанного времени".
 */
@IsMapper
public interface So2Mapper
	extends IDomainMapper<BaseDomain> {

   /**
	* Получить список контролёров.
	*
	* @param params параметры фильтрации
	* @return список контролёров
	*/
   List<So2EmployeeView> getEmployees(BaseGridViewParams params);

   /**
	* Получить отработанное время контролёров за все дни.
	*
	* @param params    параметры фильтрации
	* @param employees id контролёры
	* @return отработанное время
	*/
   List<So2ShiftHours> getShiftHoursForEmployee(@Param("params") BaseGridViewParams params, @Param("employees") List<So2EmployeeView> employees);

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
