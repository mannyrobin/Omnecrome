package ru.armd.pbk.services.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.tasks.*;
import ru.armd.pbk.dto.tasks.ChangeTasksStatusDTO;
import ru.armd.pbk.dto.tasks.TaskDTO;
import ru.armd.pbk.dto.tasks.TaskReportDTO;
import ru.armd.pbk.enums.tasks.TaskStates;
import ru.armd.pbk.mappers.nsi.askppuskcheck.AskpPuskCheckMapper;
import ru.armd.pbk.mappers.tasks.AskpContactlessCardMapper;
import ru.armd.pbk.matcher.tasks.ITaskReportMatcher;
import ru.armd.pbk.repositories.tasks.TaskGkuopEmployeeRepository;
import ru.armd.pbk.repositories.tasks.TaskMgtEmployeeRepository;
import ru.armd.pbk.repositories.tasks.TaskReportRepository;
import ru.armd.pbk.repositories.tasks.TaskRepository;
import ru.armd.pbk.services.plans.schedules.PlanScheduleService;

import java.util.*;

/**
 * Сервисы отчётов по заданиям.
 */
@Service
public class TaskReportService
	extends BaseDomainService<TaskReport, TaskReportDTO> {

   private static final Logger LOGGER = Logger.getLogger(TaskReportService.class);

   private TaskRepository taskRepository;
   private TaskGkuopEmployeeRepository taskGkuopEmployeeRepository;
   private TaskMgtEmployeeRepository taskMgtEmployeeRepository;

   @Autowired
   private TasksService taskService;

   @Autowired
   private AskpPuskCheckMapper askpPuskCheckMapper;

   @Autowired
   private PlanScheduleService planScheduleService;

   @Autowired
   private AskpContactlessCardMapper askpContactlessCardMapper;

   @Autowired
   TaskReportService(TaskReportRepository repository, TaskRepository taskRepository,
					 TaskGkuopEmployeeRepository taskGkuopEmployeeRepository, TaskMgtEmployeeRepository taskMgtEmployeeRepository) {
	  super(repository);
	  this.taskRepository = taskRepository;
	  this.taskGkuopEmployeeRepository = taskGkuopEmployeeRepository;
	  this.taskMgtEmployeeRepository = taskMgtEmployeeRepository;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public TaskReport toDomain(TaskReportDTO dto) {
	  return ITaskReportMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public TaskReportDTO toDTO(TaskReport domain) {
	  TaskReportDTO dto = ITaskReportMatcher.INSTANCE.toDTO(domain);
	  int count = askpPuskCheckMapper.getUniqueRouteCount(dto.getTaskId());
	  dto.setRouteCount(count == 0 ? "Нет данных" : count + "");
	  int bskCount = askpContactlessCardMapper.getUniqueRouteCount(dto.getTaskId());
	  dto.setBskCount(bskCount == 0 ? "Нет данных" : bskCount + "");
	  return dto;
   }

   @Override
   public TaskReportDTO saveDTO(TaskReportDTO dto) {
	  TaskReport domain = toDomain(dto);
	  TaskReport resultDomain = domainRepository.save(domain);
	  Long taskId = domain.getTaskId();
	  TaskState state = taskRepository.getTaskStateByTaskId(taskId);
	  if (state.getId().equals(TaskStates.IN_PROGRESS.getId())) {
		 ChangeTasksStatusDTO change = new ChangeTasksStatusDTO();
		 change.setStateId(TaskStates.DONE.getId());
		 change.setTaskIds(Arrays.asList(taskId));
		 initUpdaterInfo(change);
		 taskRepository.changeTasksStatus(change.getStateId(), change.getTaskIds(), change.getUpdateDate(), change.getUpdateUserId());
	  }

	  List<Long> newEmployeeIds = new LinkedList<Long>();
	  newEmployeeIds.addAll(dto.getGkuopEmployees());
	  List<Long> taskIds = new ArrayList<>();
	  TaskDTO task = taskService.getDTOById(taskId);
	  for (Long employeeId : dto.getMgtEmployees()) {
		  taskIds.add(taskRepository.getEmployeeTask(employeeId, task.getTaskDate(), false).getId());
	  }
	  if (!taskIds.contains(resultDomain.getTaskId())) taskIds.add(resultDomain.getTaskId());

	  if (dto.getId() != null) {
		  for (Long id : taskIds) {
			  List<Long> oldEmployeeIds = taskGkuopEmployeeRepository.getGkuopEmployeeIdsByTaskId(id);
			  if (oldEmployeeIds != null && !oldEmployeeIds.isEmpty()) {
				  taskGkuopEmployeeRepository.deleteTaskEmployees(id, oldEmployeeIds);
			  }
		  }
	  }
	  if (newEmployeeIds.size() > 0) {
		  for (Long id : taskIds) {
			  for (Long employeeId : newEmployeeIds) {
				  taskGkuopEmployeeRepository.save(new TaskGkuopEmployee(id, employeeId));
			  }
		  }
	  }

	  newEmployeeIds = new LinkedList<Long>();
	  newEmployeeIds.addAll(dto.getMgtEmployees());
	  Long taskOwner = planScheduleService.getDTOById(task.getPlanScheduleId()).getEmployeeId();
	  if (!newEmployeeIds.contains(taskOwner)) {
		 newEmployeeIds.add(taskOwner);
	  }
	  if (dto.getId() != null && newEmployeeIds.size() > 0) {
		 taskMgtEmployeeRepository.deleteTaskEmployees(resultDomain.getTaskId(), newEmployeeIds);
	  }
	  if (newEmployeeIds != null && newEmployeeIds.size() > 1) {
		 for (Long employeeId : newEmployeeIds) {
			taskMgtEmployeeRepository.save(new TaskMgtEmployee(resultDomain.getTaskId(), employeeId));
		 }
	  }

	  taskService.processASKP(taskId);

	  return toDTO(resultDomain);
   }

   @Transactional
   @Override
   public TaskReportDTO getDTOById(Long id) {
	  TaskReportDTO dto = super.getDTOById(id);
	  dto.setGkuopEmployees(taskGkuopEmployeeRepository.getGkuopEmployeeIdsByTaskId(id));
	  dto.setMgtEmployees(taskMgtEmployeeRepository.getMgtEmployeeIdsByTaskId(id,
		  planScheduleService.getDTOById(taskService.getDTOById(id).getPlanScheduleId()).getEmployeeId()));
	  return dto;
   }
}
