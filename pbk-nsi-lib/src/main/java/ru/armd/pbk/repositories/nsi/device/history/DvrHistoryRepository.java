package ru.armd.pbk.repositories.nsi.device.history;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDeviceOwnerHistoryDomain;
import ru.armd.pbk.core.repositories.BaseDeviceHistoryRepository;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.device.history.DvrHistoryMapper;

/**
 * Репозиторий истории видеорегистраторов.
 */
@Repository
public class DvrHistoryRepository
	extends BaseDeviceHistoryRepository<BaseDeviceOwnerHistoryDomain> {

   public static final Logger LOGGER = Logger.getLogger(DvrHistoryRepository.class);

   @Autowired
   DvrHistoryRepository(DvrHistoryMapper mapper) {
	  super(NsiAuditType.NSI_DVR_HISTORY, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
