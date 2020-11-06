package ru.armd.pbk.repositories.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseVersionDomainRepository;
import ru.armd.pbk.domain.nsi.Park;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.ParkMapper;

/**
 * Репозиторий парков.
 *
 * @author nikita_chebotaryov
 */
@Repository
public class ParkRepository
	extends BaseVersionDomainRepository<Park> {

   public static final Logger LOGGER = Logger.getLogger(ParkRepository.class);

   /**
	* Конструктор.
	*
	* @param mapper маппер.
	*/
   @Autowired
   ParkRepository(ParkMapper mapper) {
	  super(NsiAuditType.NSI_PARK, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
