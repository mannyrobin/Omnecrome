package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsVehicle;
import ru.armd.pbk.repositories.viss.gfts.GtfsVehicleRepository;

/**
 * Загрузчик ТС.
 */
@Component
@Scope("prototype")
public class GtfsVehiclesLoader
	extends GtfsLoader<GtfsVehicle> {

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsVehiclesLoader(GtfsVehicleRepository domainRepository) {
	  super(domainRepository, true);
   }

   @Override
   protected GtfsVehicle createDomain(String[] fields) {
	  GtfsVehicle domain = new GtfsVehicle();
	  domain.setWorkDate(getWorkDate());
	  domain.setVehicleId(getLongValue(fields[0]));
	  domain.setDepotId(getIntegerValue(fields[2]));
	  domain.setDepotNumber(getIntegerValue(fields[3]));
	  domain.setRouteType(getStringValue(fields[6]));
	  domain.setIsDeleted(getIntegerValue(fields[15]));
	  return domain;
   }

}
