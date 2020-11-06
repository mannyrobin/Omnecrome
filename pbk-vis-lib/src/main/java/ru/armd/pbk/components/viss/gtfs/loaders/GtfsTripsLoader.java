package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsTrips;
import ru.armd.pbk.repositories.viss.gfts.GtfsTripsRepository;

/**
 * Загрузчик "Рейсов".
 */
@Component
@Scope("prototype")
public class GtfsTripsLoader
	extends GtfsLoader<GtfsTrips> {

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsTripsLoader(GtfsTripsRepository domainRepository) {
	  super(domainRepository, true);
   }

   @Override
   protected GtfsTrips createDomain(String[] fields) {
	  GtfsTrips domain = new GtfsTrips();
	  domain.setWorkDate(getWorkDate());
	  domain.setTripId(getIntegerValue(fields[0]));
	  domain.setRouteId(getIntegerValue(fields[1]));
	  domain.setTripShortName(getStringValue(fields[3]));
	  domain.setDirectionId(getIntegerValue(fields[4]));
	  domain.setTripType(getIntegerValue(fields[5]));
	  return domain;
   }

}
