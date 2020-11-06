package ru.armd.pbk.services.plans.employees;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseService;
import ru.armd.pbk.domain.nsi.employee.Employee;
import ru.armd.pbk.domain.nsi.employee.EmployeeDepartmentMove;
import ru.armd.pbk.domain.nsi.employee.EmployeeWorkMode;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.repositories.nsi.bso.BsoRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeDepartmentMoveRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeWorkModeRepository;
import ru.armd.pbk.services.plans.PlansService;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * Сервис для работы с планированием сотрудника.
 */
@Service
public class PlanEmployeeService
	extends BaseService {

	public static final Logger LOGGER = Logger.getLogger(PlanEmployeeService.class);

   private static final String EASU_FHD_END_DATE = "9999-12-31";

   @Autowired
   private EmployeeRepository employeeRepository;

   @Autowired
   private PlansService plansService;

   @Autowired
   private EmployeeWorkModeRepository repository;

   @Autowired
   private BsoRepository bsoRepository;

   @Autowired
   private EmployeeDepartmentMoveRepository employeeDepartmentMoveRepository;

   /**
	* Уволить сотрудника.
	*
	* @param employeeId - ИД сотрудника
	* @param deptId     - подразделение
	* @param fireDate   - дата увольнения
	*/
   @Transactional
   public void fireEmployee(Long employeeId, Long deptId, Date fireDate) {
	  fireEmployee(employeeId, deptId, fireDate, true);
   }

   /**
	* Уволить сотрудника.
	*
	* @param employeeId - ИД сотрудника
	* @param deptId     - подразделение
	* @param fireDate   - дата увольнения
	* @param deleteEq   - удалить устройства
	*/
   protected void fireEmployee(Long employeeId, Long deptId, Date fireDate, Boolean deleteEq) {
	  Employee employee = employeeRepository.getActual(employeeId);
	  if (employee != null) {
		  Boolean forPlanUse = employee.getForPlanUse();
		  employee.setForPlanUse(false);
		  employee.setFireDate(fireDate);
		  employee.setVersionStartDate(new Date());
		  if (deleteEq) {
			  employee.setDvrId(null);
			  employee.setContCardId(null);
			  employee.setOffCardId(null);
			  employee.setPuskId(null);
			  //Закрытие периода необходимо только при уволнении сотрудника.
			  //При переводе сотрудника уже периоды уже актуально выставлены.
			  EmployeeDepartmentMove last = employeeDepartmentMoveRepository.getLastDepartmentMove(employeeId);
			  if (last != null && last.getPeriodEndDate().after(fireDate)) {
				  last.setPeriodEndDate(fireDate);
				  employeeDepartmentMoveRepository.save(last);
			  }
		  }
		  employeeRepository.saveVersion(employee);

		  if (forPlanUse) {
			  bsoRepository.unbindNonUseBsosForEmployee(employeeId);
			  //Отменяем задания сотрудника и заменяем их заданиями с резерва
			  plansService.cancelTasksForDept(deptId, fireDate);
		  }
	  } else {
		  LOGGER.warn("Can't find employee for employeeId during fire employee:" + employeeId + "fireDate: " + fireDate);
	  }
   }

   /**
	* Вернуть на работу сотрудника.
	*
	* @param employeeId - ИД сотрудника
	* @param deptId     - подразделение
	* @param fireDate   - дата увольнения
	*/
   @Transactional
   public void unfireEmployee(Long employeeId, Long deptId) {
	  unfireEmployee(employeeId, deptId, true);
   }

   /**
	* Вернуть на работу сотрудника.
	*
	* @param employeeId - ИД сотрудника
	* @param deptId     - подразделение
	* @param deleteEq   - удалить устройства
	*/
   protected void unfireEmployee(Long employeeId, Long deptId, Boolean deleteEq) {
	  Employee employee = employeeRepository.getActual(employeeId);
	  if (employee.getFireDate() == null) {
		 return;
	  }
	  Employee lastButOne = employeeRepository.getLastButOneFireActual(employeeId);
	  employee.setForPlanUse(lastButOne == null ? false : lastButOne.getForPlanUse());
	  employee.setFireDate(null);
	  if (deleteEq && lastButOne != null) {
		 Map<String, Object> filter = new HashMap<>();
		 if (lastButOne.getPuskId() != null) {
			filter.put("puskId", lastButOne.getPuskId());
			filter.put("currentId", lastButOne.getId());
			if (employeeRepository.checkEmployeeVersion(filter)) {
			   throw new PBKValidationException("puskId",
				   "ПУсК из выбранной версии уже используется другим сотрудником!");
			}
		 }
		 if (lastButOne.getDvrId() != null) {
			filter.clear();
			filter.put("dvrId", lastButOne.getDvrId());
			filter.put("currentId", lastButOne.getId());
			if (employeeRepository.checkEmployeeVersion(filter)) {
			   throw new PBKValidationException("dvrId",
				   "Видеорегистратор из выбранной версии уже используется другим сотрудником!");
			}
		 }
		 if (lastButOne.getContCardId() != null) {
			filter.clear();
			filter.put("contactlessCardId", lastButOne.getContCardId());
			filter.put("currentId", lastButOne.getId());
			if (employeeRepository.checkEmployeeVersion(filter)) {
			   throw new PBKValidationException("contCardId",
				   "БСК из выбранной версии уже используется другим сотрудником!");
			}
		 }
		 if (lastButOne.getOffCardId() != null) {
			filter.clear();
			filter.put("officialCardId", lastButOne.getOffCardId());
			filter.put("currentId", lastButOne.getId());
			if (employeeRepository.checkEmployeeVersion(filter)) {
			   throw new PBKValidationException("offCardId",
				   "ССК из выбранной версии уже используется другим сотрудником!");
			}
		 }
		 employee.setDvrId(lastButOne.getDvrId());
		 employee.setContCardId(lastButOne.getContCardId());
		 employee.setOffCardId(lastButOne.getOffCardId());
		 employee.setPuskId(lastButOne.getPuskId());
	  }
	  employee.setVersionStartDate(new Date());
	  employeeRepository.saveVersion(employee);
	  EmployeeDepartmentMove last = employeeDepartmentMoveRepository.getLastDepartmentMove(employeeId);
	  DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
	  if (last != null && !df.format(last.getPeriodEndDate()).equals(EASU_FHD_END_DATE)) {
		 try {
			last.setPeriodEndDate(df.parse(EASU_FHD_END_DATE));
			employeeDepartmentMoveRepository.save(last);
		 } catch (ParseException e) {
			throw new PBKValidationException("periodEndDate",
				"Не корректная дата окончания работы в подразделении!");
		 }
	  }
   }

   /**
	* Переместить из одного старого в новое подразделение.
	*
	* @param employeeId - ИД сотрудника
	* @param lastDeptId - старое подразделение
	* @param currDeptId - новое подразделение
	* @param moveDate   - дата перехода
	*/
   @Transactional
   public void moveEmployee(Long employeeId, Long lastDeptId, Long currDeptId, Date moveDate) {
	  Employee employee = employeeRepository.getActual(employeeId);
	  Boolean forPlanUse = employee.getForPlanUse();
	  fireEmployee(employeeId, lastDeptId, moveDate, false);
	  employee = employeeRepository.getActual(employeeId);
	  employee.setForPlanUse(forPlanUse);
	  employee.setFireDate(null);
	  employee.setDepartmentId(currDeptId);
	  employeeRepository.save(employee);
	  //Перемещаем сотрудника
	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("employeeId", employeeId);
	  params.put("dateFrom", moveDate);
	  for (EmployeeWorkMode workMode : repository.getDomains(params)) {
		 plansService.checkControllerGraph(employeeId, workMode.getWorkDate(),
				 workMode.getWorkModeId(), workMode.getWorkPlanHours(), moveDate, true);
	  }
	  plansService.processShiftModes(employee.getPersonalNumber());
   }

}
