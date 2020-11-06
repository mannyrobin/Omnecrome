package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.TaskReportField;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.TaskReportFieldMapper;

/**
 * Репозиторий для поля отчета сотрудника.
 */
@Repository
public class TaskReportFieldRepository
	extends BaseDomainRepository<TaskReportField> {

   public static final Logger LOGGER = Logger.getLogger(TaskReportFieldRepository.class);

   @Autowired
   TaskReportFieldRepository(TaskReportFieldMapper mapper) {
	  super(NsiAuditType.NSI_OPERATION_TYPES, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
