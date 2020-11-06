package ru.armd.pbk.services.viss.gismgt;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.armd.pbk.core.services.BaseDomainService;
import ru.armd.pbk.domain.viss.gismgt.GmDistrict;
import ru.armd.pbk.dto.viss.gismgt.GmDistrictDTO;
import ru.armd.pbk.repositories.viss.gismgt.GmDistrictRepository;

/**
 * Сервис ГИС МГТ Округов.
 */
@Service
public class GmDistrictService
	extends BaseDomainService<GmDistrict, GmDistrictDTO> {

   private static final Logger LOGGER = Logger.getLogger(GmTransportKindService.class);

   @Autowired
   GmDistrictService(GmDistrictRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

}
