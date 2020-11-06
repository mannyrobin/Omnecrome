package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.WorkMode;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.WorkModeMapper;

/**
 * Репозиторий для работы со сущностью "Рабочие время".
 */
@Repository
public class WorkModeRepository
	extends BaseDomainRepository<WorkMode> {

   public static final Logger LOGGER = Logger.getLogger(WorkModeRepository.class);

   @Autowired
   WorkModeRepository(WorkModeMapper mapper) {
	  super(NsiAuditType.NSI_WORK_MODE, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
