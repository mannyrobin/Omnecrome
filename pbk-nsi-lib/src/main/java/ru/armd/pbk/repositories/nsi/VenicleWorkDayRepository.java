package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.VenicleWorkDay;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.VenicleWorkDayMapper;

/**
 * Репозиторий для работы с выходами ТС.
 */
@Repository
public class VenicleWorkDayRepository
	extends BaseDomainRepository<VenicleWorkDay> {

   public static final Logger LOGGER = Logger.getLogger(VenicleRepository.class);

   @Autowired
   VenicleWorkDayRepository(VenicleWorkDayMapper mapper) {
	  super(NsiAuditType.NSI_TS_WORK_DAY, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
