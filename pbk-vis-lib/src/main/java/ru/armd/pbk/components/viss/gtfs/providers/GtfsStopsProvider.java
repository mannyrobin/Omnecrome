package ru.armd.pbk.components.viss.gtfs.providers;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsStopsLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsStops;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Остановок".
 */
@Component
public class GtfsStopsProvider
	extends GtfsBaseProvider<GtfsStops> {

   @Autowired
   GtfsStopsProvider(ObjectFactory<GtfsStopsLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_STOPS);
   }

}
