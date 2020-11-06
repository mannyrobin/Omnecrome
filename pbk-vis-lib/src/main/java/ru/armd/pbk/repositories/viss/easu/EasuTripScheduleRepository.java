package ru.armd.pbk.repositories.viss.easu;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.easu.EasuTripSchedule;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.easu.EasuTripScheduleMapper;

import java.util.List;

/**
 * Маппер для нарядов ЕАСУ.
 */
@Repository
public class EasuTripScheduleRepository
	extends BaseDomainRepository<EasuTripSchedule> {

   public static final Logger LOGGER = Logger.getLogger(EasuTripScheduleRepository.class);

   EasuTripScheduleMapper mapper;

   @Autowired
   EasuTripScheduleRepository(EasuTripScheduleMapper mapper) {
	  super(VisAuditType.VIS_EASUFHD_TRIP_SCHEDULE, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   public void insertBulk(List<EasuTripSchedule> list) {
	  mapper.insertBulk(list);
   }
}
