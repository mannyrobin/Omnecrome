package ru.armd.pbk.repositories.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.viss.gismgt.GmDistrict;
import ru.armd.pbk.enums.core.VisAuditType;
import ru.armd.pbk.mappers.viss.gismgt.GmDistrictMapper;

/**
 * Репозиторий для работы со сущностью "ГИС МГТ Округ".
 */
@Repository
public class GmDistrictRepository
	extends BaseDomainRepository<GmDistrict> {

   public static final Logger LOGGER = Logger.getLogger(GmDistrictRepository.class);

   @Autowired
   GmDistrictRepository(GmDistrictMapper mapper) {
	  super(VisAuditType.VIS_GISMGT_DISTRICT, mapper);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}