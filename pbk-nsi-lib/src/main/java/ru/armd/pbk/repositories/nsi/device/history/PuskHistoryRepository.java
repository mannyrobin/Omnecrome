package ru.armd.pbk.repositories.nsi.device.history;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDeviceOwnerHistoryDomain;
import ru.armd.pbk.core.repositories.BaseDeviceHistoryRepository;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.device.history.PuskHistoryMapper;

/**
 * Репозиторий истории ПУсК.
 */
@Repository
public class PuskHistoryRepository
	extends BaseDeviceHistoryRepository<BaseDeviceOwnerHistoryDomain> {

   public static final Logger LOGGER = Logger.getLogger(PuskHistoryRepository.class);

   @Autowired
   PuskHistoryRepository(PuskHistoryMapper mapper) {
	  super(NsiAuditType.NSI_PUSK_HISTORY, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
