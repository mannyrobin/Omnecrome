package ru.armd.pbk.services.nsi.bonus;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.nsi.bonus.BonusPeriod;
import ru.armd.pbk.dto.nsi.bonus.BonusPeriodDTO;
import ru.armd.pbk.matcher.nsi.bonus.IBonusPeriodMatcher;
import ru.armd.pbk.repositories.nsi.bonus.BonusPeriodRepository;

/**
 * Сервис коэфициентов премирования.
 */
@Service
public class BonusPeriodService
	extends BaseDomainService<BonusPeriod, BonusPeriodDTO> {

   private static final Logger LOGGER = Logger.getLogger(BonusPeriodService.class);

   @Autowired
   BonusPeriodService(BonusPeriodRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public BonusPeriod toDomain(BonusPeriodDTO dto) {
	  return IBonusPeriodMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public BonusPeriodDTO toDTO(BonusPeriod domain) {
	  return IBonusPeriodMatcher.INSTANCE.toDTO(domain);
   }
}
