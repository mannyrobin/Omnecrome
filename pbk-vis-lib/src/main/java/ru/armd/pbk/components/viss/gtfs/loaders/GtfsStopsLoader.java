package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsStops;
import ru.armd.pbk.repositories.viss.gfts.GtfsStopsRepository;

/**
 * Загрузчик "Остановок".
 */
@Component
@Scope("prototype")
public class GtfsStopsLoader
	extends GtfsLoader<GtfsStops> {

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsStopsLoader(GtfsStopsRepository domainRepository) {
	  super(domainRepository, true);
   }

   @Override
   protected GtfsStops createDomain(String[] fields) {
	  GtfsStops domain = new GtfsStops();
	  domain.setWorkDate(getWorkDate());
	  domain.setStopId(getIntegerValue(fields[0]));
	  domain.setStopName(getStringValue(fields[2]));
	  domain.setStopLat(getStringValue(fields[4]));
	  domain.setStopLon(getStringValue(fields[5]));
	  domain.setIsDeleted(getIntegerValue(fields[13]));
	  return domain;
   }

}
