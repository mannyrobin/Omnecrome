package ru.armd.pbk.components.viss.gtfs.providers;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsRouteLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsRoute;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Маршрутов".
 */
@Component
public class GtfsRouteProvider
	extends GtfsBaseProvider<GtfsRoute> {

   @Autowired
   GtfsRouteProvider(ObjectFactory<GtfsRouteLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_ROUTE);
   }

}
