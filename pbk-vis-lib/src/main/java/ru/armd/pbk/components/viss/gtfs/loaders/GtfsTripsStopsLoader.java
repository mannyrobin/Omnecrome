package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsTripsStops;
import ru.armd.pbk.repositories.viss.gfts.GtfsTripsStopsRepository;

import java.text.DateFormat;
import java.text.SimpleDateFormat;

/**
 * Загрузчик "Справочник рейсов с последовательностями остановок из ЭРМ".
 */
@Component
@Scope("prototype")
public class GtfsTripsStopsLoader
	extends GtfsLoader<GtfsTripsStops> {

   private final DateFormat df = new SimpleDateFormat("yyyyMMdd");

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsTripsStopsLoader(GtfsTripsStopsRepository domainRepository) {
	  super(domainRepository, false);
   }

   @Override
   protected GtfsTripsStops createDomain(String[] fields) {
	  GtfsTripsStops domain = new GtfsTripsStops();
	  domain.setWorkDate(getWorkDate());
	  domain.setRouteId(getIntegerValue(fields[0]));
	  domain.setRouteShortName(getStringValue(fields[1]));
	  domain.setRegNum(getStringValue(fields[2]));
	  domain.setRouteType(getStringValue(fields[3]));
	  domain.setTripId(getIntegerValue(fields[4]));
	  domain.setTripShortName(getStringValue(fields[5]));
	  domain.setDirectionId(getIntegerValue(fields[6]));
	  domain.setStartDate(getDateValue(fields[7], df));
	  domain.setEndDate(getDateValue(fields[8], df));
	  domain.setStopSequence(getIntegerValue(fields[9]));
	  domain.setStopId(getIntegerValue(fields[10]));
	  return domain;
   }

}
