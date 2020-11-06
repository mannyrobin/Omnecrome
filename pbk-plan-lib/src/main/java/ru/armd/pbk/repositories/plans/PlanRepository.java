package ru.armd.pbk.repositories.plans;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.domain.BaseDomain;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.enums.core.PlanAuditType;
import ru.armd.pbk.mappers.plans.PlanMapper;

/**
 * Репозиторий планов.
 */
@Repository
public class PlanRepository
	extends BaseDomainRepository<BaseDomain> {

   public static final Logger LOGGER = Logger.getLogger(PlanRepository.class);

   @Autowired
   PlanRepository(PlanMapper mapper) {
	  super(PlanAuditType.PLAN_PLAN, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }
}