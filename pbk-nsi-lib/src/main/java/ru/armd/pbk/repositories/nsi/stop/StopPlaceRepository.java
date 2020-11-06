package ru.armd.pbk.repositories.nsi.stop;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import ru.armd.pbk.core.repositories.BaseDomainRepository;
import ru.armd.pbk.domain.nsi.stop.StopPlace;
import ru.armd.pbk.enums.core.NsiAuditType;
import ru.armd.pbk.mappers.nsi.stop.StopPlaceMapper;

/**
 * Репозиторий остановочных мест.
 */
@Repository
public class StopPlaceRepository
	extends BaseDomainRepository<StopPlace> {

   public static final Logger LOGGER = Logger.getLogger(StopPlaceRepository.class);

   private StopPlaceMapper mapper;

   @Autowired
   StopPlaceRepository(StopPlaceMapper mapper) {
	  super(NsiAuditType.NSI_STOP_PLACE, mapper);
	  this.mapper = mapper;
   }

   @Override
   public Logger getLogger() {
	  return LOGGER;
   }

   /**
	* Получить остановочное место по ID остановочного пункта.
	*
	* @param id - ID остановочного пункта.
	* @return остановочное место.
	*/
   public StopPlace getStopPlaceByGmStopId(Long id) {
	  return mapper.getStopPlaceByGmStopId(id);
   }
}
