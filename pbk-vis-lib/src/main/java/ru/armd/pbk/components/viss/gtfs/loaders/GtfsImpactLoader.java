package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsImpact;
import ru.armd.pbk.repositories.viss.gfts.GtfsImpactRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Загрузчик "Управляющие воздействия".
 */
@Component
@Scope("prototype")
public class GtfsImpactLoader
	extends GtfsLoader<GtfsImpact> {

   private final DateFormat df = new SimpleDateFormat("yyyyMMdd");
   private final DateFormat dateTimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsImpactLoader(GtfsImpactRepository domainRepository) {
	  super(domainRepository, false);
   }

   @Override
   protected GtfsImpact createDomain(String[] fields) {
	  if (!filter(fields[2], df)) {
		 return null;
	  }
	  GtfsImpact domain = new GtfsImpact();
	  domain.setWorkDate(getWorkDate());
	  domain.setDepotId(getIntegerValue(fields[1]));
	  domain.setImpactId(getIntegerValue(fields[2]));
	  domain.setImpactTime(getDateValue(fields[3], dateTimeFormat));
	  domain.setVehicleId(getIntegerValue(fields[4]));
	  domain.setTripId(getIntegerValue(fields[5]));
	  domain.setGrafic(getIntegerValue(fields[6]));
	  domain.setRouteNum(getIntegerValue(fields[7]));
	  domain.setShiftNum(getIntegerValue(fields[8]));
	  return domain;
   }

}
