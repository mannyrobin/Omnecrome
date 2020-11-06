package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.domain.nsi.ParkDriver;
import ru.armd.pbk.dto.nsi.ParkDriverDTO;
import ru.armd.pbk.matcher.nsi.IParkDriverMatcher;
import ru.armd.pbk.repositories.nsi.ParkDriverRepository;

import java.util.HashMap;
import java.util.Map;

/**
 * Сервис для работы с водителями парков.
 */
@Service
public class ParkDriverService
	extends BaseVersionDomainService<ParkDriver, ParkDriverDTO> {

   private static final Logger LOGGER = Logger.getLogger(ParkDriverService.class);

   @Autowired
   ParkDriverService(ParkDriverRepository domainRepository) {
	  super(domainRepository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public ParkDriver toDomain(ParkDriverDTO dto) {
	  return IParkDriverMatcher.INSTANCE.toDomain(dto);
   }

   @Override
   public ParkDriverDTO toDTO(ParkDriver domain) {
	  return IParkDriverMatcher.INSTANCE.toDTO(domain);
   }

   @Transactional
   @Override
   public ParkDriverDTO saveDTO(ParkDriverDTO dto) {
	  for (Long tsDriverId : dto.getTsDriverIds()) {
		 dto.setTsDriverId(tsDriverId);
		 ParkDriver domain = toDomain(dto);
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("tsDriverId", dto.getTsDriverId());
		 ParkDriver actual = domainRepository.getDomain(params);
		 if (actual != null) {
			domain.setId(actual.getId());
		 }
		 domainRepository.save(domain);
	  }
	  return null;
   }

}
