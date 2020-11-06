package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsDepot;
import ru.armd.pbk.repositories.viss.gfts.GtfsDepotRepository;

/**
 * Загрузчик "автотранспортных предприятий".
 */
@Component
@Scope("prototype")
public class GtfsDepotLoader
	extends GtfsLoader<GtfsDepot> {

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsDepotLoader(GtfsDepotRepository domainRepository) {
	  super(domainRepository, true);
   }

   @Override
   protected GtfsDepot createDomain(String[] fields) {
	  GtfsDepot domain = new GtfsDepot();
	  domain.setWorkDate(getWorkDate());
	  domain.setAgencyId(getIntegerValue(fields[0]));
	  domain.setDepotId(getIntegerValue(fields[1]));
	  domain.setDepotName(getStringValue(fields[2]));
	  domain.setIsDeleted(getIntegerValue(fields[4]));
	  return domain;
   }

}
