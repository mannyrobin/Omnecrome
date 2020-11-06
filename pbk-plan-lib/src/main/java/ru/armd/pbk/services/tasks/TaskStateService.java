package ru.armd.pbk.services.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.core.views.SelectItem;
import ru.armd.pbk.domain.tasks.TaskState;
import ru.armd.pbk.dto.tasks.TaskStateDTO;
import ru.armd.pbk.repositories.tasks.TaskStateRepository;

import java.util.List;

/**
 * Сервис состояний заданий.
 */
@Service
public class TaskStateService
	extends BaseDomainService<TaskState, TaskStateDTO> {

   private static final Logger LOGGER = Logger.getLogger(TaskStateService.class);

   @Autowired
   TaskStateService(TaskStateRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   public List<SelectItem> getSelectListGroup(List<Long> taskIds) {
	  return ((TaskStateRepository) domainRepository).getSelectListGroup(taskIds);
   }
}
