package ru.armd.pbk.services.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.tasks.TaskWithdrawnCard;
import ru.armd.pbk.dto.tasks.TaskWithdrawnCardDTO;
import ru.armd.pbk.matcher.tasks.ITaskWithdrawnCardMatcher;
import ru.armd.pbk.repositories.tasks.TaskWithdrawnCardRepository;

@Service
public class TaskWithdrawnCardService
	extends BaseDomainService<TaskWithdrawnCard, TaskWithdrawnCardDTO> {

   private static final Logger LOGGER = Logger.getLogger(TaskTypeService.class);

   @Autowired
   TaskWithdrawnCardService(TaskWithdrawnCardRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public TaskWithdrawnCard toDomain(TaskWithdrawnCardDTO dto) {
	  return ITaskWithdrawnCardMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public TaskWithdrawnCardDTO toDTO(TaskWithdrawnCard domain) {
	  return ITaskWithdrawnCardMatcher.INSTANCE.toDTO(domain);
   }
}
