package ru.armd.pbk.repositories.viss.asdu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.asdu.AsduPlanTrip;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.asdu.AsduPlanTripMapper;

import java.util.List;

/**
 * Маппер для нарядов ЕАСУ.
 */
@Repository
public class AsduPlanTripRepository
	extends BaseDomainRepository<AsduPlanTrip> {

   public static final Logger LOGGER = Logger.getLogger(AsduPlanTripRepository.class);

   AsduPlanTripMapper mapper;

   @Autowired
   AsduPlanTripRepository(AsduPlanTripMapper mapper) {
	  super(VisAuditType.VIS_ASDU_PLANTRIP, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   public void insertBulk(List<AsduPlanTrip> list) {
	  mapper.insertBulk(list);
   }
}
