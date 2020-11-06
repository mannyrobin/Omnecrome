package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsRoute;
import ru.armd.pbk.repositories.viss.gfts.GtfsRouteRepository;

/**
 * Загрузчик "Маршрутов".
 */
@Component
@Scope("prototype")
public class GtfsRouteLoader
	extends GtfsLoader<GtfsRoute> {

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsRouteLoader(GtfsRouteRepository domainRepository) {
	  super(domainRepository, false);
   }

   @Override
   protected GtfsRoute createDomain(String[] fields) {
	  GtfsRoute domain = new GtfsRoute();
	  domain.setWorkDate(getWorkDate());
	  domain.setRouteId(getIntegerValue(fields[0]));
	  domain.setAgencyId(getIntegerValue(fields[1]));
	  domain.setRouteShortName(getStringValue(fields[3]));
	  domain.setRouteLongName(getStringValue(fields[4]));
	  domain.setRouteDesc(getStringValue(fields[5]));
	  domain.setRouteView(getStringValue(fields[7]));
	  domain.setRouteType(getStringValue(fields[8]));
	  return domain;
   }

}
