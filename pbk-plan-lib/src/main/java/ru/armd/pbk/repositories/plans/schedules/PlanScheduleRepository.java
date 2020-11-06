package ru.armd.pbk.repositories.plans.schedules;

import org.apache.ibatis.annotations.Param;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.BaseSelectListParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.plans.schedules.PlanScheduleView;
import ru.armd.pbk.domain.plans.schedules.ScheduleShift;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.PlanAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.plans.brigades.PlanBrigadeMapper;
import ru.armd.pbk.mappers.plans.schedules.PlanScheduleMapper;

import java.util.Date;
import java.util.List;

/**
 * Репозиторий расписания планов отдела.
 */
@Repository
public class PlanScheduleRepository
	extends BaseDomainRepository<ScheduleShift> {

   public static final Logger LOGGER = Logger.getLogger(PlanScheduleRepository.class);

   @Autowired
   private PlanScheduleMapper planScheduleMapper;

   @Autowired
   private PlanBrigadeMapper planBrigadeMapper;

   @Autowired
   PlanScheduleRepository(PlanScheduleMapper mapper) {
	  super(PlanAuditType.PLAN_SCHEDULE, mapper);
   }

   /**
	* Получает домен расписания плана отдела по параметрам.
	*
	* @param params параметры
	* @return домен расписания плана отдела
	*/
   public PlanScheduleView getPlanSchedules(BaseGridViewParams params) {
	  PlanScheduleView planSchedule = new PlanScheduleView();
	  try {
		 planSchedule.setEmployees(planScheduleMapper.getEmployeeForPlanViews(params));
		 planSchedule.setReserveCount(planBrigadeMapper.getBrigadeReserveCounts(params));
		 if (planSchedule.getEmployees() != null && !planSchedule.getEmployees().isEmpty()) {
			planSchedule.setScheduleShifts(
				planScheduleMapper.getPlanSchedulesForEmployees(params, planSchedule.getEmployees()));
			planSchedule.setReserves(planScheduleMapper.getReserveList(params));
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список для отображения расписания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, PlanAuditType.PLAN_SCHEDULE, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return planSchedule;
   }

   /**
	* Получить смену расписания по параметрам.
	*
	* @param params параметры
	* @return смену расписания по параметрам
	*/
   public ScheduleShift getSchedule(BaseSelectListParams params) {
	  ScheduleShift result = null;
	  try {
		 result = planScheduleMapper.getSchedule(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить смену расписания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return result;
   }

   /**
	* Получить смену расписания по id задания.
	*
	* @param taskId id задания
	* @return смену расписания по параметрам
	*/
   public ScheduleShift getScheduleByTaskId(Long taskId) {
	  ScheduleShift result = null;
	  try {
		 result = planScheduleMapper.getScheduleByTaskId(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось получить смену расписания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return result;
   }

   /**
	* Получить смену расписания на замену по id задания.
	*
	* @param taskId id задания
	* @return смену расписания на замену по параметрам
	*/
   public ScheduleShift getChangeScheduleByTaskId(Long taskId) {
	  ScheduleShift result = null;
	  try {
		 result = planScheduleMapper.getChangeScheduleByTaskId(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось получить смену расписания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return result;
   }

   /**
	* Получить смену причины замены по id расписания.
	*
	* @param scheduleId id расписания
	* @return смену причины замены
	*/
   public Long getCauseShiftId(Long scheduleId) {
	  Long result = null;
	  try {
		 result = planScheduleMapper.getCauseShiftId(scheduleId);
	  } catch (Exception e) {
		 String message = "Не удалось получить причину замены. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return result;
   }

   /**
	* Найти расписание контроллера.
	*
	* @param employeeId контроллер
	* @param workDate   дата
	* @return
	*/
   public ScheduleShift getPlanScheduleCell(Long employeeId, Date workDate) {
	  return planScheduleMapper.getPlanScheduleCell(employeeId, workDate);
   }

   /**
	* Получение списка расписания для отображения в комбобоксах при создании
	* заданий.
	*
	* @param params Параметры фильтрации.
	* @return списка расписания.
	*/
   public List<SelectItem> getSelectItemsForCreatingTask(BaseSelectListParams params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = planScheduleMapper.getSelectItemsForCreatingTask(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

   /**
	* Получение списка сотрудников для которых не существует расписания
	* в заданном временом промежутке.
	*
	* @param params Параметры фильтрации.
	* @return списка расписания.
	*/
   public List<SelectItem> getEmployeesWithoutSchedule(BaseSelectListParams params) {
	  List<SelectItem> sList = null;
	  try {
		 sList = planScheduleMapper.getEmployeesWithoutSchedule(params.getFilter());
	  } catch (Exception e) {
		 String message = "Не удалось получить список SelectItems. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return sList;
   }

	public List<ScheduleShift> getScheduleForCheckingMode() {
		List<ScheduleShift> result;
		try {
			result = planScheduleMapper.getScheduleForCheckingMode();
		} catch (Exception e) {
			String message = "Не удалось получить расписание. Ошибка: " + e.getMessage();
			logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
			throw new PBKException(message, e);
		}
		return result;
	}

	public List<ScheduleShift> getPlanScheduleCells(Long employeeId, Date dateFrom) {
		List<ScheduleShift> result;
		try {
			result = planScheduleMapper.getPlanScheduleCells(employeeId, dateFrom);
		} catch (Exception e) {
			String message = "Не удалось получить расписание. Ошибка: " + e.getMessage();
			logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
			throw new PBKException(message, e);
		}
		return result;
	}



}