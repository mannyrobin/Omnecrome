package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.TsCapacity;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.TsCapacityMapper;

/**
 * Репозиторий вместимость ТС.
 */
@Repository
public class TsCapacityRepository
	extends BaseDomainRepository<TsCapacity> {

   public static final Logger LOGGER = Logger.getLogger(TsCapacityRepository.class);

   @Autowired
   TsCapacityRepository(TsCapacityMapper mapper) {
	  super(NsiAuditType.NSI_TS_CAPACITY, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
