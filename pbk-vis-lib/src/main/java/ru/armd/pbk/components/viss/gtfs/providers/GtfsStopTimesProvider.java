package ru.armd.pbk.components.viss.gtfs.providers;

import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsStopTimesLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsStopTimes;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Поостановочного расписания".
 */
@Component
public class GtfsStopTimesProvider
	extends GtfsBaseProvider<GtfsStopTimes> {

   @Autowired
   GtfsStopTimesProvider(ObjectFactory<GtfsStopTimesLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_STOP_TIMES);
   }

}
