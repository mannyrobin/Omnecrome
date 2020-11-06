package ru.armd.pbk.repositories.tasks;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.authtoken.DepartmentAuthorization;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.core.views.BaseGridView;
import ru.armd.pbk.core.views.BaseGridViewParams;
import ru.armd.pbk.domain.tasks.TaskWithdrawnCard;
import ru.armd.pbk.enums.core.TaskAuditType;
import ru.armd.pbk.mappers.tasks.TaskWithdrawnCardMapper;

import java.util.List;

@Repository
public class TaskWithdrawnCardRepository
	extends BaseDomainRepository<TaskWithdrawnCard> {

   public static final Logger LOGGER = Logger.getLogger(TaskTypeRepository.class);

   @Autowired
   TaskWithdrawnCardRepository(TaskWithdrawnCardMapper mapper) {
	  super(TaskAuditType.TASK_WITHDRAWN_CARD, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   @DepartmentAuthorization
   public <Views extends BaseGridView, Params extends BaseGridViewParams> List<Views> getGridViews(Params params) {
	  return super.getGridViews(params);
   }
}
