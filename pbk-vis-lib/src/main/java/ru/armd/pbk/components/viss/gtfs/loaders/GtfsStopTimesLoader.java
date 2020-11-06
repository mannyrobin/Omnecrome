package ru.armd.pbk.components.viss.gtfs.loaders;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import ru.armd.pbk.domain.viss.gtfs.GtfsStopTimes;
import ru.armd.pbk.repositories.viss.gfts.GtfsStopTimesRepository;

/**
 * Загрузчик "Поостановочного расписания".
 */
@Component
@Scope("prototype")
public class GtfsStopTimesLoader
	extends GtfsLoader<GtfsStopTimes> {

   /**
	* Конструктор.
	*
	* @param domainRepository репозиторий.
	*/
   @Autowired
   public GtfsStopTimesLoader(GtfsStopTimesRepository domainRepository) {
	  super(domainRepository, true);
   }

   @Override
   protected GtfsStopTimes createDomain(String[] fields) {
	  GtfsStopTimes domain = new GtfsStopTimes();
	  domain.setWorkDate(getWorkDate());
	  domain.setTripId(getIntegerValue(fields[0]));
	  domain.setServiceId(getIntegerValue(fields[1]));
	  domain.setArrivalTime(getIntegerValueOfHourAndMinute(fields[2]));
	  domain.setDepartureTime(getIntegerValueOfHourAndMinute(fields[3]));
	  domain.setStopId(getIntegerValue(fields[4]));
	  domain.setStopSequence(getIntegerValue(fields[5]));
	  domain.setTripNum(getIntegerValue(fields[10]));
	  domain.setShiftNum(getIntegerValue(fields[11]));
	  domain.setGrafic(getIntegerValue(fields[12]));
	  return domain;
   }

}
