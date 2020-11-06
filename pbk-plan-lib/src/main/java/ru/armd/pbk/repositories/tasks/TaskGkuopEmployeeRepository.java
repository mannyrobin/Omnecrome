package ru.armd.pbk.repositories.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.tasks.TaskGkuopEmployee;
import ru.armd.pbk.enums.core.AuditLevel;
import ru.armd.pbk.enums.core.AuditObjOperation;
import ru.armd.pbk.enums.core.TaskAuditType;
import ru.armd.pbk.exceptions.PBKException;
import ru.armd.pbk.mappers.tasks.TaskGkuopEmployeeMapper;

import java.util.List;

/**
 * Репозиторий сотрудников ГКУ ОП заданий.
 */
@Repository
public class TaskGkuopEmployeeRepository
	extends BaseDomainRepository<TaskGkuopEmployee> {

   public static final Logger LOGGER = Logger.getLogger(TaskGkuopEmployeeRepository.class);

   private TaskGkuopEmployeeMapper taskGkuopEmployeeMapper;

   @Autowired
   TaskGkuopEmployeeRepository(TaskGkuopEmployeeMapper mapper) {
	  super(TaskAuditType.TASK_EMPLOYEE, mapper);
	  this.taskGkuopEmployeeMapper = mapper;
   }

   /**
	* Удаляет сотрудников ГКУ ОП заданий по id задания и сотрудников.
	*
	* @param taskId      id задания
	* @param employeeIds список id сотрудников
	* @return
	*/
   public int deleteTaskEmployees(Long taskId, List<Long> employeeIds) {
	  int count = 0;
	  if (employeeIds == null) {
		 return count;
	  }
	  try {
		 count = taskGkuopEmployeeMapper.deleteTaskEmployees(taskId, employeeIds);
		 logAudit(AuditLevel.TRACE, getDomainAuditType(), AuditObjOperation.DELETE, employeeIds,
			 "Успешно удалено " + count + " из " + employeeIds.size() + " записи/записей.", null);
	  } catch (Exception e) {
		 String message = "Не удалось удалить записи. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.DELETE, employeeIds, message, e);
		 throw new PBKException(message, e);
	  }
	  return count;
   }

   /**
	* Получает все id сотрудников ГКУ ОП для задания.
	*
	* @param taskId id задания
	* @return список id сотрудников
	*/
   public List<Long> getGkuopEmployeeIdsByTaskId(Long taskId) {
	  List<Long> routeIds = null;
	  try {
		 routeIds = taskGkuopEmployeeMapper.getGkuopEmployeeIdsByTaskId(taskId);
	  } catch (Exception e) {
		 String message = "Не удалось получить список id маршрутов планового задания. Ошибка: " + e.getMessage();
		 logAudit(AuditLevel.ERROR, getDomainAuditType(), AuditObjOperation.SELECT, null, message, e);
		 throw new PBKException(message, e);
	  }
	  return routeIds;
   }
}
