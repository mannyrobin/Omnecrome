package ru.armd.pbk.repositories.nsi.bonus;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.bonus.BonusPeriod;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.bonus.BonusPeriodMapper;

/**
 * Репозиторий для коэфициентов премирования.
 */
@Repository
public class BonusPeriodRepository
	extends BaseDomainRepository<BonusPeriod> {

   public static final Logger LOGGER = Logger.getLogger(BonusPeriodRepository.class);

   @Autowired
   BonusPeriodRepository(BonusPeriodMapper mapper) {
	  super(NsiAuditType.NSI_BONUS, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}
