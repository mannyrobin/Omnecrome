package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.domain.nsi.Driver;
import ru.armd.pbk.domain.nsi.ParkDriver;
import ru.armd.pbk.dto.nsi.DriverDTO;
import ru.armd.pbk.dto.nsi.ParkDriverDTO;
import ru.armd.pbk.matcher.nsi.IDriverMatcher;
import ru.armd.pbk.repositories.nsi.DriverRepository;
import ru.armd.pbk.repositories.nsi.ParkDriverRepository;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Сервис водителей.
 */
@Service
public class DriverService
	extends BaseVersionDomainService<Driver, DriverDTO> {

   private static final Logger LOGGER = Logger.getLogger(DriverService.class);

   @Autowired
   ParkDriverRepository parkDriverRepository;

   @Autowired
   ParkDriverService parkDriverService;

   @Autowired
   DriverService(DriverRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public DriverDTO toDTO(Driver domain) {
	  DriverDTO dto = IDriverMatcher.INSTANCE.toDTO(domain);

	  Map<String, Object> params = new HashMap<String, Object>();
	  params.put("tsDriverId", dto.getHeadId());
	  List<ParkDriver> parkDrivers = parkDriverRepository.getDomains(params);
	  if (parkDrivers.size() == 1) {
		 // На данный момент поддерживаем связку один ко многим
		 dto.setParkId(parkDrivers.get(0).getParkId());
	  }

	  return dto;
   }

   @Override
   public Driver toDomain(DriverDTO dto) {
	  return IDriverMatcher.INSTANCE.toDomain(dto);
   }

   /**
	* Обновление парка водителей.
	*
	* @param parkId     ИД парка
	* @param tsDriverId ИД водителя ТС
	*/
   public void updateParkDrivers(Long parkId, Long tsDriverId) {
	  if (parkId == null) {
		 Map<String, Object> params = new HashMap<String, Object>();
		 params.put("tsDriverId", tsDriverId);
		 List<ParkDriver> parkDrivers = parkDriverRepository.getDomains(params);
		 for (ParkDriver parkDriver : parkDrivers) {
			parkDriverRepository.deleteSoft(Collections.singletonList(parkDriver.getId()), false);
		 }
	  } else {
		 ParkDriverDTO parkDriverDTO = new ParkDriverDTO();
		 parkDriverDTO.setParkId(parkId);
		 parkDriverDTO.setTsDriverIds(Collections.singletonList(tsDriverId));
		 // В сервисе учитывается версионность
		 parkDriverService.saveDTO(parkDriverDTO);
	  }
   }

   @Transactional
   @Override
   public DriverDTO saveVersionDTO(DriverDTO dto) {
	  Long parkId = dto.getParkId();
	  dto = super.saveVersionDTO(dto);
	  updateParkDrivers(parkId, dto.getHeadId());
	  return dto;
   }

   @Transactional
   @Override
   public DriverDTO saveDTO(DriverDTO dto) {
	  Long parkId = dto.getParkId();
	  dto = super.saveDTO(dto);
	  updateParkDrivers(parkId, dto.getHeadId());
	  return dto;
   }
}
