package ru.armd.pbk.repositories.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.tasks.TaskState;
import ru.armd.pbk.enums.core.TaskAuditType;
import ru.armd.pbk.mappers.tasks.TaskStateMapper;

import java.util.List;

/**
 * Репозиторий состояний заданий.
 */
@Repository
public class TaskStateRepository
	extends BaseDomainRepository<TaskState> {

   public static final Logger LOGGER = Logger.getLogger(TaskStateRepository.class);

   @Autowired
   TaskStateRepository(TaskStateMapper mapper) {
	  super(TaskAuditType.TASK_TASK_STATE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить список статусов заданий.
	*
	* @param taskIds задания.
	* @return
	*/
   public List<SelectItem> getSelectListGroup(List<Long> taskIds) {
	  return ((TaskStateMapper) getDomainMapper()).getSelectListGroup(taskIds, taskIds.size());
   }
}
