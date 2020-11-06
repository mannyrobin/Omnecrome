package ru.armd.pbk.repositories.nsi.bonus;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.bonus.Bonus;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.bonus.BonusMapper;

/**
 * Репозиторий для премирования.
 */
@Repository
public class BonusRepository
	extends BaseDomainRepository<Bonus> {

   public static final Logger LOGGER = Logger.getLogger(BonusRepository.class);

   @Autowired
   BonusRepository(BonusMapper mapper) {
	  super(NsiAuditType.NSI_BONUS, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
