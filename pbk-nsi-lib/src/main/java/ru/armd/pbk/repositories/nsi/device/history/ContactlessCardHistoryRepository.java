package ru.armd.pbk.repositories.nsi.device.history;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDeviceOwnerHistoryDomain;
import ru.armd.pbk.core.repositories.BaseDeviceHistoryRepository;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.device.history.ContactlessCardHistoryMapper;

/**
 * Репозиторий истории БСК.
 */
@Repository
public class ContactlessCardHistoryRepository
	extends BaseDeviceHistoryRepository<BaseDeviceOwnerHistoryDomain> {

   public static final Logger LOGGER = Logger.getLogger(ContactlessCardHistoryRepository.class);

   @Autowired
   ContactlessCardHistoryRepository(ContactlessCardHistoryMapper mapper) {
	  super(NsiAuditType.NSI_CONTACTLESS_CARD_HISTORY, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
