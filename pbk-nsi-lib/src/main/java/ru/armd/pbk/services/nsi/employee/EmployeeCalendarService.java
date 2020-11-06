package ru.armd.pbk.services.nsi.employee;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.repositories.nsi.employee.EmployeeCalendarRepository;
import ru.armd.pbk.views.nsi.employee.EmployeeCalendarView;

import java.util.Date;
import java.util.List;

/**
 * Сервис режима работы сотрудника.
 */
@Service
public class EmployeeCalendarService {
   @SuppressWarnings("unused")
   private static final Logger LOG = Logger.getLogger(EmployeeCalendarService.class);

   @Autowired
   private EmployeeCalendarRepository employeeCalendarRepository;

   /**
	* Календарь на период для сотрудника.
	*
	* @param employeeId id сотрудника
	* @param from       с
	* @param to         по
	* @return
	*/
   public List<EmployeeCalendarView> getCalendarForPeriod(Long employeeId, Date from, Date to) {
	  return employeeCalendarRepository.getCalendarForPeriod(employeeId, from, to);
   }
}