package ru.armd.pbk.repositories.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.tasks.PuskTask;
import ru.armd.pbk.domain.tasks.Task;
import ru.armd.pbk.domain.tasks.TaskState;
import ru.armd.pbk.domain.tasks.Tasks;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.PlanAuditType;
import ru.armd.pbk.enums.core.TaskAuditType;
import ru.armd.pbk.enums.nsi.Shift;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.tasks.TaskMapper;
import ru.armd.pbk.utils.date.DateUtils;
import ru.armd.pbk.views.tasks.TaskAdditionalInfo;
import ru.armd.pbk.views.tasks.TaskViewDomain;

import java.util.*;

/**
 * Репозиторий заданий.
 */
@Repository
public class TaskRepository
	extends BaseDomainRepository<Task> {

   public static final Logger LOGGER = Logger.getLogger(TaskRepository.class);

   @Autowired
   TaskRepository(TaskMapper mapper) {
	  super(TaskAuditType.TASK_TASK, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить статус задания.
	*
	* @param taskId ИД задания.
	* @return
	*/
   public TaskState getTaskStateByTaskId(Long taskId) {
	  TaskState state = null;
	  try {
		 state = ((TaskMapper) domainMapper).getTaskStateByTaskId(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось получить статус задания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return state;
   }

   /**
	* Получает Дату для задания по PLAN_SCHEDULES.
	*
	* @param taskId ИД
	*/
   public Date getTaskDate(Long taskId) {
	  Date date = null;
	  try {
		 date = ((TaskMapper) domainMapper).getTaskDate(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось получить дату задания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return date;
   }

   /**
	* Установка статуса задания.
	*
	* @param stateId      ИД статуса.
	* @param taskIds      ИД заданий.
	* @param updateDate   дата изменения.
	* @param updateUserId ИД пользователя, который изменил.
	*/
   public void changeTasksStatus(Long stateId, List<Long> taskIds, Date updateDate, Long updateUserId) {
	  try {
		 ((TaskMapper) domainMapper).changeTasksStatus(stateId, taskIds, updateDate, updateUserId);
		 logAudit(AuditLevel.INFO, getDomainAuditType(), AuditObjOperation.UPDATE, null,
			 "Успешное обновление статусов заданий", null);
	  } catch (Exception e) {
		 String message = "Не удалось обновить статусы заданий. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.UPDATE, null, message, e);
		 throw new PBKException(message, e);
	  }
   }

   @SuppressWarnings("unchecked")
   @DepartmentAuthorization
   @Override
   public List<TaskViewDomain> getGridViews(BaseGridViewParams params) {
	  List<TaskViewDomain> views = null;
	  try {
		 views = getDomainMapper().getGridViews(params);
	  } catch (Exception e) {
		 String message = "Не удалось получить список gridView. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return views;
   }

   /**
	* Получить view задания по id.
	*
	* @param taskId id задания
	* @return view задания
	*/
   public TaskViewDomain getGridViewByTaskId(Long taskId) {
	  TaskViewDomain view = null;
	  try {
		 view = ((TaskMapper) getDomainMapper()).getGridViewByTaskId(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось получить домен. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return view;
   }

   /**
	* Получает домен заданий для отображения в таблице по параметрам.
	*
	* @param params параметры
	* @return домен заданий
	*/
   @DepartmentAuthorization
   public Tasks getPlanTasksForTable(BaseGridViewParams params) {
	  Tasks tasks = new Tasks();
	  try {
		 tasks.setTasks(((TaskMapper) domainMapper).getPlanTaskViews(params));
		 if (tasks.getTasks() != null && !tasks.getTasks().isEmpty()) {
			tasks.setRoutes(((TaskMapper) domainMapper).getPlanRoutsForTask(tasks.getTasks()));
		 }
	  } catch (Exception e) {
		 String message = "Не удалось получить список для отображения плановых заданий в таблице. Ошибка: "
			 + e.getMessage();
		 logAudit(AuditLevel.ERROR, PlanAuditType.PLAN_TASK, AuditObjOperation.SELECT, params, message, e);
		 throw new PBKException(message, e);
	  }
	  return tasks;
   }

   /**
	* Получение задания по id расписания.
	*
	* @param scheduleId id расписания
	* @return задание
	*/
   public Task getTaskByScheduleId(Long scheduleId) {
	  Task task = null;
	  try {
		 task = ((TaskMapper) domainMapper).getTaskByScheduleId(scheduleId);
	  } catch (Exception e) {
		 String message = "Не удалось получить задание по id расписания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, scheduleId, message, e);
		 throw new PBKException(message, e);
	  }
	  return task;
   }

   /**
	* Проверка можно ли задание с идентификатором {@code taskId}
	* автоматически закрыть. Задание можно автоматически закрыть
	* если количество проверенных ТС совпадает с количеством
	* проверок АСКП или БСК. Критерий сравнения выбирается из
	* настройки AUTO_CLOSE_TASK_BY.
	*
	* @param taskId идентификатор задания.
	* @return
	*/
   public Boolean canAutoCloseTask(Long taskId) {
	  Boolean result = false;
	  try {
		 result = ((TaskMapper) domainMapper).canAutoCloseTask(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось проверить закрытие задания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, taskId, message, e);
		 throw new PBKException(message, e);
	  }
	  return result;
   }

   /**
	* Получение дополнительной информации по id задания (место встречи,
	* подразделние).
	*
	* @param taskId id задания
	* @return дополнительная информация по заданию
	*/
   public TaskAdditionalInfo getTaskAdditionalInfoById(Long taskId) {
	  TaskAdditionalInfo result = null;
	  try {
		 result = ((TaskMapper) domainMapper).getTaskAdditionalInfoById(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось получить дополнительную информацию по заданию. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, taskId, message, e);
		 throw new PBKException(message, e);
	  }
	  return result;
   }

   /**
	* Получить список заданий на дату.
	*
	* @param workDate дата
	* @return список заданий
	*/
   public List<Task> getTasksByWorkDate(Date workDate) {
	  return ((TaskMapper) domainMapper).getTasksByDate(workDate);
   }

	public List<Long> getTasksOfFiredEmployees() {
		return ((TaskMapper) domainMapper).getTasksOfFiredEmployees();
	}

	public List<Long> getTasksOfMovedDepEmployees() {
		return ((TaskMapper) domainMapper).getTasksOfMovedDepEmployees();
	}

	public List<Long> getTasksOfChangedPlanEmployees() {
		return ((TaskMapper) domainMapper).getTasksOfChangedPlanEmployees();
	}



   /**
	* Получение списка районов задания.
	*
	* @param taskId     ИД задания.
	* @param districtId ИД текущего района
	* @return
	*/
   public List<SelectItem> getTaskDistricts(Long taskId, Long districtId) {
	  return ((TaskMapper) domainMapper).getTaskDistricts(taskId, districtId);
   }

   /**
	* Найти задание контроллера на дату {@code date}.
	* Задание подбирается по следующим правилам. Для контролера
	* с идентификатором {@code employeeId} в зависимости от времени.
	* <p>
	* 1) если  время с 0 до 2-59, то выбирается задание для предыдущего дня;
	* 2) если время с 3 до 7-59, то смотрим задание для контролера на предыдущий день.
	* Если он - ночник, то выбирается задание предыдущего дня. В противном случае - задание рассматриваемого дня.
	* 3) Если время с 8 до 23-59, то однозначно выбирается задание рассматриваемого дня.
	*
	* @param employeeId - ИД сотрудника.
	* @param date       - дата выполнения.
	* @return
	*/
   public Task getEmployeeTask(Long employeeId, Date date, boolean shiftHours) {
   		TaskMapper taskMapper = (TaskMapper) domainMapper;
	    Date workDate = DateUtils.shiftToDayStart(date);
		if (shiftHours) {
			int hour = DateUtils.get(date, Calendar.HOUR_OF_DAY);

			if (hour >= 0 && hour < 3) {
				return taskMapper.getEmployeeTask(employeeId, DateUtils.addDays(workDate, -1));
			} else if (hour >= 3 && hour < 8) {
				Task nightTask = taskMapper.getEmployeeTask(employeeId, DateUtils.addDays(workDate, -1));
				if (nightTask != null && nightTask.getShiftId().equals(Shift.NIGHT.getId())) {
					return nightTask;
				}
			}
		}
	  return taskMapper.getEmployeeTask(employeeId, workDate);
   }

	//@Transactional(propagation= Propagation.REQUIRES_NEW)
	public void removeTasksByWorkDate(Long deptId, Date workDate) {
	  /*taskRouteRepository.removeTasksRoutesByWorkDate(deptId, workDate);
	  taskMapper.removeTasksByWorkDate(deptId, workDate);*/
		TaskMapper taskMapper = (TaskMapper) domainMapper;
		HashMap<String, Object> params = new HashMap<String, Object>();
		params.put("deptId", deptId);
		params.put("workDate", workDate);
		taskMapper.removeTasksByWorkDateP(params);
	}

	//@Transactional(propagation= Propagation.REQUIRES_NEW)
	public void updateTaskDistrict(Map<Long, Long> tasks){
		TaskMapper taskMapper = (TaskMapper) domainMapper;
   	   	taskMapper.updateTaskDistrict(tasks);
	}

	public List<PuskTask> getTasksForPusk() {
		return ((TaskMapper) domainMapper).getTasksForPusk();
	}

}
