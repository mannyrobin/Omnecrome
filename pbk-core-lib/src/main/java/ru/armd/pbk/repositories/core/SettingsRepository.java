package ru.armd.pbk.repositories.core;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.core.Setting;
import ru.armd.pbk.enums.core.CoreAuditType;
import ru.armd.pbk.mappers.core.SettingsMapper;

/**
 * Репозиторий настроек системы.
 */
@Repository
public class SettingsRepository
	extends BaseDomainRepository<Setting> {

   public static final Logger LOGGER = Logger.getLogger(SettingsRepository.class);

   /**
	* Создаёт репозиторий настроек.
	*
	* @param mapper маппер настроек.
	*/
   @Autowired
   SettingsRepository(SettingsMapper mapper) {
	  super(CoreAuditType.CORE_SETTING, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
