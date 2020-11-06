package ru.armd.pbk.mappers.nsi.employee;

import org.apache.ibatis.annotations.Param;
import ru.armd.pbk.annotations.mappers.IsMapper;
import ru.armd.pbk.views.nsi.employee.EmployeeCalendarView;

import java.util.Date;
import java.util.List;

/**
 * Маппер для работы с режимом работы сотрудника.
 */
@IsMapper
public interface EmployeeCalendarMapper {
   /**
	* Календарь на период для сотрудника.
	*
	* @param employeeId id сотрудника
	* @param from       с
	* @param to         по
	* @return
	*/
   List<EmployeeCalendarView> getCalendarForPeriod(@Param("employeeId") Long employeeId, @Param("from") Date from,
												   @Param("to") Date to);
}
