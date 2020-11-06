package ru.armd.pbk.services.nsi;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.armd.pbk.core.services.BaseVersionDomainService;
import ru.armd.pbk.domain.nsi.stop.Stop;
import ru.armd.pbk.domain.nsi.stop.StopPlace;
import ru.armd.pbk.dto.nsi.StopDTO;
import ru.armd.pbk.matcher.nsi.IStopMatcher;
import ru.armd.pbk.repositories.nsi.stop.StopPlaceRepository;
import ru.armd.pbk.repositories.nsi.stop.StopRepository;
import ru.armd.pbk.utils.WktGeomPoint;
import ru.armd.pbk.utils.WktGeomUtils;

/**
 * Сервис билетов.
 */
@Service
public class StopService
	extends BaseVersionDomainService<Stop, StopDTO> {

   private static final Logger LOGGER = Logger.getLogger(StopService.class);

   @Autowired
   private StopPlaceRepository stopPlaceRepository;

   @Autowired
   StopService(StopRepository repository) {
	  super(repository);
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   @Override
   public StopDTO toDTO(Stop domain) {
	  return IStopMatcher.INSTANCE.toDTO(domain, stopPlaceRepository.getStopPlaceByGmStopId(domain.getGmStopId()));
   }

   @Override
   public Stop toDomain(StopDTO dto) {
	  return IStopMatcher.INSTANCE.toDomain(dto);
   }

   @Transactional
   @Override
   public StopDTO saveDTO(StopDTO dto) {
	  StopPlace stopPlace = stopPlaceRepository.getStopPlaceByGmStopId(dto.getGmStopId());
	  stopPlace.setWktGeom(WktGeomUtils.wktGeomPointToString(new WktGeomPoint(dto.getLatitude(), dto.getLongitude())));
	  stopPlaceRepository.save(stopPlace);
	  return super.saveDTO(dto);
   }

}
