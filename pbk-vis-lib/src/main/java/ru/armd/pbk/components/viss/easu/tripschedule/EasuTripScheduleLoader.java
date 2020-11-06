package ru.armd.pbk.components.viss.easu.tripschedule;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.core.loaders.BaseCsvDomainLoader;
import ru.armd.pbk.domain.viss.easu.EasuTripSchedule;
import ru.armd.pbk.repositories.viss.easu.EasuTripScheduleRepository;
import ru.armd.pbk.utils.ImportResult;

import java.io.InputStream;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

/**
 * Загрузчик нарядов.
 */
@Component
public class EasuTripScheduleLoader
	extends BaseCsvDomainLoader<EasuTripSchedule> {

   private Date date;
   private List<EasuTripSchedule> cache = new LinkedList<EasuTripSchedule>();
   private EasuTripScheduleRepository repository;

   @Autowired
   EasuTripScheduleLoader(EasuTripScheduleRepository domainRepository) {
	  super(domainRepository, false, "windows-1251");
	  repository = domainRepository;
   }

   @Override
   protected EasuTripSchedule createDomain(String[] fields) {
	  //код расписания;номер строки выхода;наименование маршрута;номер выхода;смена?;номер расписания;-;-;-;-;-;-;-;-;-;Краткое наименование к/ст;наименование к/ст;-;-;-;направление маршрута;-;доля рейса;-;-;-;№ рейса;-;межостановочный пробег;....
	  //88382;1;767;108;1;88382;0;1;АВТОБУС;1;БУДНИ;114;А -14;2;1;-;А -14;0;0;0;-;-;0;1;439;7:19;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0;0

	  EasuTripSchedule domain = new EasuTripSchedule();
	  domain.setDate(date);
	  domain.setScheduleCode(fields[0]);
	  domain.setRecordNum(getLongValue(fields[1]));
	  domain.setRouteCode(fields[2]);
	  domain.setMoveCode(fields[3]);
	  domain.setShiftNum(getLongValue(fields[4]));
	  domain.setVehicleType(fields[8]);
	  domain.setDayType(fields[10]);
	  domain.setShortStopName(fields[15]);
	  domain.setStopName(fields[16]);
	  domain.setTime(fields[25]);
	  domain.setRouteNum(getLongValue(fields[26]));
	  return domain;
   }

   @Override
   protected EasuTripSchedule getExistedDomain(EasuTripSchedule newDomain) {
	  // уникально
	  return null;
   }

   public ImportResult<EasuTripSchedule> importFile(InputStream is, Date date) {
	  this.date = date;
	  return super.importFile(is);
   }

   @Override
   protected void doProcessFields(String[] fields) {
	  cache.add(createDomain(fields));
	  if (cache.size() > 100) {
		 try {
			repository.insertBulk(cache);
		 } finally {
			cache.clear();
		 }
	  }
   }

   @Override
   protected void afterLastRow() {
	  if (cache.size() > 0) {
		 try {
			repository.insertBulk(cache);
		 } finally {
			cache.clear();
		 }
	  }
   }
}
