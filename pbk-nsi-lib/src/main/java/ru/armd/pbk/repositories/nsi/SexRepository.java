package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.Sex;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.SexMapper;

/**
 * Репозиторий "пола".
 *
 * @author nikita_chebotaryov
 */
@Repository
public class SexRepository
	extends BaseDomainRepository<Sex> {
   public static final Logger LOGGER = Logger.getLogger(SexRepository.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   public SexRepository(SexMapper mapper) {
	  super(NsiAuditType.NSI_SEX, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
