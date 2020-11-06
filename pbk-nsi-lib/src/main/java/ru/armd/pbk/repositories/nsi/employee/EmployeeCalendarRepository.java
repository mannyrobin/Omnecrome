package ru.armd.pbk.repositories.nsi.employee;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.components.BaseComponent;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.nsi.employee.EmployeeCalendarMapper;
import ru.armd.pbk.views.nsi.employee.EmployeeCalendarView;

import java.util.Date;
import java.util.List;

/**
 * Репозиторий режима работы сотрудника.
 */
@Repository
public class EmployeeCalendarRepository
	extends BaseComponent {

   @Autowired
   private EmployeeCalendarMapper employeeCalendarMapper;

   /**
	* Календарь на период для сотрудника.
	*
	* @param employeeId id сотрудника
	* @param from       с
	* @param to         по
	* @return
	*/
   public List<EmployeeCalendarView> getCalendarForPeriod(Long employeeId, Date from, Date to) {
	  List<EmployeeCalendarView> calendar = null;
	  try {
		 calendar = employeeCalendarMapper.getCalendarForPeriod(employeeId, from, to);
	  } catch (Exception e) {
		 String message = "Не удалось получить список дней календаря на период. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, NsiAuditType.NSI_EMPLOYEE_CALENDAR, AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return calendar;
   }
}
