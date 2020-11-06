package ru.armd.pbk.components.viss.gtfs.providers;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsChangeStopTimesLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsChangeStopTimes;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Оперативные изменения поостановочных расписаний".
 */
@Component
public class GtfsChangeStopTimesProvider
	extends GtfsBaseProvider<GtfsChangeStopTimes> {

   @Autowired
   GtfsChangeStopTimesProvider(ObjectFactory<GtfsChangeStopTimesLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_CHANGE_STOP_TIMES);
   }

}
