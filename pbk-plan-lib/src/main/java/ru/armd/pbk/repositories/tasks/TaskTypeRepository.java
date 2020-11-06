package ru.armd.pbk.repositories.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.enums.core.TaskAuditType;
import ru.armd.pbk.mappers.tasks.TaskTypeMapper;

/**
 * Репозиторий типов заданий.
 */
@Repository
public class TaskTypeRepository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(TaskTypeRepository.class);

   @Autowired
   TaskTypeRepository(TaskTypeMapper mapper) {
	  super(TaskAuditType.PLAN_TASK_TYPE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
