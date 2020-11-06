package ru.armd.pbk.repositories.viss;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.Vis;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.VisMapper;

/**
 * Репозиторий ВИС.
 */
@Repository
public class VisRepository
	extends BaseDomainRepository<Vis> {

   public static final Logger LOGGER = Logger.getLogger(VisRepository.class);

   /**
	* Конструктор.
	*
	* @param visMapper Маппер репозитория.
	*/
   @Autowired
   public VisRepository(VisMapper visMapper) {
	  super(VisAuditType.VIS_VIS, visMapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
