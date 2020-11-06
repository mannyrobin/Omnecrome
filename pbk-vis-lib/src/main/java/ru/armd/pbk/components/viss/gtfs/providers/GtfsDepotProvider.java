package ru.armd.pbk.components.viss.gtfs.providers;


import org.springframework.beans.factory.ObjectFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.armd.pbk.components.viss.gtfs.loaders.GtfsDepotLoader;
import ru.armd.pbk.domain.viss.gtfs.GtfsDepot;
import ru.armd.pbk.enums.core.VisAuditType;

/**
 * Провайдер "Справочник автотранспортных предприятий".
 */
@Component
public class GtfsDepotProvider
	extends GtfsBaseProvider<GtfsDepot> {

   @Autowired
   GtfsDepotProvider(ObjectFactory<GtfsDepotLoader> loader) {
	  super(loader, VisAuditType.VIS_GTFS_DEPOTS);
   }

}
