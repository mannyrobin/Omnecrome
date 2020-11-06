package ru.armd.pbk.services.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.nsi.employee.EmployeeWorkMode;
import ru.armd.pbk.domain.plans.schedules.ScheduleShift;
import ru.armd.pbk.domain.tasks.PuskTask;
import ru.armd.pbk.domain.tasks.Task;
import ru.armd.pbk.domain.tasks.TaskReport;
import ru.armd.pbk.domain.tasks.TaskRoute;
import ru.armd.pbk.dto.tasks.ChangeTasksStatusDTO;
import ru.armd.pbk.dto.tasks.TaskDTO;
import ru.armd.pbk.enums.core.TaskRouteType;
import ru.armd.pbk.enums.nsi.Shift;
import ru.armd.pbk.enums.tasks.TaskStates;
import ru.armd.pbk.exceptions.PBKValidationException;
import ru.armd.pbk.repositories.nsi.bso.BsoRepository;
import ru.armd.pbk.repositories.nsi.employee.EmployeeWorkModeRepository;
import ru.armd.pbk.repositories.plans.schedules.PlanScheduleRepository;
import ru.armd.pbk.repositories.tasks.TaskMatcher;
import ru.armd.pbk.repositories.tasks.TaskReportRepository;
import ru.armd.pbk.repositories.tasks.TaskRepository;
import ru.armd.pbk.repositories.tasks.TaskRouteRepository;
import ru.armd.pbk.utils.json.JsonGridData;
import ru.armd.pbk.views.nsi.bso.BsoView;
import ru.armd.pbk.views.nsi.route.RouteMapView;
import ru.armd.pbk.views.tasks.TaskAdditionalInfo;
import ru.armd.pbk.views.tasks.TaskView;
import ru.armd.pbk.views.tasks.TaskViewDomain;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервисы заданий.
 */
@Service
public class TasksService
	extends BaseDomainService<Task, TaskDTO> {

   private static final Logger LOGGER = Logger.getLogger(TasksService.class);

   private TaskRepository repository;
   private TaskReportRepository taskReportRepository;
   private BsoRepository bsoRepository;
   private TaskRouteRepository taskRouteRepository;
   private PlanScheduleRepository planScheduleRepository;
   private EmployeeWorkModeRepository employeeWorkModeRepository;

   @Autowired
   TasksService(TaskRepository repository, TaskReportRepository taskReportRepository, BsoRepository bsoRepository,
				TaskRouteRepository taskRouteRepository, PlanScheduleRepository planScheduleRepository,
				EmployeeWorkModeRepository employeeWorkModeRepository) {
	  super(repository);
	  this.repository = repository;
	  this.taskReportRepository = taskReportRepository;
	  this.bsoRepository = bsoRepository;
	  this.taskRouteRepository = taskRouteRepository;
	  this.planScheduleRepository = planScheduleRepository;
	  this.employeeWorkModeRepository = employeeWorkModeRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public Task toDomain(TaskDTO dto) {
	  return TaskMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public TaskDTO toDTO(Task domain) {
	  return TaskMatcher.INSTANCE.toDTO(domain);
   }

   @Transactional
   @Override
   public TaskDTO saveDTO(TaskDTO dto) {
	  ScheduleShift schedule = planScheduleRepository.getById(dto.getPlanScheduleId());
	  Shift shift = Shift.getEnumById(schedule.getShiftId());
	  if (!shift.isWorkDay() && !dto.getStateId().equals(TaskStates.CANCELED.getId())) {
		 throw new PBKValidationException("taskState", "Для нерабочей смены нельзя сохранить задание с данным статусом");
	  }
	  if (dto.getId() != null) {
		 Task oldTask = repository.getById(dto.getId());
		 bsoRepository.disuse(oldTask.getBsoId(), oldTask.getId());
		 if (oldTask.getChangePlanScheduleId() != null
			 && !oldTask.getChangePlanScheduleId().equals(dto.getChangePlanScheduleId())) {
			Task reserveTask = repository.getTaskByScheduleId(oldTask.getChangePlanScheduleId());
			reserveTask.setStateId(TaskStates.IN_RESERVE.getId());
			repository.save(reserveTask);
		 }
		 if (dto.getChangePlanScheduleId() != null
			 && !dto.getChangePlanScheduleId().equals(oldTask.getChangePlanScheduleId())) {
			dto.setStateId(TaskStates.CANCELED.getId());
			Task workTask = repository.getTaskByScheduleId(dto.getChangePlanScheduleId());
			workTask.setStateId(TaskStates.CREATED.getId());
			repository.save(workTask);
			if (dto.getRoutes() != null) {
			   for (Long routeId : dto.getRoutes()) {
				  if (routeId != -1) {
					 taskRouteRepository.save(new TaskRoute(workTask.getId(), routeId));
				  }
			   }
			}
			dto.getRoutes().clear();
		 }
	  }
	  Task domain = toDomain(dto);
	  Task resultDomain = domainRepository.save(domain);
	  if (dto.getId() == null) {
		 taskReportRepository.createEmptyReport(resultDomain.getId());
	  }
	  bsoRepository.use(resultDomain.getBsoId(), resultDomain.getId());
	  if (dto.getCauseShiftId() != null) {
		 if (!schedule.getShiftId().equals(dto.getCauseShiftId())) {
			schedule.setShiftId(dto.getCauseShiftId());
			planScheduleRepository.save(schedule);
		 }
	  }

	  List<Long> newRouteIds = dto.getRoutes() == null ? new ArrayList<Long>() : new ArrayList<Long>(dto.getRoutes());
	  if (dto.getId() != null) {
		 List<Long> oldRouteIds = taskRouteRepository.getRouteIdsByTaskId(domain.getId());
		 if (oldRouteIds != null && !oldRouteIds.isEmpty() && dto.getRoutes() != null) {
			for (Long routeId : dto.getRoutes()) {
			   if (oldRouteIds.contains(routeId)) {
				  oldRouteIds.remove(routeId);
				  newRouteIds.remove(routeId);
			   }
			}
			if (!oldRouteIds.isEmpty()) {
			   taskRouteRepository.deleteTaskRoutes(domain.getId(), oldRouteIds);
			}
		 }
	  }
	  if (newRouteIds != null) {
		 for (Long routeId : newRouteIds) {
			if (routeId != -1) {
			   taskRouteRepository.save(new TaskRoute(resultDomain.getId(), routeId));
			}
		 }
	  }

	  Long scheduleId = dto.getChangePlanScheduleId() != null ? dto.getChangePlanScheduleId() : dto.getPlanScheduleId();
	  if (scheduleId != null && dto.getStateId() != null && dto.getStateId().equals(TaskStates.CLOSED.getId())) {
		 ScheduleShift scheduleShift = planScheduleRepository.getById(scheduleId);
		 scheduleShift.setWorkFactHours(dto.getFactHours());
		 planScheduleRepository.save(scheduleShift);
	  }

	  return toDTO(resultDomain);
   }

   @Override
   public int delete(List<Long> ids) {
	  for (Long id : ids) {
		 taskReportRepository.delete(getTaskReport(id));
		 Task task = repository.getById(id);
		 bsoRepository.disuse(task.getBsoId(), task.getId());
	  }
	  return super.delete(ids);
   }

   /**
	* Возвращает список использованных БСО для указанного задания (taskId в
	* params).
	*
	* @param params параметры фильтрации
	* @return список использованных БСО
	*/
   @Transactional
   public JsonGridData getBsosUsed(BaseGridViewParams params) {
	  List<BsoView> views = bsoRepository.getBsosUsedForTask(params);
	  return createJsonGridData(views, params.getPage(), params.getCount());
   }

   private TaskReport getTaskReport(Long taskId) {
	  Map<String, Object> params = new HashMap<>();
	  params.put("taskId", taskId);
	  return taskReportRepository.getDomain(params);
   }

   /**
	* Выбор статуса задания.
	*
	* @param changeTasksStatusDTO статус
	*/
   @Transactional
   public void changeTasksStatus(ChangeTasksStatusDTO changeTasksStatusDTO) {
	  initUpdaterInfo(changeTasksStatusDTO);
	  ((TaskRepository) domainRepository).changeTasksStatus(changeTasksStatusDTO.getStateId(),
		  changeTasksStatusDTO.getTaskIds(), changeTasksStatusDTO.getUpdateDate(),
		  changeTasksStatusDTO.getUpdateUserId());
   }

   @Override
   public JsonGridData getGridViews(BaseGridViewParams params) {
	  List<TaskView> planTaskViews = TaskMatcher.INSTANCE
		  .getPlanTasksViewForTable(repository.getPlanTasksForTable(params));
	  return createJsonGridData(planTaskViews, params.getPage(), params.getCount());
   }

   @Transactional
   @Override
   public TaskDTO getDTOById(Long id) {
	  TaskDTO dto = super.getDTOById(id);
	  if (dto != null) {
		 dto.setCauseShiftId(planScheduleRepository.getCauseShiftId(dto.getPlanScheduleId()));
		 dto.setDistrictRoutes(taskRouteRepository.getTaskRoutesByTaskId(id));
		 dto.setTaskDate(((TaskRepository) domainRepository).getTaskDate(id));
		 List<Long> routeIds = new ArrayList<Long>();
		 for (TaskRoute tr : dto.getDistrictRoutes()) {
			routeIds.add(tr.getRouteId());
		 }
		 dto.setRoutes(routeIds);
	  }
	  return dto;
   }

   /**
	* Выставить фактические рабочие часы.
	*
	* @param planSchId ИД
	*/
   public void setFactWorkHours(Long planSchId) {
	  ScheduleShift factHours = planScheduleRepository.getById(planSchId);
	  if (factHours != null) {
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("employeeId", factHours.getEmployeeId());
		 params.put("workDate", factHours.getPlanDate());
		 EmployeeWorkMode workMode = employeeWorkModeRepository.getDomain(params);
		 if (workMode != null) {
			factHours.setWorkFactHours(workMode.getWorkPlanHours());
			planScheduleRepository.save(factHours);
		 }
	  }
   }

   /**
	* Смена статуса задания и установка часов по плану
	* на конкретную дату.
	*
	* @param workDate дата.
	*/
   @Transactional
   public void processASKP(Date workDate) {
	  for (Task task : ((TaskRepository) domainRepository).getTasksByWorkDate(workDate)) {
		 processASKP(task.getId());
	  }
   }

   /**
	* Смена статуса задания и установка часов по плану.
	*
	* @param taskId ИД задания.
	*/
   @Transactional
   public void processASKP(Long taskId) {
	  TaskRepository repository = (TaskRepository) domainRepository;
	  Task task = repository.getById(taskId);
	  if (task != null && repository.canAutoCloseTask(taskId)) {
		 if (task.getStateId().equals(TaskStates.DONE.getId())) {
			ChangeTasksStatusDTO status = new ChangeTasksStatusDTO();
			status.setStateId(TaskStates.CLOSED.getId());
			status.setTaskIds(Arrays.asList(taskId));
			changeTasksStatus(status);
			setFactWorkHours(task.getPlanScheduleId());
		 }
	  }
   }

	public void cancelTasksOfMovedDepEmployees() {
		TaskRepository repository = (TaskRepository) domainRepository;
		cancelTasks(repository.getTasksOfMovedDepEmployees());
	}

	public void cancelTasksOfFiredEmployees() {
	   TaskRepository repository = (TaskRepository) domainRepository;
	   cancelTasks(repository.getTasksOfFiredEmployees());
	}

	public void cancelTasksOfChangedPlanEmployees() {
		TaskRepository repository = (TaskRepository) domainRepository;
		cancelTasks(repository.getTasksOfChangedPlanEmployees());
	}

	public void cancelTasks(List<Long> tasksIds) {
		ChangeTasksStatusDTO status = new ChangeTasksStatusDTO();
		status.setStateId(TaskStates.CANCELED.getId());
		status.setTaskIds(tasksIds);
		changeTasksStatus(status);
	}

	@Transactional
   public void processReport(Long taskId) {
	  Task task = ((TaskRepository) domainRepository).getById(taskId);
	  if (task != null) {
		 if (task.getStateId().equals(TaskStates.CREATED.getId())
			 || task.getStateId().equals(TaskStates.IN_RESERVE.getId())) {
			ChangeTasksStatusDTO status = new ChangeTasksStatusDTO();
			status.setStateId(TaskStates.IN_PROGRESS.getId());
			status.setTaskIds(Arrays.asList(taskId));
			changeTasksStatus(status);
		 }
	  }
   }

   @Transactional
   public TaskViewDomain getGridViewByTaskId(Long taskId) {
	  return repository.getGridViewByTaskId(taskId);
   }

   public List<SelectItem> getTaskRouteType() {
	  return TaskRouteType.getSelItems();
   }

   @Transactional
   public List<RouteMapView> getTaskRoutesForType(Long typeId, Long taskId) {
	  return taskRouteRepository.getTaskRoutesForType(typeId, taskId);
   }

   /**
	* Получение дополнительной информации по id задания (место встречи,
	* подразделние).
	*
	* @param taskId id задания
	* @return дополнительная информация по заданию
	*/
   public TaskAdditionalInfo getTaskAdditionalInfoById(Long taskId) {
	  return repository.getTaskAdditionalInfoById(taskId);
   }

   /**
	* Получение списка всех маршрутов для задания.
	* (сопутствующие от, до)
	*
	* @param taskId - ИД задания
	* @return
	*/
   public List<SelectItem> getAllRoutesForTask(Long taskId) {
	  return taskRouteRepository.getAllRoutesForTask(taskId);
   }

   /**
	* Получение списка районов задания.
	*
	* @param taskId     ИД задания.
	* @param districtId ИД текущего района
	* @return
	*/
   public List<SelectItem> getTaskDistricts(Long taskId, Long districtId) {
	  return repository.getTaskDistricts(taskId, districtId);
   }

   @Transactional
   public List<PuskTask> getTasksForPusk() {
   	  return repository.getTasksForPusk();
   }

}
